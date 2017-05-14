package cn.edu.nju.p.dao;

/**
 * Created by dell- on 2017/5/13.
 */

public class TestGetDataFromYahooUtil {
    public static void main(String[] args){
        GetDataFromYahooUtil stockUtil = new GetDataFromYahooUtil();
        StockData sd = stockUtil.getStockCsvData("000001.sz", "2017-05-12");
        System.out.println(sd==null);
    }

}
