package cn.edu.nju.p.deeplearning.factor;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * get the 12th alpha factor of a stock of an exact date
 */
@Component
public class Alpha12 {

    @Autowired
    private StockDao stockDao;

    /**
     * get the value of alpha12
     * @param stockCode the code of stock
     * @param date the date
     * @return
     */
    double getAlpha(String stockCode, LocalDate date) {

        double[] xArray = new double[6];
        double[] yArray = new double[6];

        for (int i = 0; i < 6; i++) {
            xArray[i] = getRankValueOfDelta(stockCode, date); //normalizing
            yArray[i] = getRankValueOfAnother(stockCode, date); //normalizing
            date = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(stockCode, aDate));
        }

        PearsonsCorrelation pearsonsCorrelation = new PearsonsCorrelation();
        return -1 * pearsonsCorrelation.correlation(xArray, yArray);
    }

    private double getRankValueOfDelta(String stockCode, LocalDate date) {
        Double maxVolume = getMaxLogVolume(date);
        return divide(getLogVolume(stockCode, date), maxVolume);
    }

    private double getRankValueOfAnother(String stockCode, LocalDate date) {
        Double maxValue = getMaxAnother(date);
        return divide(getValue(stockCode, date), maxValue);
    }

    private double getLogVolume(String stockCode, LocalDate date) {

        //前两天的日期
        LocalDate dayBeforeYes = DateHelper.getLastDate(date, curDate->StockHelper.isValidByCode(stockCode, curDate));
        dayBeforeYes = DateHelper.getLastDate(dayBeforeYes, curDate -> StockHelper.isValidByCode(stockCode, curDate));

        int volume = stockDao.getStockVolume(stockCode, dayBeforeYes);
        if (volume == 0) {
            System.out.println(stockCode+" "+date.toString());
            return -999999999.0;
        }
        return Math.log(volume);
    }

    private double getMaxLogVolume(LocalDate date) {

        List<String> allCodes = StockHelper.getAllValidStocksLastTenDay(date);
        List<Double> allLogVolumes = new ArrayList<>();
        allCodes.forEach(code -> allLogVolumes.add(getLogVolume(code, date)));

        Double maxVolume = -999999999999.0;
        for (Double d : allLogVolumes) {
            if (d > maxVolume) {
                maxVolume = d;
            }
        }
        return maxVolume;
    }

    private double getValue(String code, LocalDate date) {

        double open = stockDao.getStockOpen(code, date);
        double close = stockDao.getStockClose(code, date);
        return divide((close - open), open);
    }

    private double getMaxAnother(LocalDate date) {

        List<String> allCodes = StockHelper.getAllValidStocksLastTenDay(date);
        List<Double> allValue = new ArrayList<>();
        allCodes.forEach(code -> {
            double open = stockDao.getStockOpen(code, date);
            double close = stockDao.getStockClose(code, date);
            allValue.add((close - open) / open);
        });
        Double maxValue = -999999999999.0;
        for (Double aDouble : allValue) {
            if (aDouble > maxValue) {
                maxValue = aDouble;
            }
        }
        return maxValue;
    }

    private double divide(double a, double b) {

        BigDecimal bigDecimal_a = new BigDecimal(a);
        BigDecimal bigDecimal_b = new BigDecimal(b);
        return bigDecimal_a.divide(bigDecimal_b, RoundingMode.HALF_UP).doubleValue();
    }
}
