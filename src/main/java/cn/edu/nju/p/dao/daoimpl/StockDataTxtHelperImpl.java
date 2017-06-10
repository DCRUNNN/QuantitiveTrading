package cn.edu.nju.p.dao.daoimpl;

import cn.edu.nju.p.dao.StockDataTxtHelper;
import cn.edu.nju.p.po.StockPO;
import sun.security.jca.GetInstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by dell- on 2017/6/10.
 */
public class StockDataTxtHelperImpl implements StockDataTxtHelper {

    private static final String FILE_PATH = "D://youkuang//";

    private static StockDataTxtHelperImpl INSTANCE = new StockDataTxtHelperImpl();

    public static StockDataTxtHelperImpl getInstance() {
        return INSTANCE;
    }

    /**
     * 外层的String是股票代码 000001
     * 内层的String是日期 例如2016-01-01
     *
     * @return
     */
    @Override
    public Map<String,Map<String,StockPO>> getStockData(String year) {
        File file = new File(FILE_PATH + year + ".csv");
        Map<String, Map<String, StockPO>> outMap = new HashMap<String, Map < String, StockPO >>();
        Map<String, StockPO> inMap = new HashMap<String, StockPO>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader br = new BufferedReader(reader);

            DecimalFormat df = new DecimalFormat("0.00%");

            String str = br.readLine(); //读取标题
            str = br.readLine();
            String flag = str.split(",")[1].substring(0,6);//flag用来判断是否读到新的股票

            while (str != null) {
                String[] data = str.split(",");
                String code = data[1].substring(0, 6); //代码
                if (flag.equals(code)) {//相等的话说明是同一只股票
                    String date = formatDate(data[0]) ; //日期
                    String name = data[2]; //股票名称
                    double open = Double.parseDouble(data[3]); //开盘价
                    double high = Double.parseDouble(data[4]); //最高价
                    double low = Double.parseDouble(data[5]); //最低价
                    double close = Double.parseDouble(data[6]); //收盘价
                    double lastClose = Double.parseDouble(data[7]); //昨日收盘价
                    long volume = Long.parseLong(data[8]); //成交量
                    String quoteChange = df.format(Double.parseDouble(data[9])); //涨跌幅
                    boolean isOpen = Integer.parseInt(data[10]) == 1; //是否开盘
                    StockPO po = new StockPO(date, open, high, low, close, volume, close, code, name, "SZ", quoteChange, isOpen,lastClose);
                    inMap.put(date, po);
                }else{
                    outMap.put(flag, inMap); //把上一支股票插到外层map里
                    flag=code; //现在flag是新的股票的代码

                    inMap = new HashMap<>(); //很关键 但是速度减慢了

                    //inMap.clear()的话会把outMap里的已有数据清空

                    String date = formatDate(data[0]) ; //日期
                    String name = data[2]; //股票名称
                    double open = Double.parseDouble(data[3]); //开盘价
                    double high = Double.parseDouble(data[4]); //最高价
                    double low = Double.parseDouble(data[5]); //最低价
                    double close = Double.parseDouble(data[6]); //收盘价
                    double lastClose = Double.parseDouble(data[7]); //昨日收盘价
                    long volume = Long.parseLong(data[8]); //成交量
                    String quoteChange = df.format(Double.parseDouble(data[9])); //涨跌幅
                    boolean isOpen = Integer.parseInt(data[10]) == 1; //是否开盘
                    StockPO po2 = new StockPO(date, open, high, low, close, volume, close, code, name, "SZ", quoteChange, isOpen,lastClose);
                    inMap.put(date, po2);
                }
                str = br.readLine();
            }

            br.close();

            return outMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String formatDate(String date) {
        String[] data = date.split("/");
        if (data[1].length() == 1) {
            data[1] = "0" + data[1];
        }
        if (data[2].length() == 1) {
            data[2] = "0" + data[2];
        }
        return data[0] + "-" + data[1] + "-" + data[2];
    }

    public static void main(String[] args) {
        StockDataTxtHelperImpl test = new StockDataTxtHelperImpl();
        Map<String, Map<String, StockPO>> map = test.getStockData("2017");
        System.out.println(map);
//        LocalDate date = LocalDate.of(2017, 06, 05);
//        System.out.println(map.get("000001").get(date));
//        Set<String> set1 = map.keySet();
//        for (String str : set1) {
//            if (str.equals("000001")) {
//                Map<String, StockPO> inHashMap = map.get(str);
//                Set<String> set2 = inHashMap.keySet();
//                for (String key : set2) {
//                    System.out.println("\t" + key + "：" + inHashMap.get(key));
//                }
//            }
//        }
//        Map<String, StockPO> in = map.get("000001");
//        Iterator<Map.Entry<String,StockPO>> iterator = in.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry<String,StockPO> entry = iterator.next();
//            StockPO po = entry.getValue();
//            System.out.println(po.toString());
//        }
//        System.out.println(set1.size());
//        System.out.println(map.size());
    }
}
