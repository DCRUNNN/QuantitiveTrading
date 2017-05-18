package cn.edu.nju.p.vo;

import cn.edu.nju.p.po.KLinePO;

import java.time.LocalDate;


/**
 * KLine需要使用到的VO
 * name 股票名称
 * code 股票代码
 * date 日期
 * isGoHigh 是否走高
 * low 最低指数
 * high 最高指数
 * open 开盘指数
 * close 收盘指数
 * range 最高指数与最低指数之差
 * dBetweenOpenAndClose 开盘收盘指数之差的绝对值
 */
public class KLineVO {

    private String name;
    private String code;
    private String date;
    private int tag; //to show stock go high or not,go high is 1,and go lower is 0
    private double low;
    private double high;
    private double open;
    private double close;
    private double range;
    private double dBetweenOpenAndClose;
    private int volume;

    private double macd;
    private double diff;
    private double dea;

    public KLineVO(String name, String code, String date, int tag, double low, double high, double open, double close, double range, double dBetweenOpenAndClose, int volume, double macd, double diff, double dea) {
        this.name = name;
        this.code = code;
        this.date = date;
        this.tag = tag;
        this.low = low;
        this.high = high;
        this.open = open;
        this.close = close;
        this.range = range;
        this.dBetweenOpenAndClose = dBetweenOpenAndClose;
        this.volume = volume;
        this.macd = macd;
        this.diff = diff;
        this.dea = dea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getdBetweenOpenAndClose() {
        return dBetweenOpenAndClose;
    }

    public void setdBetweenOpenAndClose(double dBetweenOpenAndClose) {
        this.dBetweenOpenAndClose = dBetweenOpenAndClose;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    @Override
    public String toString() {
        return "KLineVO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", tag=" + tag +
                ", low=" + low +
                ", high=" + high +
                ", open=" + open +
                ", close=" + close +
                ", range=" + range +
                ", dBetweenOpenAndClose=" + dBetweenOpenAndClose +
                ", volume=" + volume +
                ", macd=" + macd +
                ", diff=" + diff +
                ", dea=" + dea +
                '}';
    }
}
