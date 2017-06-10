package cn.edu.nju.p.dao.daoimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.jca.GetInstance;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell- on 2017/6/9.
 */
//@Component
public class StockDaoImpl implements StockDao {

    @Autowired
    StockDao stockDao;

    /**
     * 外层的String是股票代码 000001
     * 内层的String是日期 例如2016-01-01
     *
     */
    private static Map<String, Map<String, StockPO>> map2017;
    private static Map<String, Map<String, StockPO>> map2016;
    private static Map<String, Map<String, StockPO>> map2015;
    private static Map<String, Map<String, StockPO>> map2014;
    private static Map<String, Map<String, StockPO>> map2013;
    private static Map<String, Map<String, StockPO>> map2012;
    private static Map<String, Map<String, StockPO>> map2011;
    private static Map<String, Map<String, StockPO>> map2010;
    private static Map<String, Map<String, StockPO>> map2009;
    private static Map<String, Map<String, StockPO>> map2008;
    private static Map<String, Map<String, StockPO>> map2007;
    private static Map<String, Map<String, StockPO>> map2006;
    private static Map<String, Map<String, StockPO>> map2005;
    private static StockDataTxtHelperImpl helper = StockDataTxtHelperImpl.getInstance();

    public StockDaoImpl() {
        long a=System.currentTimeMillis();
        map2005 = helper.getStockData("2005");
        map2006 = helper.getStockData("2006");
        map2007 = helper.getStockData("2007");
        map2008 = helper.getStockData("2008");
        map2009 = helper.getStockData("2009");
        map2010 = helper.getStockData("2010");
        map2011 = helper.getStockData("2011");
        map2012 = helper.getStockData("2012");
        map2013 = helper.getStockData("2013");
        map2014 = helper.getStockData("2014");
        map2015 = helper.getStockData("2015");
        map2016 = helper.getStockData("2016");
        map2017 = helper.getStockData("2017");
        System.out.println("初始化耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
    }

    private static Map<String, Map<String, StockPO>> getMap(LocalDate date) {
        int year = date.getYear();
        switch (year) {
            case 2005:
                return map2005;
            case 2006:
                return map2006;
            case 2007:
                return map2007;
            case 2008:
                return map2008;
            case 2009:
                return map2009;
            case 2010:
                return map2010;
            case 2011:
                return map2011;
            case 2012:
                return map2012;
            case 2013:
                return map2013;
            case 2014:
                return map2014;
            case 2015:
                return map2015;
            case 2016:
                return map2016;
            case 2017:
                return map2017;
            default:
                break;
        }
        return null;
    }

    @Override
    public StockPO getStockPO(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString());
    }

    @Override
    public Double getStockOpen(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getOpen();
    }

    @Override
    public Double getStockHigh(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getHigh();
    }

    @Override
    public Double getStockLow(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getLow();
    }

    @Override
    public Double getStockClose(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getClose();
    }

    @Override
    public Long getStockVolume(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getVolume();
    }

    @Override
    public Double getStockAdjClose(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getAdj_close();
    }

    @Override
    public Boolean getStockIsOpen(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getIsOpen();
    }

    @Override
    public String getStockQuoteChange(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getQuote_change();
    }

    @Override
    public Double getStockLastClose(@Param("code") String code, @Param("date") LocalDate date) {
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        return map.get(code).get(date.toString()).getLastClose();
    }

    @Override
    public String getStockName(String code) {
        LocalDate date = LocalDate.of(2017, 06, 01);
        return map2017.get(code).get(date.toString()).getName();
    }

    @Override
    public String getStockMarket(String code) {
        LocalDate date = LocalDate.of(2017, 06, 01);
        return map2017.get(code).get(date.toString()).getMarket();
    }

    @Override
    public String getStockCode(String name) {
        Set<Map.Entry<String, Map<String, StockPO>>> entryseSet=map2017.entrySet();
        for (Map.Entry<String, Map<String, StockPO>> set : entryseSet) {
            Map<String,StockPO> inMap=set.getValue();
            Set<Map.Entry<String, StockPO>> inSet = inMap.entrySet();
            for(Map.Entry<String, StockPO> set2 : inSet) {
                if (set2.getValue().getName().equals(name)) {
                    return set2.getValue().getCode();
                }
            }
        }
        return null;
    }

    @Override
    public List<StockPO> getPOList(@Param("date") String date) {
        List<StockPO> poList = new ArrayList<>();
        LocalDate localDate = LocalDate.parse(date);
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(localDate);

        Set<Map.Entry<String, Map<String, StockPO>>> entrySet=map.entrySet();
        for (Map.Entry<String, Map<String, StockPO>> set : entrySet) {
            Map<String,StockPO> inMap=set.getValue();
            Set<Map.Entry<String, StockPO>> inSet = inMap.entrySet();
            for(Map.Entry<String, StockPO> set2 : inSet) {
                if (set2.getKey().equals(date)) {
                    poList.add(set2.getValue());
                }
            }
        }
        return poList;
    }

    @Override
    public List<String> getAllStocks() {
        LocalDate date = LocalDate.of(2017, 06, 01);
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date);
        Set<String> codeSet = map.keySet();
        List<String> codeList = new ArrayList<>(codeSet);
        return codeList;

    }

    @Override
    public String getStockSector(String code) {
        return null;
    }

    @Override
    public List<String> getStockBySector(String sector) {
        return null;
    }

    @Override
    public void insertIntoStockDatabase(@Param("year") String year, @Param("StockPO") StockPO po) throws SQLException {
        int count=1;
        try {
            stockDao.insertIntoStockDatabase(year,po);
            System.out.println(po.getName() + "已经写入数据库");
                    count++;
//                    System.out.println(count);
        } catch (SQLException ex) {
            System.out.println(po.getName()+" "+po.getCode()+" 没有写入数据库");
        }
        System.out.println(count);
    }



    public static void main(String[] args) {
//        long a=System.currentTimeMillis();
//        LocalDate date = LocalDate.of(2015, 9, 25);
        StockDaoImpl impl=new StockDaoImpl();


//        System.out.println("初始化耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
    }
}
