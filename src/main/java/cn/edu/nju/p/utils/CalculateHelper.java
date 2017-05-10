package cn.edu.nju.p.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * 股票计算的辅助类
 */
public class CalculateHelper {

    private double depositReturn;

    private Map<LocalDate,Double> primaryRates;
    private Map<LocalDate,Double> fieldRates;

    /**
     * 手动传递基准收益率和策略收益率
     * @param depositReturn 存储年收益
     * @param primaryRates 基准收益率
     * @param fieldRates 策略收益率
     */
    public CalculateHelper(double depositReturn, Map<LocalDate, Double> primaryRates, Map<LocalDate, Double> fieldRates) {
        this.depositReturn = depositReturn;
        this.primaryRates = primaryRates;
        this.fieldRates = fieldRates;
    }

    /**
     * 默认使用的构造方法，其中的primaryRates,fieldRates都是size为0的
     * @param depositReturn 存储收益
     */
    public CalculateHelper(double depositReturn) {
        this.depositReturn = depositReturn;
        primaryRates = new LinkedHashMap<>();
        fieldRates = new LinkedHashMap<>();
    }

    /**
     * 设置基准收益率
     * @param primaryRates
     */
    public void setPrimaryRates(Map<LocalDate, Double> primaryRates) {
        this.primaryRates = primaryRates;
    }

    /**
     * 设置策略收益率
     * @param fieldRates
     */
    public void setFieldRates(Map<LocalDate, Double> fieldRates) {
        this.fieldRates = fieldRates;
    }


    /**
     * 获得beta因子
     * @return 返回beta因子
     */
    public double getBeta() {

        double[] primaryFieldRateArray = ArrayUtils.toPrimitive(primaryRates.values().toArray(new Double[primaryRates.size()]));
        double[] fieldRateArray = ArrayUtils.toPrimitive(fieldRates.values().toArray(new Double[fieldRates.size()]));
        Variance variance = new Variance();
        double primaryVariance = variance.evaluate(primaryFieldRateArray);//基准收益率方差
        Covariance covariance = new Covariance();
        double covar = covariance.covariance(fieldRateArray,primaryFieldRateArray);//策略收益率和基准收益率的协方差
        return primaryVariance / covar;
    }

    /**
     * 获得alpha因子
     * @return alpha因子
     */
    public double getAlpha(){

        return (getFieldYearRate() - depositReturn)- getBeta()*(getPrimaryYearRate() - depositReturn);

    }

    /**
     * 获得基准的年化收益
     * @return
     */
    public double getPrimaryYearRate(){

        if (primaryRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }
        List<Double> values = new ArrayList<>(getPrimaryAdjRates().values());
        double totalBenefit = values.get(values.size() - 1);
        return totalBenefit / values.size() * 250;
    }

    /**
     * 获得策略的年化收益
     * @return
     */
    public double getFieldYearRate(){

        if (fieldRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }
        List<Double> values = new ArrayList<>(getFieldAdjRates().values());
        double totalBenefit = values.get(values.size() - 1);
        return totalBenefit / values.size() * 250;
    }

    /**
     * 获得夏普比率
     * @return
     */
    public double getShapeRatio(){

        double[] fieldRateArray = ArrayUtils.toPrimitive(fieldRates.values().toArray(new Double[fieldRates.size()]));
        return (getFieldYearRate() - depositReturn) / new StandardDeviation().evaluate(fieldRateArray);
    }

    /**
     * 获得调整之后的渐加收益率
     * @return 累加的基准收益
     */
    public Map<LocalDate,Double> getPrimaryAdjRates() {

        if (primaryRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }
        return adjustRates(primaryRates);
    }

    /**
     * 获得调整之后的渐加收益
     * @return 累加的策略收益
     */
    public Map<LocalDate,Double> getFieldAdjRates(){

        if (fieldRates.size() == 0) {
            throw new RuntimeException("使用CalculateHelper的计算之前请先设置基准收益率和策略收益率！");
        }

        return adjustRates(fieldRates);
    }
    /**
     * 调整日收益率为累加收益
     * @param rates 日收益
     * @return 调整之后的渐加收益
     */
    private Map<LocalDate, Double> adjustRates(Map<LocalDate, Double> rates) {

        List<Map.Entry<LocalDate, Double>> rateList = new ArrayList<>(rates.entrySet());
        double totalNum = 0.0;
        Map<LocalDate, Double> resultMap = new LinkedHashMap<>();
        //累加
        for (Map.Entry<LocalDate, Double> localDateDoubleEntry : rateList) {
            totalNum+=localDateDoubleEntry.getValue();
            resultMap.put(localDateDoubleEntry.getKey(), totalNum);
        }
        return resultMap;
    }

    /**
     * 计算最大回撤
     * @return
     */
    public double getMaxDrawDown(){

        List<Double> rateValues = new ArrayList<>(getFieldAdjRates().values());
        double peak = -9999; //收益率最高值
        double maxBack = -9999;//最大回撤
        for (Double ratePerDay : rateValues) {
            if (ratePerDay > peak) {
                peak = ratePerDay;
            }
            double back = (peak - ratePerDay) / peak;
            if (back > maxBack) {
                maxBack = back;
            }
        }
        return maxBack;
    }

    /**
     * 计算收益率频率
     * @param holdingDayNum 持有期长度
     * @return
     */
    public Map<Double, Integer> getRateFrequency(int holdingDayNum) {

        Map<Double, Integer> result = new LinkedHashMap<>();

        double rateAPeriod = 0.0;

        List<Map.Entry<LocalDate, Double>> rateList = new ArrayList<>(fieldRates.entrySet());
        for (int i = 0; i < rateList.size(); i++) {
            rateAPeriod += rateList.get(i).getValue();
            if ((i+1) % holdingDayNum == 0) {
                if(result.containsKey(rateAPeriod)){
                    //如果重复的话 就替代
                    int num = result.get(rateAPeriod)+1;
                    result.replace(rateAPeriod, num);
                }else {
                    result.put(rateAPeriod, 1);
                }
                rateAPeriod = 0.0;
            }
        }

        if (fieldRates.size() % holdingDayNum != 0) {
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
}
