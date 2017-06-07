package cn.edu.nju.p.utils;

import java.math.BigDecimal;

/**
 * the util to operate on Double
 */
public class DoubleUtils {

    public static double formatDouble(double d,int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
