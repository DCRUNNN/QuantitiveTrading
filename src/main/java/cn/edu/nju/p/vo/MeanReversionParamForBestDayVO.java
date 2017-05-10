package cn.edu.nju.p.vo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-4-15.
 */
public class MeanReversionParamForBestDayVO {

    private List<String> stockPool;

    private LocalDate beginDate;

    private LocalDate endDate;

    private int holdingNum; //每次持有的股票数目

    private int meanDayNum; //使用几日均线作为标准

    /**
     * 自己输入股票池
     * @param stockPool
     * @param beginDate
     * @param endDate
     * @param holdingNum
     * @param meanDayNum
     */
    public MeanReversionParamForBestDayVO(List<String> stockPool, LocalDate beginDate, LocalDate endDate, int holdingNum, int meanDayNum) {
        this.stockPool = stockPool;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.holdingNum = holdingNum;
        this.meanDayNum = meanDayNum;
    }

    /**
     * 使用默认的股票池
     * @param beginDate
     * @param endDate
     * @param holdingNum
     * @param meanDayNum
     */
    public MeanReversionParamForBestDayVO(LocalDate beginDate, LocalDate endDate, int holdingNum, int meanDayNum) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.holdingNum = holdingNum;
        this.meanDayNum = meanDayNum;
    }

    public List<String> getStockPool() {
        return stockPool;
    }

    public void setStockPool(List<String> stockPool) {
        this.stockPool = stockPool;
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

    public int getHoldingNum() {
        return holdingNum;
    }

    public void setHoldingNum(int holdingNum) {
        this.holdingNum = holdingNum;
    }

    public int getMeanDayNum() {
        return meanDayNum;
    }

    public void setMeanDayNum(int meanDayNum) {
        this.meanDayNum = meanDayNum;
    }

    @Override
    public String toString() {
        return "MeanReversionParamForBestDayVO{" +
                "stockPool=" + stockPool +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", holdingNum=" + holdingNum +
                ", meanDayNum=" + meanDayNum +
                '}';
    }
}
