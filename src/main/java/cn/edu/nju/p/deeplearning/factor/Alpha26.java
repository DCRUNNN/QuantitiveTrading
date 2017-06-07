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
import java.util.List;

/**
 * guarantee the date and the code is effective
 */
@Component
public class Alpha26 {

    @Autowired
    private StockDao stockDao;

    public double getAlpha(String code, LocalDate date) {

        double max = Double.MIN_NORMAL;
        for (int i = 0; i < 3; i++) {
            double correlation = getCorrelation(code, date);
            if (correlation > max) {
                max = correlation;
            }
            date = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
        }
        return -1 * max;
    }

    private double getCorrelation(String code, LocalDate date) {

        double[] xArray = new double[5];
        double[] yArray = new double[5];
        for (int i = 0; i < 5; i++) {
            xArray[i] = getRankVolumeOfPastFiveDays(code, date);
            yArray[i] = getRankHighOfPastFiveDays(code, date);
            date = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
        }
        System.out.println("PearsonCorrelation ------> " + code + "   "+date.toString()+"      "+new PearsonsCorrelation().correlation(xArray, yArray));
        return new PearsonsCorrelation().correlation(xArray, yArray);
    }

    private double getRankHighOfPastFiveDays(String code, LocalDate date) {

        double codeHigh = getTotalHighOfPastFiveDays(code, date);
        List<String> allCodes = StockHelper.getAllValidStocksLastThirtyDay(date);
        double maxHigh = 0;
        for (String stockCode : allCodes) {
            double totalHigh = getTotalHighOfPastFiveDays(stockCode, date);
            if (totalHigh > maxHigh) {
                maxHigh = totalHigh;
            }
        }
        return divide(codeHigh, maxHigh);
    }

    private double getTotalHighOfPastFiveDays(String code, LocalDate date) {

        double total = 0.0;
        for (int i = 0; i < 5; i++) {
            total += stockDao.getStockHigh(code, date);
            date = DateHelper.getLastDate(date, adate -> StockHelper.isValidByCode(code, adate));
        }
        return total;
    }

    private double getRankVolumeOfPastFiveDays(String code, LocalDate date) {

        int codeVolume = getTotalVolumeOfPastFiveDays(code, date);
        List<String> allCodes = StockHelper.getAllValidStocksLastThirtyDay(date);
        int maxVolume = 0;
        for (String stockCode : allCodes) {
            int volume = getTotalVolumeOfPastFiveDays(stockCode, date);
            if (volume > maxVolume) {
                maxVolume = volume;
            }
        }
        return (double) codeVolume / maxVolume;
    }

    private int getTotalVolumeOfPastFiveDays(String code, LocalDate date) {

        int total = 0;
        for (int i = 0; i < 5; i++) {
            total += stockDao.getStockVolume(code, date);
            date = DateHelper.getLastDate(date, date1 -> StockHelper.isValidByCode(code, date1));
        }
        return total;
    }

    private double divide(double a, double b) {

        BigDecimal bigDecimal_a = new BigDecimal(a);
        BigDecimal bigDecimal_b = new BigDecimal(b);
        return bigDecimal_a.divide(bigDecimal_b, RoundingMode.HALF_UP).doubleValue();
    }
}
