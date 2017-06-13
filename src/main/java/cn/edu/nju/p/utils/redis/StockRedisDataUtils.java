package cn.edu.nju.p.utils.redis;

import cn.edu.nju.p.cache.RedisCache;
import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xihao on 17-6-13.
 */
@Component
public class StockRedisDataUtils {

    private RedisCache redisCache;

    private StockDao stockDao;

    private StringRedisTemplate stringRedisTemplate;

    /**
     * 所有的股票数据,<date,jsonStock>形式存储到内存中
     */
    private Map<String, String> allStocks;

    @Autowired
    public StockRedisDataUtils(RedisCache redisCache, StockDao stockDao, StringRedisTemplate stringRedisTemplate) {

        this.redisCache = redisCache;
        this.stockDao = stockDao;
        this.stringRedisTemplate = stringRedisTemplate;
        allStocks = new HashMap<>();

//        deleteAllStockDataCache();
//        把所有的股票数据加载到redis之中
//        System.out.println("initing stock data------");
//        initAllRedisStockData();

//        初始化　allStocks Map
//        initAllStocksMap();
    }

    /**
     * 删除所有redis中的日期股票数据
     */
    private void deleteAllStockDataCache() {
        System.out.println("begin deleting keys...");
        Set<String> keys = stringRedisTemplate.keys("20*");
        for (String key : keys) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 初始化allStock Map
     * 把redis 中的股票数据全部读取到HashMap中
     */
    private void initAllStocksMap() {
        System.out.println("开始读取股票数据...");
        String jsonOfValidDates = redisCache.getCache("validDates", String.class);
        List<String> allValidDates = JSON.parseArray(jsonOfValidDates, String.class);
        for (String validDate : allValidDates) {
//            System.out.println("获取"+validDate+"的数据...");
            String jsonOfStockDataMap = redisCache.getCache(validDate,String.class);
            allStocks.put(validDate, jsonOfStockDataMap);
        }
        System.out.println("读取股票数据完毕...");
    }

    /**
     * 初始化redis中某日股票数据
     * @param date
     */
    private void initRedis(LocalDate date) {
        System.out.println("initing stock data of "+date.toString()+" -------");
        List<StockPO> pos = stockDao.getPOList(date.toString());
        if (pos == null || pos.size() == 0) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        pos.forEach(po -> map.put(po.getCode(), JSON.toJSONString(po))); //获得当日的map
//        System.out.println(JSON.toJSONString(map,true));

        redisCache.putCache(date.toString(), JSON.toJSONString(map));
    }


    /**
     * 初始化redis中的所有股票数据
     */
    private void initAllRedisStockData() {
        LocalDate beginDate = LocalDate.of(2006, 1, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 12);
        while (beginDate.isBefore(endDate)) {
            initRedis(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        initRedis(endDate);
    }

    /**
     * 获取某日股票的收盘价
     * 注意确保传入的code和date 有效
     * @param code 股票代码
     * @param date　日期　
     */
    @Cacheable("getStockCloseFromRedis")
    public double getStockClose(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getClose();
    }

    @Cacheable("getStockOpenFromRedis")
    public double getStockOpen(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getOpen();
    }

    /**
     * 获取当日股票是否开盘
     * @param code 股票代码
     * @param date　日期
     * @return
     */
    @Cacheable("getStockIsOpenFromRedis")
    public boolean getStockIsOpen(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getIsOpen();
    }

    @Cacheable("getStockHighFormRedis")
    public double getStockHigh(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getHigh();
    }

    @Cacheable("getStockLowFromRedis")
    public double getStockLow(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getLow();
    }

    @Cacheable("getStockVolumeFromRedis")
    public long getStockVolume(String code, LocalDate date) {
        StockPO stockPO = getStockPO(code, date);
        return stockPO.getVolume();
    }

    @Cacheable("getStockNameFromRedis")
    public String getStockName(String code) {
        StockPO stockPO = getStockPO(code, LocalDate.of(2017,6,9));
        return stockPO.getName();
    }

    @Cacheable("getStockLastCloseFromRedis")
    public double getLastClose(String code, LocalDate date) {
        StockPO po = getStockPO(code, date);
        return po.getLastClose();
    }


    /**
     * 获取stockpo
     * @param code 股票代码
     * @param date　日期
     * @return
     */
    public StockPO getStockPO(String code, LocalDate date) {
        Map<String, String> stockDataMap = getStockDataOfDateMap(date);
        String jsonOfStock = stockDataMap.get(code);
        return JSON.parseObject(jsonOfStock, StockPO.class);
    }

    /**
     * 获取当日的所有数据map<String code,String jsonOfStockPO>
     * @param date 日期
     * @return
     */
    private Map<String, String> getStockDataOfDateMap(LocalDate date) {

        String jsonStockOfDate = allStocks.get(date.toString()); //是一个map的json string
//        System.out.println(jsonStockOfDate);
        /*
        * 获取当日的map
        * */
        return (Map<String, String>) JSON.parse(jsonStockOfDate);
    }
}
