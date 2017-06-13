package cn.edu.nju.p.po;

import java.io.Serializable;

/**
 * serial 记录编号
 * date 日期（月/日/年）
 * open 开盘指数
 * high 最高指数
 * low 最低指数
 * close 收盘指数
 * volume 成交量
 * adjClose 复权后的收盘指数
 * code 股票代码
 * name 股票名称
 * market 市场名称
 * time 查询时间
 * currentPrice 目前价格
 * quote_change 涨跌幅
 * turnover 成交金额
 * isopen 是否开盘
 * lastClose 昨日收盘价
 *
 * @author dc
 *
 */


public class StockPO implements Serializable{

    //	private int serial;
    private String date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private double adj_close;
    private String code;
    private String name;
    private String market;
    private String time;
    private double currentPrice;
    private String quote_change;
    private String turnover;
    private boolean isOpen;
    private double lastClose;

    public StockPO(String date, double open, double high, double low, double close, long volume, double adj_close, String code, String name, String market, String time, double currentPrice, String quote_change, String turnover, boolean isOpen, double lastClose) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adj_close = adj_close;
        this.code = code;
        this.name = name;
        this.market = market;
        this.time = time;
        this.currentPrice = currentPrice;
        this.quote_change = quote_change;
        this.turnover = turnover;
        this.isOpen = isOpen;
        this.lastClose = lastClose;
    }

    public StockPO(String date, double open, double high, double low, double close, long volume, double adj_close, String code, String name, String market, String quote_change, boolean isOpen, double lastClose) {

        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adj_close = adj_close;
        this.code = code;
        this.name = name;
        this.market = market;
        this.quote_change = quote_change;
        this.isOpen = isOpen;
        this.lastClose = lastClose;
    }

    public StockPO() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getAdj_close() {
        return adj_close;
    }

    public void setAdj_close(double adj_close) {
        this.adj_close = adj_close;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getQuote_change() {
        return quote_change;
    }

    public void setQuote_change(String quote_change) {
        this.quote_change = quote_change;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public double getLastClose() {
        return lastClose;
    }

    public void setLastClose(double lastClose) {
        this.lastClose = lastClose;
    }

    @Override
    public String toString() {
        return "StockPO{" +
                "date='" + date + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", adj_close=" + adj_close +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", market='" + market + '\'' +
                ", time='" + time + '\'' +
                ", currentPrice=" + currentPrice +
                ", quote_change='" + quote_change + '\'' +
                ", turnover='" + turnover + '\'' +
                ", isOpen=" + isOpen +
                ", lastClose=" + lastClose +
                '}';
    }
}

