package cn.edu.nju.p.strategy;

import java.util.List;

/**
 * Created by Xihao on 2017/6/14.
 */
public class StrategyResultVO {

    private List<Double> primaryRates;
    private List<Double> yieldRates;
    private List<String> dateList;

    private double beta ;
    private double alpha;
    private double shapeRatio;
    private double maxDrawnDown;
    private double yearYield;
    private double primaryYearYield;

    public StrategyResultVO(List<Double> primaryRates, List<Double> yieldRates, List<String> dateList, double beta, double alpha, double shapeRatio, double maxDrawnDown, double yearYield, double primaryYearYield) {
        this.primaryRates = primaryRates;
        this.yieldRates = yieldRates;
        this.dateList = dateList;
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
        return "StrategyResultVO{" +
                "primaryRates=" + primaryRates +
                ", yieldRates=" + yieldRates +
                ", dateList=" + dateList +
                ", beta=" + beta +
                ", alpha=" + alpha +
                ", shapeRatio=" + shapeRatio +
                ", maxDrawnDown=" + maxDrawnDown +
                ", yearYield=" + yearYield +
                ", primaryYearYield=" + primaryYearYield +
                '}';
    }
}
