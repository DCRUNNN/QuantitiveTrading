package cn.edu.nju.p.dao;

import cn.edu.nju.p.QuantradingApplication;
import cn.edu.nju.p.po.StockPO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * Created by dell- on 2017/5/12.
 */
public class MysqlHelperImpl implements MysqlHelper {

    private static final String PATH="d:\\\\StockData_";
    private static final MysqlHelperImpl helper=new MysqlHelperImpl();

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(QuantradingApplication.class);
    StockDao stockDao = annotationConfigApplicationContext.getBean(StockDao.class);

    @Override
    public void getDataFromCSV(String year) throws SQLException{
        String path = PATH + year + ".csv";
        String encoding="GBK";

        FileInputStream is = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        int count = 0;
        try {
            File file = new File(path);
//            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            is = new FileInputStream(file);
            reader = new InputStreamReader(is, encoding);
            br = new BufferedReader(reader);
            String content = br.readLine();
            content = br.readLine();//读取文件标题
            while (content != null) {
                String[] data = content.split(",");
                String date = data[0];
                double open = setPrecision(data[1]);
                double high = setPrecision(data[2]);
                double low = setPrecision(data[3]);
                double close = setPrecision(data[4]);
                int volume = Integer.parseInt(data[5]);
                double adjClose = setPrecision(data[6]);
                String code = MysqlHelperImpl.adjustCode(data[7]);
                String name = MysqlHelperImpl.adjustName(data[8]);
                String market = data[9];

                StockPO stockPO = new StockPO(date, open, high, low, close, volume, adjClose, code, name, market,"",0.0);
                try {
                    helper.insertIntoDataBase(year, stockPO);
                    System.out.println(stockPO.getName() + "已经写入数据库");
                    count++;
                    System.out.println(count);

                } catch (SQLException ex) {
                    System.out.println(stockPO.getName()+" "+stockPO.getCode()+" 没有写入数据库");
                }

                content=br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
               try{
                   br.close();
               }catch (IOException ex){
                   ex.printStackTrace();
               }
            }
        }
    }

    @Override
    public void insertIntoDataBase(String year, StockPO po) throws SQLException {
        try {
            stockDao.insertIntoStockDatabase(year,po);
        } catch (SQLException e) {
            System.out.println(po.getName()+" "+po.getCode()+" 没有写入数据库");

        }

    }

    public static String adjustName(String name){
        String newName=name.replaceAll(" ", "");
        if(newName.contains("Ａ")){
            String newStr=newName.replace('Ａ', 'A');
            return newStr;
        }
        return newName;
    }

    public static String adjustCode(String code){
        String result=code;
        int len=code.length();
        int zero=6-len;
        for(int i=0;i<zero;i++){
            result="0"+result;
        }
        return result;
    }

    public double setPrecision(String value){
        double data=Double.parseDouble(value);
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(data));
    }

    public static void main(String[] args) {
        String year = "2012";
        try {
            helper.getDataFromCSV(year);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
