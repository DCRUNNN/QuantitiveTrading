package cn.edu.nju.p.utils;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public static boolean isValidByCode(String stockCode, LocalDate date) {

        try {
            stockDao.getStockClose(stockCode, date);
            return true;
        } catch (StockNotFoundException /*| NullPointerException*/ e) {
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
            //成交量是0或者有效日无数据都是停牌
            if(/*controller.getVolumeByCode(stockCode,date) == 0*/!isValidByCode(stockCode,date)){
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
                .stream()
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
     * 计算所有股票当天的总收益
     * @param stockHolding 持有的股票
     * @param date 日期
     * @return 持有的股票的单日收益率平均值
     */
    public static double countAllStockRate(List<String> stockHolding, LocalDate date){

        LocalDate lastDate = DateHelper.getLastDate(date, a->true);
        return stockHolding
                .stream()
                .map(code -> {
                    return (stockDao.getStockAdjClose(code, date) - stockDao.getStockAdjClose(code, lastDate)) / stockDao.getStockAdjClose(code, lastDate);
                })
                .reduce(0.0,(a,b)->a + b) / stockHolding.size();
    }

    /**
     * 获取股票池的基准收益率
     * @param stockPool 股票池
     * @param validDates 所有的有效日期
     * @return 返回基准收益率(不是累加的)
     */
    public static Map<LocalDate,Double> getPrimaryRate(List<String> stockPool, List<LocalDate> validDates){

        Map<LocalDate, Double> primaryRate = new LinkedHashMap<>();
        validDates.forEach(aDate -> primaryRate.put(aDate,countAllStockRate(stockPool,aDate)));
        return primaryRate;
    }

    /**
     * to check if the stock exists
     * @param stockCode
     * @return true if exists,else false
     */
    public static boolean codeExists(String stockCode) {
        List<String> codes = stockDao.getPOCodes();
        if (codes.contains(stockCode)) {
            return true;
        }
        return false;
    }

    @Autowired
    public void setStockDao(StockDao dao) {
        stockDao = dao;
    }
}
