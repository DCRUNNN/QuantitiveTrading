package cn.edu.nju.p.dao;

/**
 * Created by dell- on 2017/5/13.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetDataFromYahooUtil {

    public static final String YAHOO_FINANCE_URL = "http://ichart.yahoo.com/table.csv?";
    public static final String YAHOO_FINANCE_URL_TODAY = "http://download.finance.yahoo.com/d/quotes.csv?";

    /**
     * 根据 股票编码、开始日期、结束日期 获取股票数据
     *
     * @param stockName  沪市：000000.ss 深市：000000.sz
     * @param fromDate    开始日期
     * @param toDate         结束日期
     * @return List<StockData>
     */
    public static List<StockData> getStockCsvData(String stockName, String fromDate,String toDate) {
        List<StockData> list = new ArrayList<StockData>();
        String[] datefromInfo= fromDate.split("-");
        String[] toDateInfo = toDate.split("-");
        String code = stockName.substring(0, 6);;

        String a = (Integer.valueOf(datefromInfo[1])-1)+"";// a – 起始时间，月
        String b = datefromInfo[2];// b – 起始时间，日
        String c =  datefromInfo[0];// c – 起始时间，年
        String d = (Integer.valueOf(toDateInfo[1])-1)+"";// d – 结束时间，月
        String e = toDateInfo[2];// e – 结束时间，日
        String f =  toDateInfo[0];// f – 结束时间，年

        String params = "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e="
                + e + "&f=" + f;
        String url = YAHOO_FINANCE_URL + "s=" + stockName + params;
        System.out.println(url);

        URL MyURL = null;
        URLConnection con = null;
        InputStreamReader ins = null;
        BufferedReader in = null;

        try {
            MyURL = new URL(url);
            con = MyURL.openConnection();
//            HttpURLConnection conn=(HttpURLConnection) MyURL.openConnection();
//            con.setRequestProperty("accept", "*/*");
//            con.setRequestProperty("connection", "Keep-Alive");
//            con.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            //建立连接
//            con.connect();
//            conn.connect();
//            conn.getResponseCode();

            ins = new InputStreamReader(con.getInputStream(), "UTF-8");
            in = new BufferedReader(ins);

            System.out.println(MyURL == null);
            System.out.println(con == null);
            System.out.println(ins == null);
            System.out.println(in == null);
            System.out.println("here1");
            String newLine = in.readLine();// 标题行
            System.out.println(newLine);
            System.out.println(in.readLine());
            System.out.println("here2");
            while ((newLine = in.readLine()) != null) {
                String stockInfo[] = newLine.trim().split(",");
                StockData sd = new StockData();
                sd.setCode(code);
                sd.setDate(stockInfo[0]);
                sd.setOpen(Float.valueOf(stockInfo[1]));
                sd.setHigh(Float.valueOf(stockInfo[2]));
                sd.setLow(Float.valueOf(stockInfo[3]));
                sd.setClose(Float.valueOf(stockInfo[4]));
                sd.setVolume(Float.valueOf(stockInfo[5]));
                sd.setAdj(Float.valueOf(stockInfo[6]));
                list.add(sd);
            }
            if (in != null)  try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            return null; //无交易数据
        } finally {

        }
        return list;
    }
    /**
     * 根据 股票编码、日期 获取股票数据
     *
     * @param stockName   沪市：000000.ss 深市：000000.sz
     * @param date 日期
     * @return StockData
     */
    public static StockData getStockCsvData(String stockName, String date){
        List<StockData> list = getStockCsvData(stockName,date,date);
        return ((list.size()>0)?list.get(0):null);
    }
    /**
     * 根据 股票编码 获取当天股票数据
     *
     * @param stockName   沪市：000000.ss 深市：000000.sz
     * @return StockData
     */
    public static StockData getStockCsvData(String stockName){
        String date = String.format("%1$tF", new Date());
        List<StockData> list = getStockCsvData(stockName,date,date);
        return ((list!=null&&list.size()>0)?list.get(0):null);
    }
}
