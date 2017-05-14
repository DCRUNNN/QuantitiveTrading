package cn.edu.nju.p.dao;

/**
 * Created by dell- on 2017/5/13.
 */
public class StockData {

    private String code; // 股票编码
    private String name; // 股票名称
    private String date; // 交易日期
    private double open = 0.0; // 开盘价
    private double high = 0.0; // 最高价
    private double low = 0.0; // 最低价
    private double close = 0.0; // 最后一次交易价格，相当于收盘价
    private double volume = 0.0;// 总交易手
    private double adj = 0.0; // 最后一次交易价格 (今天的收盘价当做加权价格)

    public String getDate(){
        return date;
    }

    public double getOpen(){
        return open;
    }

    public double gethigh(){
        return high;
    }

    public double getLow(){
        return low;
    }

    public double getClose(){
        return close;
    }

    public double getVolume(){
        return volume;
    }

    public double getAdjClose(){
        return adj;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public void setCode(String code){
        this.code=code;
    }

    public void setDate(String date){
        this.date=date;
    }

    public void setOpen(double open){
        this.open=open;
    }

    public void setHigh(double high){
        this.high=high;
    }

    public void setLow(double low){
        this.low=low;
    }

    public void setClose(double close){
        this.close=close;
    }

    public void setVolume(double volume){
        this.volume=volume;
    }

    public void setAdj(double adj){
        this.adj=adj;
    }



    public String toString(StockData po) {
        String result="code:"+po.getCode()+" name:"+po.getName()+" date:"+po.getDate()+" open:"+po.getOpen()+" high:"+po.gethigh()
                +" low:"+po.getLow()+" close:"+po.getClose()+" volumn:"+po.getVolume()+" adj close:"+po.getAdjClose()
                ;
        return result;
    }


}
