package cn.edu.nju.p.po;

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

public class KLinePO {
	
	private String name;
    private String code;
    private LocalDate date;
    private boolean isGoHigh;
    private double low;
    private double high;
    private double open;
    private double close;
    private double range;
    private double dBetweenOpenAndClose;
    private int volume;

    public KLinePO(String name,String code, LocalDate date, boolean isGoHigh, double low, double high, double open, double close, double range, double dBetweenOpenAndClose,int volume){
    	this.name = name;
        this.code = code;
        this.date = date;
        this.isGoHigh = isGoHigh;
        this.low = low;
        this.high = high;
        this.open = open;
        this.close = close;
        this.range = range;
        this.dBetweenOpenAndClose = dBetweenOpenAndClose;
        this.volume = volume;
    }
    
    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getLow() {
        return low;
    }
    
    public double getHigh() {
        return high;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public String getCode() {
        return code;
    }

    public boolean isGoHigh() {
        return isGoHigh;
    }

    public double getRange() {
        return range;
    }

    public double getdBetweenOpenAndClose() {
        return dBetweenOpenAndClose;
    }

    public int getVolume(){
        return this.volume;
    }
    
    @Override
    public String toString() {
        return "KLinePO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", isGoHigh=" + isGoHigh +
                ", low=" + low +
                ", high=" + high +
                ", open=" + open +
                ", close=" + close +
                ", range=" + range +
                ", dBetweenOpenAndClose=" + dBetweenOpenAndClose +
                ", volume=" + volume +
                '}';
    }
    
}
