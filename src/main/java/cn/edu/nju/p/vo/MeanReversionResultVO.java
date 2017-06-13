package cn.edu.nju.p.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 均线策略的收益结果
 */
public class MeanReversionResultVO implements Serializable{

    private List<Double> primaryRates;
    private List<Double> yieldRates;

    private List<String> dateList;

    private Map<Double,Integer> rateFrequency; //收益率频数

    private double beta ;
    private double alpha;
    private double shapeRatio;
    private double maxDrawnDown;
    private double yearYield;
    private double primaryYearYield;

    public MeanReversionResultVO(List<Double> primaryRates, List<Double> yieldRates, List<String> dateList, Map<Double, Integer> rateFrequency, double beta, double alpha, double shapeRatio, double maxDrawnDown, double yearYield, double primaryYearYield) {
        this.primaryRates = primaryRates;
        this.yieldRates = yieldRates;
        this.dateList = dateList;
        this.rateFrequency = rateFrequency;
        this.beta = beta;
        this.alpha = alpha;
        this.shapeRatio = shapeRatio;
        this.maxDrawnDown = maxDrawnDown;
        this.yearYield = yearYield;
        this.primaryYearYield = primaryYearYield;
    }

    public List<Double> getPrimaryRates() {
        return primaryRates;
    }

    public void setPrimaryRates(List<Double> primaryRates) {
        this.primaryRates = primaryRates;
    }

    public List<Double> getYieldRates() {
        return yieldRates;
    }

    public void setYieldRates(List<Double> yieldRates) {
        this.yieldRates = yieldRates;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public Map<Double, Integer> getRateFrequency() {
        return rateFrequency;
    }

    public void setRateFrequency(Map<Double, Integer> rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getShapeRatio() {
        return shapeRatio;
    }

    public void setShapeRatio(double shapeRatio) {
        this.shapeRatio = shapeRatio;
    }

    public double getMaxDrawnDown() {
        return maxDrawnDown;
    }

    public void setMaxDrawnDown(double maxDrawnDown) {
        this.maxDrawnDown = maxDrawnDown;
    }

    public double getYearYield() {
        return yearYield;
    }

    public void setYearYield(double yearYield) {
        this.yearYield = yearYield;
    }

    public double getPrimaryYearYield() {
        return primaryYearYield;
    }

    public void setPrimaryYearYield(double primaryYearYield) {

        this.primaryYearYield = primaryYearYield;
    }

    @Override
    public String toString() {
        return "MeanReversionResultVO{" +
                "primaryRates=" + primaryRates +
                ", yieldRates=" + yieldRates +
                ", dateList=" + dateList +
                ", rateFrequency=" + rateFrequency +
                ", beta=" + beta +
                ", alpha=" + alpha +
                ", shapeRatio=" + shapeRatio +
                ", maxDrawnDown=" + maxDrawnDown +
                ", yearYield=" + yearYield +
                ", primaryYearYield=" + primaryYearYield +
                '}';
    }
}
