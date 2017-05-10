package cn.edu.nju.p.po;


/**
* 某一日股票市场的需要显示的所有数据
* volume 成交量
* amountOfLimitUp 涨停股票的数目
* amountOfLimitDown 跌停股票的数目
* amountOf5PercentUp 涨幅超过5%的股票数目
* amountOf5PercentDown 跌幅超过5%的股票数目
* amountOf5PercentUptolastClose 开盘减去收盘大于5%*上个收盘指数的股票数目
* amountOf5PercentDowntolastClose 开盘减去收盘小于-5%*上个收盘指数的股票数目
*/
public class StockMarketPO {
	private long volume;
    private int amountOfLimitUp;
    private int amountOfLimitDown;
    private int amountOf5PercentUp;
    private int amountOf5PercentDown;
    private int amountOf5PercentUptolastClose;
    private int amountOf5PercentDowntolastClose;

    public long getVolume() {
        return volume;
    }

    public int getAmountOfLimitUp() {
        return amountOfLimitUp;
    }

    public int getAmountOfLimitDown() {
        return amountOfLimitDown;
    }

    public int getAmountOf5PercentUp() {
        return amountOf5PercentUp;
    }

    public int getAmountOf5PercentDown() {
        return amountOf5PercentDown;
    }

    public int getAmountOf5PercentUptolastClose() {
        return amountOf5PercentUptolastClose;
    }

    public int getAmountOf5PercentDowntolastClose() {
        return amountOf5PercentDowntolastClose;
    }

    public StockMarketPO(long volume, int amountOfLimitUp, int amountOfLimitDown, int amountOf5PercentUp, int amountOf5PercentDown, int amountOf5PercentUptolastClose, int amountOf5PercentDowntolastClose) {
        this.volume = volume;
        this.amountOfLimitUp = amountOfLimitUp;
        this.amountOfLimitDown = amountOfLimitDown;
        this.amountOf5PercentUp = amountOf5PercentUp;
        this.amountOf5PercentDown = amountOf5PercentDown;
        this.amountOf5PercentUptolastClose = amountOf5PercentUptolastClose;
        this.amountOf5PercentDowntolastClose = amountOf5PercentDowntolastClose;
    }
    
    @Override
    public String toString() {
        return "StockMarketPO{" +
                "volume=" + volume +
                ", amountOfLimitUp=" + amountOfLimitUp +
                ", amountOfLimitDown=" + amountOfLimitDown +
                ", amountOf5PercentUp=" + amountOf5PercentUp +
                ", amountOf5PercentDown=" + amountOf5PercentDown +
                ", amountOf5PercentUptolastClose=" + amountOf5PercentUptolastClose +
                ", amountOf5PercentDowntolastClose=" + amountOf5PercentDowntolastClose +
                '}';
    }
}
