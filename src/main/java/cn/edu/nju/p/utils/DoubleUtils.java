package cn.edu.nju.p.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * the util to operate on Double
 */
public class DoubleUtils {

    public static double formatDouble(double d,int scale) {
        BigDecimal bigDecimal = new BigDecimal(d);
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static List<Double> adjDoubleList(List<Double> doubles) {

        List<Double> newList = new ArrayList<>();
        for (Double aDouble : doubles) {
            newList.add(formatDouble(aDouble * 100,2));
        }
        return newList;
    }
}
