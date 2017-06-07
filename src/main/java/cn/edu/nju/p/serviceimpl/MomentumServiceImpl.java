package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.service.strategy.MomentumService;
import cn.edu.nju.p.utils.CalculateHelper;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.DoubleUtils;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.vo.MomentumResultVO;
import cn.edu.nju.p.vo.MomentumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MomentumService的实现类，实现了动量策略，使用简单收益率作为动量因子
 */
@Service
public class MomentumServiceImpl implements MomentumService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private CalculateHelper helper;


    /**
     * 获取基本的数据
     * @param momentumVO 数据输入
     * @return 返回显示结果的vo
     */
    @Override
    public MomentumResultVO getResult(MomentumVO momentumVO) {

        LocalDate beginDate = momentumVO.getBeginDate();
        LocalDate endDate = momentumVO.getEndDate();
        int formativeDayNum = momentumVO.getDayNumFormative();
        int holdingDayNum = momentumVO.getDayNumHolding();

        //获取股票池
        List<String> stockPool = momentumVO.getStockPool()==null?getRecommendPool():momentumVO.getStockPool();

        //获得开始日期-形成期的具体日期，作为判断股票停牌的开始日期
        LocalDate virtualBeginDate = DateHelper.getIntervalEffectiveDate(beginDate,formativeDayNum);

        //过滤股票池
        stockPool = StockHelper.filterStockPool(stockPool,virtualBeginDate,endDate);

        //开始日期和结束日期之间的有效日期
        List<LocalDate> betweenDates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, a -> true);

        Map<LocalDate,Double> fieldRate = new LinkedHashMap<>();//策略收益率
        Map<LocalDate, Double> dailyFieldRate = new LinkedHashMap<>();
        //持有的股票
        List<String> stockToHold;

        int primaryMoney = 100000;
        int totalMoney = 100000;

        LocalDate beginValidDate = betweenDates.get(0);
        LocalDate virBeginDate = DateHelper.getIntervalEffectiveDate(beginValidDate, formativeDayNum);
        stockToHold = getWinnerStock(virBeginDate, DateHelper.getLastDate(beginValidDate, a -> true),stockPool);
        double beginClose = StockHelper.calculateTotalClose(stockToHold, beginValidDate); //初始总收盘价
        int moneyPer100 = new BigDecimal(100 * beginClose).intValue();

        int nums = primaryMoney / moneyPer100;
        int leftMoney = primaryMoney - nums * moneyPer100;
        int lastMoney = primaryMoney;

        //计算策略收益率
        for (int i = 1; i < betweenDates.size(); i++) {
            LocalDate currentDate = betweenDates.get(i);
            if (i % holdingDayNum == 0) {
                //整除 重新选择股票
                virBeginDate = DateHelper.getIntervalEffectiveDate(currentDate, formativeDayNum);
                stockToHold = getWinnerStock(virBeginDate, DateHelper.getLastDate(currentDate, a -> true),stockPool);
                beginClose = StockHelper.calculateTotalClose(stockToHold, betweenDates.get(i - 1));
                moneyPer100 = new BigDecimal(100 * beginClose).intValue();
                nums = lastMoney / moneyPer100;
                leftMoney = lastMoney - nums * moneyPer100;
            }

            double totalClose = StockHelper.calculateTotalClose(stockToHold, currentDate);
            totalMoney = new BigDecimal(totalClose * 100).intValue() * nums + leftMoney; //当前总资产

            fieldRate.put(currentDate, DoubleUtils.formatDouble((double) (totalMoney - primaryMoney) / primaryMoney, 4));
            dailyFieldRate.put(currentDate, DoubleUtils.formatDouble((double) (totalMoney - lastMoney) / lastMoney, 4));
            lastMoney = totalMoney;
        }

        //基准收益率 取股票池中所有股票的平均收益
        ArrayList<Map<LocalDate, Double>> maps = StockHelper.getPrimaryRate(stockPool, betweenDates);

        helper.setFieldRates(dailyFieldRate);
        helper.setTotalFieldRates(fieldRate);
        helper.setPrimaryRates(maps.get(0));
        helper.setTotalPrimaryRates(maps.get(1));

        Map<LocalDate, Double> fieldRate_adj = helper.getFieldAdjRates();
        Map<LocalDate, Double> primaryRate_adj = helper.getPrimaryAdjRates();
        Map<Double, Integer> rateFrequency = helper.getRateFrequency(holdingDayNum);
        double beta = helper.getBeta();
        double alpha = helper.getAlpha();
        double primaryYearYield = helper.getPrimaryYearRate();
        double yearYield = helper.getFieldYearRate();
        double shapeRatio = helper.getShapeRatio();
        double maxDrawDown = helper.getMaxDrawDown();

        return new MomentumResultVO(fieldRate_adj,primaryRate_adj,rateFrequency,beta,alpha,shapeRatio,maxDrawDown,yearYield,primaryYearYield);
    }

    /**
     * 获取自推荐的股票池.默认就是所有股票
     * @return 返回自推荐的股票池
     */
    private List<String> getRecommendPool() {

        return stockDao.getAllStocks();
    }

    /**
     * 获取winner股票 例如获取10-20到11-20的前20%收益率最高的股票
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param stockPool 股票池
     * @return 返回前20%收益的winner
     */
    public List<String> getWinnerStock(LocalDate beginDate,LocalDate endDate,List<String> stockPool){

        Map<String, Double> fieldRates = new LinkedHashMap<>();
        stockPool.forEach(stockCode -> fieldRates.put(stockCode,countRate(beginDate,endDate,stockCode)));

        //对收益率进行排序
        List<Map.Entry<String, Double>> rateList = new ArrayList<>(fieldRates.entrySet());
        rateList.sort((rate1, rate2) -> new BigDecimal(rate2.getValue()).compareTo(new BigDecimal(rate1.getValue())));

        int winnerNum = rateList.size()/5;
        return rateList.subList(0,winnerNum)
                .parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * 计算股票在开始时间和结束时间内的简单收益率 计算公式为(结束日期当天adjClose-开始日期前日adjClose)/开始日期前日adjClose
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param stockCode 股票代码
     * @return 收益率
     */
    public double countRate(LocalDate beginDate,LocalDate endDate,String stockCode){

        LocalDate beginDate_last = DateHelper.getLastDate(beginDate, a -> true);
        double beginClose = stockDao.getStockAdjClose(stockCode,beginDate_last);
        double endClose = stockDao.getStockAdjClose(stockCode,endDate);
        return (endClose-beginClose)/beginClose;
    }


}
