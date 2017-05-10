package cn.edu.nju.p.vo;

import java.time.LocalDate;
import java.util.List;


/**
 * Created by xihao on 17-4-3.
 */
public class MomentumParamVO {

    private LocalDate beginDate; //开始日期
    private LocalDate endDate; //结束日期
    private boolean isFormativeDay; //是否输入了形成期天数,输入的话为true，没有为false
    private int dayNum; //输入了形成期就是形成期的天数，否则为持有期天数

    private List<String> stockCodes; //自输入的股票池

    /**
     * 输入股票池的话 使用输入的股票池
     * @param beginDate
     * @param endDate
     * @param isFormativeDay
     * @param dayNum
     * @param stockCodes
     */
    public MomentumParamVO(LocalDate beginDate, LocalDate endDate, boolean isFormativeDay, int dayNum, List<String> stockCodes) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isFormativeDay = isFormativeDay;
        this.dayNum = dayNum;
        this.stockCodes = stockCodes;
    }

    /**
     * 不输入股票池的话就是使用默认的股票池
     * @param beginDate
     * @param endDate
     * @param isFormativeDay
     * @param dayNum
     */
    public MomentumParamVO(LocalDate beginDate, LocalDate endDate, boolean isFormativeDay, int dayNum) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isFormativeDay = isFormativeDay;
        this.dayNum = dayNum;
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

    public boolean isFormativeDay() {
        return isFormativeDay;
    }

    public void setFormativeDay(boolean formativeDay) {
        isFormativeDay = formativeDay;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }


    public List<String> getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(List<String> stockCodes) {
        this.stockCodes = stockCodes;
    }

    @Override
    public String toString() {
        return "MomentumParamVO{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", isFormativeDay=" + isFormativeDay +
                ", dayNum=" + dayNum +
                ", stockCodes=" + stockCodes +
                '}';
    }
}
