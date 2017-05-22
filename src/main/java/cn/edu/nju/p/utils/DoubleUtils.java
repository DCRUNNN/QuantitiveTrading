package cn.edu.nju.p.utils;

import java.math.BigDecimal;

/**
 * the util to operate on Double
 */
public class DoubleUtils {

    public static double formatDouble(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
