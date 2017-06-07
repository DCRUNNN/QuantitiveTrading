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
import java.util.stream.Collectors;

/**
 * @see StockDao 调用股票数据层接口，对股票进行一些逻辑处理
 */
@Component
public class StockHelper {

    private static StockDao stockDao;

    /**
     * 根据股票代码判断这个日期是否存在数据
     *
     * @param stockCode
     * @param date
     * @return 存在数据返回true，不存在数据返回false
     */
    @Cacheable("checkValid")
    public static boolean isValidByCode(String stockCode, LocalDate date) {

        try {
            Double close = stockDao.getStockClose(stockCode, date);
            Double adjClose = stockDao.getStockAdjClose(stockCode, date);
            return close != null && adjClose != null;
        } catch (StockNotFoundException | NullPointerException e) {
            return false;
        }
    }

    /**
     * 判断股票在指定日期之内是否存在停牌状况
     * @param stockCode 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 存在停牌情况返回true，不存在返回false
     */
    public static boolean hasStopped(String stockCode, LocalDate beginDate, LocalDate endDate) {

        List<LocalDate> betweenDates;//获取去除周末的日期
        betweenDates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, a -> true);

        //存在无效日期就返回true

        for (LocalDate date:betweenDates) {

            if (!isValidByCode(stockCode, date)) {
                //没有收盘数据也是停牌
                return true;
            }

            //有效日期并且成交量为０就是停牌
            if(isValidByCode(stockCode,date) && stockDao.getStockVolume(stockCode,date) == 0){
                //System.out.println(date.toString()+" "+stockCode);
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
     * @param currentDate
     * @return
     */
    public static List<String> getRecommendStock(LocalDate currentDate) {

        return null;
    }


    /**
     * 获取股票池的基准收益率
     * @param stockPool 股票池
     * @param validDates 所有的有效日期
     * @return 返回基准收益率(累加的)
     */
    public static ArrayList<Map<LocalDate, Double>> getPrimaryRate(List<String> stockPool, List<LocalDate> validDates){

        Map<LocalDate, Double> primaryRate = new LinkedHashMap<>();
        Map<LocalDate, Double> dailyRates = new LinkedHashMap<>();

        double totalClose = calculateTotalClose(stockPool, validDates.get(0)); //买入时的总收盘价
        double lastClose = totalClose;

        for (int i = 1; i < validDates.size(); i++) {
            LocalDate currentDate = validDates.get(i);
            double allClose = calculateTotalClose(stockPool, currentDate);
//            System.out.println("Primary Rate: ------------->" + (allClose - totalClose));
            //计算当日和买入日比较的总收益，并且格式化
            primaryRate.put(currentDate, DoubleUtils.formatDouble((allClose - totalClose) / totalClose, 4));
            dailyRates.put(currentDate, DoubleUtils.formatDouble((allClose - lastClose) / lastClose, 4));
            lastClose = allClose;
        }

        ArrayList<Map<LocalDate, Double>> maps = new ArrayList<>();
        maps.add(dailyRates);
        maps.add(primaryRate);
        return maps;
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

    public static List<String> getAllValidStocksLastThirtyDay(LocalDate date) {

        LocalDate lastDate = date.minusDays(30);
        List<String> allStocks = stockDao.getAllStocks();

        List<LocalDate> betweenDates = DateHelper.getBetweenDateAndFilter(lastDate, date, a -> true);

        List<String> results = new ArrayList<>();

        VacationDates vacationDates = new VacationDates();
        for (String stock : allStocks) {
            boolean hasStopped = false;
            for (LocalDate localDate : betweenDates) {
                boolean isValidAndNoVolume = !vacationDates.isVacation(localDate) && isValidByCode(stock, localDate) && stockDao.getStockVolume(stock, localDate) == 0;
                if (isValidAndNoVolume) {
                    hasStopped = true;
                    break;
                }
            }
            if (!hasStopped) {
                results.add(stock);
            }
        }

        return results;
    }

    @Autowired
    public void setStockDao(StockDao dao) {
        stockDao = dao;
    }


}
