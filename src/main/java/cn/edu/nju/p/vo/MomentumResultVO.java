package cn.edu.nju.p.vo;

import java.time.LocalDate;
import java.util.Map;


/**
 * Created by xihao on 17-3-30.
 */
public class MomentumResultVO {

    private Map<LocalDate,Double> fieldRate; //策略的累计收益率

    private Map<LocalDate,Double> primaryRate; //策略的基准收益率

    private Map<Double,Integer> rateFrequency; //收益率频数

    private double beta ;
    private double alpha;
    private double shapeRatio;
    private double maxDrawnDown;
    private double yearYield;
    private double primaryYearYield;

    public MomentumResultVO(Map<LocalDate, Double> fieldRate, Map<LocalDate, Double> primaryRate, Map<Double, Integer> rateFrequency, double beta, double alpha, double shapeRatio, double maxDrawnDown, double yearYield, double primaryYearYield) {
    	this.fieldRate = fieldRate;
        this.primaryRate = primaryRate;
        this.rateFrequency = rateFrequency;
        this.beta = beta;
        this.alpha = alpha;
        this.shapeRatio = shapeRatio;
        this.maxDrawnDown = maxDrawnDown;
        this.yearYield = yearYield;
        this.primaryYearYield = primaryYearYield;
    }

    public Map<LocalDate, Double> getFieldRate() {
        return fieldRate;
    }

    public void setFieldRate(Map<LocalDate, Double> fieldRate) {
        this.fieldRate = fieldRate;
    }

    public Map<LocalDate, Double> getPrimaryRate() {
        return primaryRate;
    }

    public void setPrimaryRate(Map<LocalDate, Double> primaryRate) {
        this.primaryRate = primaryRate;
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
        return "MomentumResultVO{" +
                "fieldRate=" + fieldRate +
                ", primaryRate=" + primaryRate +
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
