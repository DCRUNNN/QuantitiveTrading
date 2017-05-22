package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.service.exhibition.KLineService;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.DoubleUtils;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.utils.ema.MACDUtils;
import cn.edu.nju.p.vo.KLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * KLineService的实现类
 */
@Service
public class KLineServiceImpl implements KLineService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private MACDUtils macdUtils;

    /**
     * 通过股票代码获得KLine需要的所有VO
     *
     * @param code      股票代码
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 返回每一天的KLine数据
     */
    public List<KLineVO> getKLineVOSByCode(String code, LocalDate beginDate, LocalDate endDate) {

//        使用lambda表达式过滤没有数据的日期
        List<LocalDate> dateList = DateHelper.getBetweenDateAndFilter(beginDate,endDate, date-> StockHelper.isValidByCode(code,date));

        List<KLineVO> kLineVOList = new ArrayList<>();

        dateList.parallelStream().forEach(date -> {

            String stockName = stockDao.getStockName(code);
            double low = stockDao.getStockLow(code,date);
            double high = stockDao.getStockHigh(code,date);
            double open = stockDao.getStockOpen(code,date);
            double close = stockDao.getStockClose(code,date);
            int volume = stockDao.getStockVolume(code,date);
            boolean isGoHigh = open<close;
            int targ = isGoHigh ? 1 : 0;
            double range = high-low;
            double dBetweenOpenAndClose = Math.abs(open-close);

            double diff = macdUtils.getEmaValue(12, date, code) - macdUtils.getEmaValue(26, date, code);
            double dea = macdUtils.getDea(9, date, code);
            double macd = (diff - dea) * 2;

            diff = DoubleUtils.formatDouble(diff);
            dea = DoubleUtils.formatDouble(dea);
            macd = DoubleUtils.formatDouble(macd);

            kLineVOList.add(new KLineVO(stockName, code, date.toString(), targ, low, high, open, close, range, dBetweenOpenAndClose, volume, macd, diff, dea));
        });

        return kLineVOList
                .parallelStream()
                .sorted(Comparator.comparing(KLineVO::getDate))
                .collect(Collectors.toList());
    }

    /**
     * 通过股票名称获取k线数据
     * @param name 股票名称
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 有效日期的k线数据
     */
    public List<KLineVO> getKLineVOSByName(String name, LocalDate beginDate, LocalDate endDate) throws  StockNotFoundException {

        String code = stockDao.getStockCode(name);
        return getKLineVOSByCode(code, beginDate, endDate);
    }

}
