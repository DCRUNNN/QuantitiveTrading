package cn.edu.nju.p.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-4-6.
 */
public class MeanReversionParamVO implements Serializable{

    private List<String> stockPool;

    private int holdingDay; //持有天数

    private int holdingStockNum; //每次买入的股票数量

    private LocalDate beginDate;

    private LocalDate endDate;

    /**
     * 确定几日均线
     */
    private int meanDayNum;

    /**
     * 自己实现了股票池
     * @param stockPool 股票池
     * @param holdingDay 每次持仓期
     * @param holdingStockNum 每次持有的股票数目
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param meanDayNum 选择是五日均线还是10,20,30
     */
    public MeanReversionParamVO(List<String> stockPool, int holdingDay, int holdingStockNum, LocalDate beginDate, LocalDate endDate, int meanDayNum) {
        this.stockPool = stockPool;
        this.holdingDay = holdingDay;
        this.holdingStockNum = holdingStockNum;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.meanDayNum = meanDayNum;
    }

    /**
     * 没有输入股票池，默认使用所有的股票作为股票池
     * @param holdingDay 每次持仓期
     * @param holdingStockNum 每次持有的股票数目
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param meanDayNum 选择是五日均线还是10,20,30
     */
    public MeanReversionParamVO(int holdingDay, int holdingStockNum, LocalDate beginDate, LocalDate endDate, int meanDayNum) {
        this.holdingDay = holdingDay;
        this.holdingStockNum = holdingStockNum;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.meanDayNum = meanDayNum;
    }

    public List<String> getStockPool() {
        return stockPool;
    }

    public void setStockPool(List<String> stockPool) {
        this.stockPool = stockPool;
    }

    public int getHoldingDay() {
        return holdingDay;
    }

    public void setHoldingDay(int holdingDay) {
        this.holdingDay = holdingDay;
    }

    public int getHoldingStockNum() {
        return holdingStockNum;
    }

    public void setHoldingStockNum(int holdingStockNum) {
        this.holdingStockNum = holdingStockNum;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getMeanDayNum() {
        return meanDayNum;
    }

    public void setMeanDayNum(int meanDayNum) {
        this.meanDayNum = meanDayNum;
    }

    @Override
    public String toString() {
        return "MeanReversionParamVO{" +
                "stockPool=" + stockPool +
                ", holdingDay=" + holdingDay +
                ", holdingStockNum=" + holdingStockNum +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", meanDayNum=" + meanDayNum +
                '}';
    }
}
