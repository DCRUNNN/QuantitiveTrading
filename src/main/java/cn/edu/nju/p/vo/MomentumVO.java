package cn.edu.nju.p.vo;

import java.time.LocalDate;
import java.util.List;


/**
 * 动量策略的参数
 */
public class MomentumVO {

    /**
     * 形成期天数
     */
    private int dayNumFormative;

    /**
     * 持有期天数
     */
    private int dayNumHolding;

    /**
     * 开始日期
     */
    private LocalDate beginDate;

    /**
     * 结束日期
     */
    private LocalDate endDate;

    /**
     * 输入的股票池
     */
    private List<String> stockPool;

    /**
     * 输入股票池 作为自选股票池
     * @param dayNumFormative
     * @param dayNumHolding
     * @param beginDate
     * @param endDate
     * @param stockPool
     */
    public MomentumVO(int dayNumFormative, int dayNumHolding, LocalDate beginDate, LocalDate endDate, List<String> stockPool) {
    	this.dayNumFormative = dayNumFormative;
        this.dayNumHolding = dayNumHolding;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.stockPool = stockPool;
    }

    /**
     * 不输入股票池，使用默认股票池
     * @param dayNumFormative
     * @param dayNumHolding
     * @param beginDate
     * @param endDate
     */
    public MomentumVO(int dayNumFormative, int dayNumHolding, LocalDate beginDate, LocalDate endDate) {
        this.dayNumFormative = dayNumFormative;
        this.dayNumHolding = dayNumHolding;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public int getDayNumFormative() {
        return dayNumFormative;
    }

    public void setDayNumFormative(int dayNumFormative) {
        this.dayNumFormative = dayNumFormative;
    }

    public int getDayNumHolding() {
        return dayNumHolding;
    }

    public void setDayNumHolding(int dayNumHolding) {
        this.dayNumHolding = dayNumHolding;
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

    public List<String> getStockPool() {
        return stockPool;
    }

    public void setStockPool(List<String> stockPool) {
        this.stockPool = stockPool;
    }

    @Override
    public String toString() {
        return "MomentumVO{" +
                "dayNumFormative=" + dayNumFormative +
                ", dayNumHolding=" + dayNumHolding +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", stockPool=" + stockPool +
                '}';
    }
}
