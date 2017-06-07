package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.service.exhibition.MeanReversionService;
import cn.edu.nju.p.utils.CalculateHelper;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.DoubleUtils;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.vo.MeanReversionParamVO;
import cn.edu.nju.p.vo.MeanReversionResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 均线回归策略的实现类
 */
@Service
public class MeanReversionServiceImpl implements MeanReversionService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private CalculateHelper helper;

    @Override
    public MeanReversionResultVO getResult(MeanReversionParamVO paramVO) {

        LocalDate beginDate = paramVO.getBeginDate();
        LocalDate endDate = paramVO.getEndDate();
        int holdingDay = paramVO.getHoldingDay(); //持仓期长度
        int holdingNum = paramVO.getHoldingStockNum(); //持仓数目
        int meanDayNum = paramVO.getMeanDayNum(); //几日均线

        LocalDate virBeginDate = DateHelper.getIntervalEffectiveDate(beginDate, holdingDay); //实际开始日期，（因为在beginDate时候需要前面的持有期长度的日期都是有效日期）
        List<String> stockPool = paramVO.getStockPool() == null ? getRecommendPool() : paramVO.getStockPool();
        stockPool = StockHelper.filterStockPool(stockPool, virBeginDate, endDate); //对股票池进行过滤，筛选掉停牌的股票

        List<LocalDate> validDates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, a -> true); //去除周末的有效日期

        //先计算股票池的所有股票的平均收益率，获得每日的基准收益

        //计算策略的每日收益
        Map<LocalDate, Double> fieldRates = new LinkedHashMap<>();
        Map<LocalDate, Double> dailyFieldRates = new LinkedHashMap<>();

        int primaryMoney = 100000; //10w 初始资金
        int totalMoney;

        LocalDate beginValidDate = validDates.get(0);
        List<String> stockToHold = getWinner(stockPool, beginValidDate, meanDayNum, holdingNum) ; //每次持有的股票
        double beginClose = StockHelper.calculateTotalClose(stockToHold, beginValidDate);

        int moneyPer100 = new BigDecimal(100 * beginClose).intValue();
        int nums = primaryMoney / moneyPer100; //多少100手
        int moneyLeft = primaryMoney - moneyPer100 * nums; //剩余的不足100手的金钱

        int lastMoney = primaryMoney;

        for (int i = 1; i < validDates.size(); i++) {
            LocalDate currentDate = validDates.get(i);

            if (i % holdingDay == 0) {
                //能够整除，重新选仓
                stockToHold = getWinner(stockPool, currentDate, meanDayNum, holdingNum);
                beginClose = StockHelper.calculateTotalClose(stockToHold, validDates.get(i - 1));
                moneyPer100 = new BigDecimal(100 * beginClose).intValue();
                nums = lastMoney / moneyPer100;
                moneyLeft = lastMoney - nums * moneyPer100;
            }

            double totalClose = StockHelper.calculateTotalClose(stockToHold, currentDate);
            totalMoney = new BigDecimal(totalClose * 100).intValue() * nums + moneyLeft; //当前总资产

            //计算持有股票的每日总收益率
            fieldRates.put(currentDate, DoubleUtils.formatDouble((double) (totalMoney - primaryMoney) / primaryMoney, 4));
            dailyFieldRates.put(currentDate, DoubleUtils.formatDouble((double) (totalMoney - lastMoney) / lastMoney, 4));
            lastMoney = totalMoney;
        }

        ArrayList<Map<LocalDate, Double>> primaryRatesMap = StockHelper.getPrimaryRate(stockPool, validDates);
        helper.setTotalPrimaryRates(primaryRatesMap.get(1));
        helper.setTotalFieldRates(fieldRates);
        helper.setFieldRates(dailyFieldRates);
        helper.setPrimaryRates(primaryRatesMap.get(0));

        Map<LocalDate, Double> fieldRate_adj = helper.getFieldAdjRates();
        Map<LocalDate, Double> primaryRate_adj = helper.getPrimaryAdjRates();
        Map<Double, Integer> rateFrequency = helper.getRateFrequency(holdingDay);
        double beta = helper.getBeta();
        double alpha = helper.getAlpha();
        double primaryYearYield = helper.getPrimaryYearRate();
        double yearYield = helper.getFieldYearRate();
        double shapeRatio = helper.getShapeRatio();
        double maxDrawDown = helper.getMaxDrawDown();

        return new MeanReversionResultVO(fieldRate_adj,primaryRate_adj,rateFrequency,beta,alpha,shapeRatio,maxDrawDown,yearYield,primaryYearYield);
    }

    /**
     * 获取默认的股票池
     * @return 返回股票池
     */
    private List<String> getRecommendPool() {

        return stockDao.getAllStocks();
    }

    /**
     * 获得某日股票的某个均线的数据(使用复盘价)
     * @param meanDayNum 几日均线
     * @param currentDate 日期(需要传递有效的日期)
     * @param stockCode 股票代码
     * @return 均线的股票价格
     */
    public double getAverage(int meanDayNum, LocalDate currentDate, String stockCode) {

        double total = 0;
        for (int i = 0; i < meanDayNum; i++) {
            total += stockDao.getStockAdjClose(stockCode, currentDate);
            currentDate = DateHelper.getLastDate(currentDate, a -> true);
        }

        return total / meanDayNum;
    }

    /**
     * 获得某段时间内持有的股票
     * @param stockPool 股票池
     * @param currentDate 当前的日期
     */
    public List<String> getWinner(List<String> stockPool,LocalDate currentDate,int meanDayNum,int holdingNum) {

        Map<String, Double> rates = new LinkedHashMap<>();
        stockPool.forEach(code -> rates.put(code, getOffsetRate(currentDate, code, meanDayNum))); //计算所有股票的偏移

        //对偏移量进行排序
        List<Map.Entry<String, Double>> rateList = new ArrayList<>(rates.entrySet());
        rateList.sort(Comparator.comparing(rate -> new BigDecimal(- rate.getValue())));

        return rateList.subList(0, holdingNum)
                .parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * 获得每只股票相对于均线的偏移量
     * @param currentDate 当前日期
     * @param stockCode 股票代码
     * @param meanDayNum 几日均线
     * @return 股票的相对偏移量
     */
    public double getOffsetRate(LocalDate currentDate, String stockCode, int meanDayNum) {

        double average = getAverage(meanDayNum, currentDate, stockCode);
        return (average - stockDao.getStockAdjClose(stockCode, currentDate)) / average;
    }

}
