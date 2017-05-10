package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.StockNoneException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.service.strategy.StockMarketService;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.vo.StockMarketVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 获取市场行情的实现类
 */
@Service
public class StockMarketServiceImpl implements StockMarketService {

    @Autowired
    private StockDao stockDao;

    /**
     * 获取当天的股票行情
     *
     * @param date 日期
     * @return 返回当日的股票数据
     */
    public StockMarketVO getMarketVO(LocalDate date) throws StockNoneException, StockNotFoundException {

        List<StockPO> polist = stockDao.getPOList(date.toString());
        if(polist == null|| polist.size()==0){
            throw new StockNoneException("输入日期当日没有股票成交！");
        }

        long volume = 0;//当日的成交量
        int amountOfLimitUp = 0;//涨停股票的数目
        int amountOfLimitDown = 0;//跌停股票数目
        int amountOf5PercentUp = 0;//涨幅超过5%的数目
        int amountOf5PercentDown = 0;//跌幅超过5%的数目
        int amountOf5PercentUptolastClose = 0;//开盘减去收盘大于5%*上个收盘指数的股票数目
        int amountOf5PercentDowntolastClose = 0;//开盘减去收盘小于-5%*上个收盘指数的股票数目

        for (StockPO po : polist) {
            volume += po.getVolume();

            //使用lambda表达式输入过滤条件（有效日期）作为参数
            LocalDate lastDate = DateHelper.getLastDate(date, checkDate -> StockHelper.isValidByCode(po.getCode(), checkDate));

            double lastClose = stockDao.getStockAdjClose(po.getCode(), lastDate.toString());//昨天收盘指数
            double currentClose = po.getAdjClose();

            if (lastClose < currentClose) {
                double percent = (currentClose - lastClose) / lastClose;
                if (percent >= (10 - 0.01*100/lastClose)) {
                    amountOfLimitUp++;
                } else if (percent > 0.05) {
                    amountOf5PercentUp++;
                }
            } else {
                double percent = (lastClose - currentClose) / lastClose;
                if (Math.abs(percent - 0.1) < 0.0000001) {
                    amountOfLimitDown++;
                } else if (percent > 0.05) {
                    amountOf5PercentDown++;
                }
            }

            double openDiffClose = po.getOpen() - po.getClose();//开盘减去收盘
            double lastSum = lastClose * 0.05;
            if (openDiffClose > lastSum) {
                amountOf5PercentUptolastClose++;
            }
            if (openDiffClose < -lastSum) {
                amountOf5PercentDowntolastClose++;
            }

        }

        return new StockMarketVO(volume,amountOfLimitUp,amountOfLimitDown,amountOf5PercentUp,amountOf5PercentDown,amountOf5PercentUptolastClose,amountOf5PercentDowntolastClose);
    }
}
