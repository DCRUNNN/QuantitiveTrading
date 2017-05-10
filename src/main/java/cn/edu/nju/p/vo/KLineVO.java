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
    private LocalDate date;
    private boolean isGoHigh;
    private double low;
    private double high;
    private double open;
    private double close;
    private double range;
    private double dBetweenOpenAndClose;
    private int volume;

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * 获得最低指数
     * @return 最低指数
     */
    public double getLow() {
        return low;
    }

    /**
     * 获得最高指数
     * @return 最高指数
     */
    public double getHigh() {
        return high;
    }

    /**
     * 获得开盘指数
     * @return 开盘指数
     */
    public double getOpen() {
        return open;
    }

    /**
     * 获得收盘指数
     * @return 收盘指数
     */
    public double getClose() {
        return close;
    }

    /**
     * 获得股票代码
     * @return 股票代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 判断股票当天是不是走高
     * @return 股票走高返回true，否则false
     */
    public boolean isGoHigh() {
        return isGoHigh;
    }

    /**
     * 获得当天的最高指数和最低指数之差
     * @return 当天的最高指数和最低指数之差
     */
    public double getRange() {
        return range;
    }

    /**
     * 获得开盘指数和收盘指数的差的绝对值
     * @return 开盘指数和收盘指数的差的绝对值
     */
    public double getdBetweenOpenAndClose() {
        return dBetweenOpenAndClose;
    }

    /**
     * 获得当天的所有成交量
     * @return 当天的所有成交量
     */
    public int getVolume(){
        return this.volume;
    }

    /**
     *
     * @param code 股票代码
     * @param date 日期
     * @param isGoHigh 是否走高
     * @param low 最低指数
     * @param high 最高指数
     * @param open 开盘指数
     * @param close 收盘指数
     * @param range 最高指数和最低指数之差
     * @param dBetweenOpenAndClose 开盘指数和收盘指数的差的绝对值
     * @param volume 成交量
     */
    public KLineVO(String name,String code, LocalDate date, boolean isGoHigh, double low, double high, double open, double close, double range, double dBetweenOpenAndClose,int volume) {
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
    
    public KLineVO(KLinePO po){
    	 this.name = po.getName();
         this.code = po.getCode();
         this.date = po.getDate();
         this.isGoHigh = po.isGoHigh();
         this.low = po.getLow();
         this.high = po.getHigh();
         this.open = po.getOpen();
         this.close = po.getClose();
         this.range = po.getRange();
         this.dBetweenOpenAndClose = po.getdBetweenOpenAndClose();
         this.volume = po.getVolume();
    }

    @Override
    public String toString() {
        return "KLineVO{" +
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
