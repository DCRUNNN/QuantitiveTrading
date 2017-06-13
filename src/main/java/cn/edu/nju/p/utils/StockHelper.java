package cn.edu.nju.p.utils;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @see StockDao 调用股票数据层接口，对股票进行一些逻辑处理
 */
@Component
public class StockHelper {

    private static StockDao stockDao;

    private static final String VERTIFICATION = "000002";
    
    /**
     * 根据股票代码判断这个日期是否存在数据
     *
     * @param stockCode
     * @param date
     * @return 存在数据返回true，不存在数据返回false
     */
    @Cacheable("checkValid")
    public static boolean isValidByCode(String stockCode, LocalDate date) {

        return !(isHoliday(date) || !stockDao.getStockIsOpen(stockCode, date));
    }

    /**
     * 获得包括当前日期在内的　第一个有效日期，当前日期无效的话找到上一个有效日期
     * @param stockCode
     * @param date
     * @return
     */
    public static LocalDate getFirstValidDate(String stockCode, LocalDate date) {
        while (!isValidByCode(stockCode, date)) {
            date = date.minusDays(1);
        }
        return date;
    }

    /**
     * 获取两个日期之间的所有有效日期，并且按条件进行过滤
     * @param beginDate begin date
     * @param endDate end date
     * @param filterCondition filter condition
     * @return
     */
    public static List<LocalDate> getBetweenValidDatesAndFilter(LocalDate beginDate, LocalDate endDate, Predicate<LocalDate> filterCondition) {

        List<LocalDate> dateList = new ArrayList<>();
        while (beginDate.isBefore(endDate)) {
            dateList.add(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        dateList.add(endDate);

        Predicate<LocalDate> notHoliday = localDate -> !isHoliday(localDate);

        dateList = dateList.parallelStream()
                .filter(notHoliday.and(filterCondition))
                .sorted()
                .collect(Collectors.toList());

        return dateList;
    }

    public static boolean isHoliday(LocalDate localDate) {

        return stockDao.getStockPO(VERTIFICATION, localDate) == null;
    }


    /**
     * 判断股票在指定日期之内是否存在停牌状况
     * @param stockCode 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 存在停牌情况返回true，不存在返回false
     */
    public static boolean hasStopped(String stockCode, LocalDate beginDate, LocalDate endDate) {

        List<LocalDate> betweenDates = getBetweenValidDatesAndFilter(beginDate, endDate, a -> true);
        for (LocalDate date : betweenDates) {
            if (stockDao.getStockPO(stockCode, date) == null || !stockDao.getStockIsOpen(stockCode, date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤股票池
     * @param stockPool 股票池
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 过滤之后的股票池
     */
    public static List<String> filterStockPool(List<String> stockPool,LocalDate beginDate,LocalDate endDate){

        return stockPool
                .parallelStream()
                .filter(stockCode -> !hasStopped(stockCode,beginDate,endDate))
                .collect(Collectors.toList());
    }

    /**
     * 根据当前日期获取推荐的股票
     * @return
     */
    public static List<String> getRecommendStock() {

        return stockDao.getAllStocks();
    }


    /**
     * 获取股票池的基准收益率
     * @param stockPool 股票池
     * @param validDates 所有的有效日期
     * @return 返回基准收益率(累加的)
     */
    public static ArrayList<List<Double>> getPrimaryRate(List<String> stockPool, List<LocalDate> validDates){

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
    public static double calculateTotalClose(List<String> stockPool, LocalDate date) {
        double totalClose = 0.0;
        for (String code : stockPool) {
            totalClose += stockDao.getStockAdjClose(code, date);
        }
        return totalClose;
    }

    /**
     * to check if the stock exists
     * @param stockCode stock code
     * @return true if exists,else false
     */
    public static boolean codeExists(String stockCode) {
        List<String> codes = stockDao.getAllStocks();
        return codes.contains(stockCode);
    }

    @Autowired
    public void setStockDao(StockDao dao) {
        stockDao = dao;
    }

}
