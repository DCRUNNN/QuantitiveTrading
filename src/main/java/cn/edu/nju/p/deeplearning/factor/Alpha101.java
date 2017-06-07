package cn.edu.nju.p.deeplearning.factor;

import cn.edu.nju.p.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * guarantee the date and the code is meaningful
 */
@Component
public class Alpha101 {

    @Autowired
    private StockDao stockDao;

    public double getAlpha(String stockCode, LocalDate localDate) {

        double high = stockDao.getStockHigh(stockCode, localDate);
        double low = stockDao.getStockLow(stockCode, localDate);
        double open = stockDao.getStockOpen(stockCode, localDate);
        double close = stockDao.getStockClose(stockCode, localDate);
        return divide((close - open), (high - low) + 0.001);
    }

    private double divide(double a, double b) {

        BigDecimal bigDecimal_a = new BigDecimal(a);
        BigDecimal bigDecimal_b = new BigDecimal(b);
        return bigDecimal_a.divide(bigDecimal_b, RoundingMode.HALF_UP).doubleValue();
    }
}
