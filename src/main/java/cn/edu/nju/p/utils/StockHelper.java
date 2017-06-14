package cn.edu.nju.p.utils;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.utils.holiday.Holidays;
import cn.edu.nju.p.utils.redis.StockRedisDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see StockDao 调用股票数据层接口，对股票进行一些逻辑处理
 */
@Component
public class StockHelper {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private Holidays holidays;

    private final String VERTIFICATION = "000002";

    @Autowired
    private StockRedisDataUtils redisDataUtils;

    /**
     * 根据股票代码判断这个日期是否存在数据
     *
     * @param stockCode
     * @param date
     * @return 存在数据返回true，不存在数据返回false
     */
    @Cacheable("checkValid")
    public boolean isValidByCode(String stockCode, LocalDate date) {

        return !(holidays.isHoliday(date) || !stockDao.getStockIsOpen(stockCode, date) || stockDao.getStockPO(stockCode, date) == null);
    }

    /**
     * 获得包括当前日期在内的　第一个有效日期，当前日期无效的话找到上一个有效日期
     * @param stockCode
     * @param date
     * @return
     */
    public LocalDate getFirstValidDate(String stockCode, LocalDate date) {
        while (!isValidByCode(stockCode, date)) {
            date = date.minusDays(1);
        }
        return date;
    }


    /**
     * 判断股票在指定日期之内是否存在停牌状况
     * @param stockCode 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 存在停牌情况返回true，不存在返回false
     */
    @Cacheable("hasStopped")
    public boolean hasStopped(String stockCode, LocalDate beginDate, LocalDate endDate) {

        LocalDate currentDate = LocalDate.now();
        if (endDate.plusDays(10).isBefore(currentDate)) {
            //离当前日期较远的话
            List<LocalDate> betweenDates = holidays.getBetweenDatesAndFilter(beginDate, endDate, a -> true);
            for (LocalDate date : betweenDates) {
                if (redisDataUtils.getStockPO(stockCode, date) == null || !redisDataUtils.getStockIsOpen(stockCode, date)) {
                    System.out.println(redisDataUtils.getStockPO(stockCode,date));
                    return true;
                }
            }
            return false;
        } else {
            List<LocalDate> betweenDates = holidays.getBetweenDatesAndFilter(beginDate, endDate, a -> true);
            for (LocalDate date : betweenDates) {
                if (stockDao.getStockPO(stockCode, date) == null || !stockDao.getStockIsOpen(stockCode, date)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 过滤股票池
     * @param stockPool 股票池
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 过滤之后的股票池
     */
    @Cacheable("filterStockPool")
    public List<String> filterStockPool(List<String> stockPool,LocalDate beginDate,LocalDate endDate){

        return stockPool
                .parallelStream()
                .filter(stockCode -> !hasStopped(stockCode,beginDate,endDate))
                .collect(Collectors.toList());
    }

    /**
     * 根据当前日期获取推荐的股票
     * @return
     */
    public List<String> getRecommendStock() {

        List<String> allStocks = stockDao.getAllStocks();
        List<String> stockPool = new ArrayList<>();
        for (int i = 0; i < allStocks.size(); i++) {
            if (i % 100 == 0) {
                stockPool.add(allStocks.get(i));
            }
        }
        return stockPool;
    }


    /**
     * 获取股票池的基准收益率
     * @param stockPool 股票池
     * @param validDates 所有的有效日期
     * @return 返回基准收益率(累加的)
     */
    public ArrayList<List<Double>> getPrimaryRate(List<String> stockPool, List<LocalDate> validDates){

        List<Double> dailyPrimaryRates = new ArrayList<>();
        List<Double> accumulationPrimaryRates = new ArrayList<>();

        double totalClose = calculateTotalClose(stockPool, validDates.get(0)); //买入时的总收盘价
        double lastClose = totalClose;

        for (int i = 1; i < validDates.size(); i++) {
            LocalDate currentDate = validDates.get(i);
            double allClose = calculateTotalClose(stockPool, currentDate);
            //计算当日和买入日比较的总收益，并且格式化
            accumulationPrimaryRates.add(DoubleUtils.formatDouble((allClose - totalClose) / totalClose, 4));
            dailyPrimaryRates.add(DoubleUtils.formatDouble((allClose - lastClose) / lastClose, 4));
            lastClose = allClose;
        }

        ArrayList<List<Double>> twoRates = new ArrayList<>();
        twoRates.add(dailyPrimaryRates);
        twoRates.add(accumulationPrimaryRates);
        return twoRates;

    }

    /**
     * 计算所有股票当日的总收盘价
     * @param stockPool 股票池
     * @param date 日期
     * @return 总收盘价
     */
    public double calculateTotalClose(List<String> stockPool, LocalDate date) {
        double totalClose = 0.0;
        for (String code : stockPool) {

            double close;

            try {
                StockPO po = redisDataUtils.getStockPO(code, date);
                if (po.getIsOpen()) {
                    close = po.getClose();
                }else {
                    close = po.getLastClose();
                }
            } catch (NullPointerException ne) {
                close = 0;
            }

            totalClose += close;
        }
        return totalClose;
    }

    /**
     * to check if the stock exists
     * @param stockCode stock code
     * @return true if exists,else false
     */
    public boolean codeExists(String stockCode) {
        List<String> codes = stockDao.getAllStocks();
        return codes.contains(stockCode);
    }

}
