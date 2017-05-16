package cn.edu.nju.p.dao;

import cn.edu.nju.p.QuantradingApplication;
import cn.edu.nju.p.po.StockPO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell- on 2017/5/12.
 */
public class GetDataFromSinaUtil {

    private static final String SINA_FINACE_URL="http://hq.sinajs.cn/list=";
    private static final GetDataFromSinaUtil dataHelper = new GetDataFromSinaUtil();

    public static GetDataFromSinaUtil getInstance(){
        return dataHelper;
    }//通过调用static方法来获得GetDataFromSinaUtil实例

    //获取股票实时数据
    public void getStockCurrentData(String code,String market){
        String url = SINA_FINACE_URL + market + code;
        try {
            URL u = new URL(url);
            byte[] b = new byte[256];
            InputStream in = null;
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
//            while (true) {
            try {
                in = u.openStream();
                int i;
                while ((i = in.read(b)) != -1) {
                    bo.write(b, 0, i);
                }
                String result = new String(bo.toByteArray(),"gbk");
                String[] stocks = result.split(";");
                for (String stock : stocks) {
                    System.out.println(stock);
                    String[] datas = stock.substring(21,stock.length()-1).split(",");
                    if(datas.length==0){
                        FileWriter fileWriter=null;
                        BufferedWriter bw=null;
                        try {
                            fileWriter = new FileWriter("D://error.txt", true);
                            bw = new BufferedWriter(fileWriter);
                            bw.write(code);
                            bw.newLine();
                            bw.flush();
                            bw.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }  finally {

                        }
                    }
                    //获取需要的数据
                    String name = datas[0];
                    double open = Double.valueOf(datas[1]);//今日开盘价
                    double close = Double.valueOf(datas[2]);//昨日收盘价
                    double currentprice = Double.valueOf(datas[3]);//当前价格
                    double high = Double.valueOf(datas[4]);//今日最高价（截至目前）
                    double low = Double.valueOf(datas[5]);//今日最低价
                    int volume = Integer.valueOf(datas[8]);//成交量
                    double turnover = Double.valueOf(datas[9]);//成交金额
                    String date = datas[30];//日期
                    String time = datas[31];//时间

                    //写入数据库
                    StockPO po = new StockPO(date, open, high, low, close, volume, close, code, name, market, time, currentprice);
                    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(QuantradingApplication.class);
                    StockDao stockDao = annotationConfigApplicationContext.getBean(StockDao.class);
                    stockDao.insertIntoStockDatabase("2017",po);
                }
                bo.reset();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                if (in != null) {
                    in.close();
                }
            }
//            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {

        GetDataFromSinaUtil.getInstance().getStockCurrentData("002204", "sz");
    }

}

