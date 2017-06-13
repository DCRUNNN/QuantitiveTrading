package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.service.exhibition.KLineService;
import cn.edu.nju.p.utils.DoubleUtils;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.utils.ema.MACDUtils;
import cn.edu.nju.p.utils.redis.StockRedisDataUtils;
import cn.edu.nju.p.vo.KLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private StockRedisDataUtils stockRedisDataUtils;

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
    public KLineVO getKLineVOSByCode(String code, LocalDate beginDate, LocalDate endDate) {

//        使用lambda表达式过滤没有数据的日期
        List<LocalDate> dateList = StockHelper.getBetweenValidDatesAndFilter(beginDate, endDate, aDate -> stockRedisDataUtils.getStockIsOpen(code, aDate));

        List<List<Object>> finalResult = new ArrayList<>(300);
        String stockName = stockRedisDataUtils.getStockName(code);

        dateList.parallelStream().forEach(date -> {
            double low = stockRedisDataUtils.getStockLow(code,date);
            double high = stockRedisDataUtils.getStockHigh(code,date);
            double open = stockRedisDataUtils.getStockOpen(code,date);
            double close = stockRedisDataUtils.getStockClose(code,date);
            long volume = stockRedisDataUtils.getStockVolume(code,date);
            boolean isGoHigh = open<close;
            int targ = isGoHigh ? 1 : 0;

            double diff = macdUtils.getEmaValue(12, date, code) - macdUtils.getEmaValue(26, date, code);
            double dea = macdUtils.getDea(9, date, code);
            double macd = (diff - dea) * 2;

            diff = DoubleUtils.formatDouble(diff,2);
            dea = DoubleUtils.formatDouble(dea,2);
            macd = DoubleUtils.formatDouble(macd,2);

            List<Object> results = new ArrayList<>();
            results.add(date.toString());
            results.add(open);
            results.add(close);
            results.add(low);
            results.add(high);
            results.add(volume);
            results.add(targ);
            results.add(macd);
            results.add(diff);
            results.add(dea);
            finalResult.add(results);
        });

        return new KLineVO(stockName, finalResult
                .parallelStream()
                .sorted(Comparator.comparing(a -> a.get(0).toString()))
                .collect(Collectors.toList()));
    }

}
