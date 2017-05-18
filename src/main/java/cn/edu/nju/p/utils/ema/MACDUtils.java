package cn.edu.nju.p.utils.ema;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by soft on 2017/5/18.
 */
@Component
public class MACDUtils {

    @Autowired
    private StockDao stockDao;

    public double getEmaValue(int emaValue,LocalDate currentDate,String stockCode) {

        if (!StockHelper.isValidByCode(stockCode, currentDate)) {
            currentDate = DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByCode(stockCode, date));
        }

        double close = stockDao.getStockClose(stockCode, currentDate); //当日收盘价
        if (emaValue == 1) {
            //last efficient date
            return close;
        }

        return (2 * close +
                (emaValue - 1) * getEmaValue(emaValue - 1
                                , DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByCode(stockCode, date))
                                , stockCode))
                / (emaValue + 1);
    }


    public double getDea(int deaValue, LocalDate currentDate, String stockCode) {

        if (!StockHelper.isValidByCode(stockCode, currentDate)) {
            currentDate = DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByCode(stockCode, date));
        }

        double diff = getEmaValue(12, currentDate, stockCode) - getEmaValue(26, currentDate, stockCode);

        if (deaValue == 1) {
            return diff;
        }

        return (2 * diff + (deaValue - 1) * getDea(deaValue - 1
                , DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByCode(stockCode, date))
                , stockCode))
                / (deaValue + 1);

    }

}
