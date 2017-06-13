package cn.edu.nju.p.utils.ema;

import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.utils.redis.StockRedisDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * The MACD calculator utils
 */
@Component
public class MACDUtils {

 /*   @Autowired
    private StockDao stockDao;*/

    @Autowired
    private StockRedisDataUtils stockRedisDataUtils;

    @Cacheable(value = "emaValue")
    public double getEmaValue(int emaValue,LocalDate currentDate,String stockCode) {

        currentDate = StockHelper.getFirstValidDate(stockCode, currentDate);

        double close = stockRedisDataUtils.getStockClose(stockCode, currentDate); //当日收盘价

        if (emaValue == 1) {
            //last efficient date
            return close;
        }

        return (2 * close +
                (emaValue - 1) * getEmaValue(emaValue - 1
                        , StockHelper.getFirstValidDate(stockCode,currentDate.minusDays(1))
                        , stockCode))
                / (emaValue + 1);
    }


    @Cacheable("deaValue")
    public double getDea(int deaValue, LocalDate currentDate, String stockCode) {

        currentDate = StockHelper.getFirstValidDate(stockCode, currentDate);

        double diff = getEmaValue(12, currentDate, stockCode) - getEmaValue(26, currentDate, stockCode);

        if (deaValue == 1) {
            return diff;
        }

        return (2 * diff + (deaValue - 1) * getDea(deaValue - 1
                , StockHelper.getFirstValidDate(stockCode,currentDate.minusDays(1))
                , stockCode))
                / (deaValue + 1);
    }

}
