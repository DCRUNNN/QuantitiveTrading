package cn.edu.nju.p.serviceimpl;


import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.DateNotEnoughException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.service.exhibition.CompareService;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.vo.StockComparedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CompareService的实现类
 */
@Service
public class CompareServiceImpl implements CompareService {

    @Autowired
    private StockDao stockDao;

    /**
     * 通过股票代码获得比较股票的需要的数据
     *
     * @param code      股票代码
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 比较股票需要的数据
     */
    public StockComparedVO getComparedStockByCode(String code, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException,DateNotEnoughException {

        //传入过滤条件作为参数，过滤掉无效日期
        List<LocalDate> dateList = DateHelper.getBetweenDateAndFilter(beginDate,endDate, date-> StockHelper.isValidByCode(code,date));//获得两个日期之间的所有日期

        if(dateList.size() ==1||dateList.size()==0){
            throw new DateNotEnoughException("输入的两个日期间隔内没有足够的有效数据，无法得到绘制图表的数据！");
        }

        ArrayList<Double> closeList = new ArrayList<>();//这段日期的所有收盘指数
        for (LocalDate date : dateList) {
            closeList.add(stockDao.getStockClose(code, date));
        }

        double max = 0;//这段时间内的最高指数
        double min = Double.MAX_VALUE;//这段时间内的最低指数
        for (LocalDate date : dateList) {
            double high = stockDao.getStockHigh(code, date);
            if (high > max) {
                max = high;
            }

            double low = stockDao.getStockLow(code, date);
            if (low < min) {
                min = low;
            }
        }

        double endClose = stockDao.getStockAdjClose(code,dateList.get(dateList.size()-1));
        double beginClose = stockDao.getStockAdjClose(code,dateList.get(0));
        boolean hasIncreased = endClose>beginClose;//这段时间内是否上涨
        double amountOfChange = Math.abs(endClose-beginClose);//涨幅

        ArrayList<Double> logList = new ArrayList<>();//对数收益率集合
        //计算第一天的对数收益率
        LocalDate firstDate = dateList.get(0);

        //输入lambda表达式的过滤条件，（过滤掉无效日期）
        LocalDate lastDate = DateHelper.getLastDate(firstDate,checkDate-> StockHelper.isValidByCode(code,checkDate));

        double firstLogClose = Math.log(stockDao.getStockAdjClose(code,firstDate)/stockDao.getStockAdjClose(code,lastDate));
        logList.add(firstLogClose);

        //计算除去第一天的所有对数收益率
        for (int k = 1; k < dateList.size(); k++) {
            double lastClose = stockDao.getStockAdjClose(code,dateList.get(k-1));
            double currentClose = stockDao.getStockAdjClose(code,dateList.get(k));

            double logClose = Math.log(currentClose/lastClose);
            logList.add(logClose);
        }

        double sum =0;//对数收益率的和
        double sumSquare=0;//对数收益率的平方和

        for (Double log : logList) {
            sum += log;
            sumSquare += Math.pow(log, 2);
        }
        double average = sum/logList.size();//对数收益率的平均
        double squareDiff = sumSquare/logList.size()-Math.pow(average,2);//方差

        String stockName = stockDao.getStockName(code);
        return new StockComparedVO(code,stockName,max,min,hasIncreased,amountOfChange,closeList,logList,squareDiff,dateList);
    }

    /**
     * 通过股票名称获得比较股票的需要的数据
     *
     * @param name      股票名称
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 比较股票需要的数据
     */
    public StockComparedVO getComparedStockByName(String name, LocalDate beginDate, LocalDate endDate) throws DateNotEnoughException, StockNotFoundException {

        String code = stockDao.getStockCode(name);
        return getComparedStockByCode(code, beginDate, endDate);
    }

}
