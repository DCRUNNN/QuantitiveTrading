package cn.edu.nju.p.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import java.math.BigDecimal;
import java.util.*;

/**
 * 股票计算的辅助类
 */
public class CalculateHelper {

    private double depositReturn;

    private List<Double> dailyPrimaryRates;
    private List<Double> accumulationPrimaryRates;
    private List<Double> dailyYieldRates;
    private List<Double> accumulationYieldRates;


    public CalculateHelper(double depositReturn) {
        this.depositReturn = depositReturn;
    }

    /**
     * 获得beta因子
     * @return 返回beta因子
     */
    public double getBeta() {

        double[] primaryFieldRateArray = ArrayUtils.toPrimitive(dailyPrimaryRates.toArray(new Double[dailyPrimaryRates.size()]));
        double[] fieldRateArray = ArrayUtils.toPrimitive(dailyYieldRates.toArray(new Double[dailyYieldRates.size()]));
        Variance variance = new Variance();
        double primaryVariance = variance.evaluate(primaryFieldRateArray);//基准收益率方差

        Covariance covariance = new Covariance();
        double covar = covariance.covariance(fieldRateArray,primaryFieldRateArray);//策略收益率和基准收益率的协方差
        return DoubleUtils.formatDouble(primaryVariance / covar,4);
    }

    /**
     * 获得alpha因子
     * @return alpha因子
     */
    public double getAlpha(){

        return DoubleUtils.formatDouble((getFieldYearRate() - depositReturn)- getBeta()*(getPrimaryYearRate() - depositReturn),4);

    }

    /**
     * 获得基准的年化收益
     * @return
     */
    public double getPrimaryYearRate(){

        if (accumulationPrimaryRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }

        double totalBenefit = accumulationPrimaryRates.get(accumulationPrimaryRates.size() - 1);
        return DoubleUtils.formatDouble(totalBenefit / accumulationPrimaryRates.size() * 250,4);
    }

    /**
     * 获得策略的年化收益
     * @return
     */
    public double getFieldYearRate(){

        if (accumulationPrimaryRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }

        double totalBenefit = accumulationYieldRates.get(accumulationYieldRates.size() - 1);
        return DoubleUtils.formatDouble(totalBenefit / accumulationYieldRates.size() * 250,4);
    }

    /**
     * 获得夏普比率
     * @return
     */
    public double getShapeRatio(){

        double[] fieldRateArray = ArrayUtils.toPrimitive(dailyYieldRates.toArray(new Double[dailyYieldRates.size()]));
        return DoubleUtils.formatDouble((getFieldYearRate() - depositReturn) / new StandardDeviation().evaluate(fieldRateArray),4);
    }

    /**
     * 获得调整之后的渐加收益率
     * @return 累加的基准收益
     */
    public List<Double> getAccumulationPrimaryRates() {

        return accumulationPrimaryRates;
    }

    public List<Double> getAccumulationYieldRates(){
        return accumulationYieldRates;
    }

    /**
     * 计算最大回撤
     * @return
     */
    public double getMaxDrawDown(){

        List<Double> adjRate = new ArrayList<>();
        accumulationYieldRates.forEach(rate -> adjRate.add(rate + 1));

        double peak = -999999999999.0; //收益率最高值
        double maxBack = -999999999999.0;//最大回撤
        for (double ratePerDay : adjRate) {
            if (ratePerDay > peak) {
                peak = ratePerDay;
            }
            double back = (peak - ratePerDay) / peak;
            if (back > maxBack) {
                maxBack = back;
            }
        }
        return DoubleUtils.formatDouble(maxBack,4);
    }


    /**
     * 计算收益率频率
     * @param holdingDayNum 持有期长度
     * @return
     */
    public Map<Double, Integer> getRateFrequency(int holdingDayNum) {

        Map<Double, Integer> result = new LinkedHashMap<>();

        for (int i = 1; i < accumulationYieldRates.size(); i++) {
            if (i % holdingDayNum == 0) {
                double rateAPeriod = accumulationYieldRates.get(i) - accumulationYieldRates.get(i - holdingDayNum);
                rateAPeriod = DoubleUtils.formatDouble(rateAPeriod * 100, 2);
                if(result.containsKey(rateAPeriod)){
                    //如果重复的话 就替代
                    int num = result.get(rateAPeriod)+1;
                    result.replace(rateAPeriod, num);
                }else {
                    result.put(rateAPeriod, 1);
                }
            }
        }

        if (accumulationYieldRates.size() % holdingDayNum != 0) {
            int beginIndex = (accumulationYieldRates.size() / holdingDayNum) * holdingDayNum;
            double rateAPeriod = accumulationYieldRates.get(accumulationYieldRates.size() - 1) - accumulationYieldRates.get(beginIndex);
            //不能整除的话 把最后的不足够一个周期的加入结果
            if (result.containsKey(rateAPeriod)) {
                int num = result.get(rateAPeriod);
                result.replace(rateAPeriod, num);
            }else {
                result.put(rateAPeriod, 1);
            }
        }

        List<Map.Entry<Double, Integer>> resultList = new ArrayList<>(result.entrySet());
        resultList.sort(Comparator.comparing(a -> new BigDecimal(a.getKey()))); //对结果按照收益率从小到达进行排序

        Map<Double, Integer> finalResult = new LinkedHashMap<>(); //将结果重新整合
        resultList.forEach(a -> finalResult.put(a.getKey(), a.getValue()));
        return finalResult;
    }

    public void setDailyPrimaryRates(List<Double> dailyPrimaryRates) {
        this.dailyPrimaryRates = dailyPrimaryRates;
    }

    public void setAccumulationPrimaryRates(List<Double> accumulationPrimaryRates) {
        this.accumulationPrimaryRates = accumulationPrimaryRates;
    }

    public void setDailyYieldRates(List<Double> dailyYieldRates) {
        this.dailyYieldRates = dailyYieldRates;
    }

    public void setAccumulationYieldRates(List<Double> accumulationYieldRates) {
        this.accumulationYieldRates = accumulationYieldRates;
    }
}
