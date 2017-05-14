package cn.edu.nju.p.vo;


import cn.edu.nju.p.po.StockPO;

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
 * 
 * @author dc
 *
 */

public class StockVO {
	
//	private int serial;
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private int volume;
	private double adjClose;
	private String code;
	private String name;
	private String market;
	private double currentPrice;
	private String time;
	
	public StockVO(StockPO po){
//		this.serial=po.getSerial();
		this.date=po.getDate();
		this.open=po.getOpen();
		this.high=po.getHigh();
		this.low=po.getLow();
		this.close=po.getClose();
		this.volume=po.getVolume();
		this.adjClose=po.getAdjClose();
		this.code=po.getCode();
		this.name=po.getName();
		this.market=po.getMarket();
		this.time = po.getTime();
		this.currentPrice = po.getCurrentPrice();
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "StockVO{" +
				"date='" + date + '\'' +
				", open=" + open +
				", high=" + high +
				", low=" + low +
				", close=" + close +
				", volume=" + volume +
				", adjClose=" + adjClose +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", market='" + market + '\'' +
				", currentPrice=" + currentPrice +
				", time='" + time + '\'' +
				'}';
	}
}
