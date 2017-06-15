package cn.edu.nju.p.strategy;

import cn.edu.nju.p.utils.CalculateHelper;
import cn.edu.nju.p.utils.DoubleUtils;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.utils.beans.ToolSpring;
import cn.edu.nju.p.utils.holiday.Holidays;
import cn.edu.nju.p.utils.redis.StockRedisDataUtils;
import com.alibaba.fastjson.JSON;
import org.datavec.api.formats.input.impl.LibSvmInputFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Xihao on 2017/6/14.
 */
public class StrategyRunner {

    public static void run(Strategy strategy) {

        Holidays holidays = ToolSpring.getBeans(Holidays.class);
        StockHelper stockHelper = ToolSpring.getBeans(StockHelper.class);
        CalculateHelper calculateHelper = ToolSpring.getBeans(CalculateHelper.class);

        List<String> stockPool = strategy.setStockPool();
        LocalDate beginDate = strategy.setBeginDate();
        LocalDate endDate = strategy.setEndDate();
        int holdingDay = strategy.setHoldingDay();
        int formativeDay = strategy.setFormativeDayNum();

        LocalDate virBeginDate = holidays.getIntervalEffectiveDate(beginDate, formativeDay);
        List<LocalDate> betweenDates = holidays.getBetweenDatesAndFilter(virBeginDate, endDate, a -> true);

        List<Double> dailyYieldRates = new ArrayList<>();
        List<Double> accumulationYieldRates = new ArrayList<>();

        List<String> stockToHold = strategy.setWinner(virBeginDate, holidays.getLastValidDate(betweenDates.get(0)),stockPool);

        double beginClose = stockHelper.calculateTotalClose(stockToHold, betweenDates.get(0)); //初始总收盘价
        int moneyPer100 = new BigDecimal(100 * beginClose).intValue();

        int primaryMoney = 100000;
        int totalMoney = 100000;
        int nums = primaryMoney / moneyPer100;
        int leftMoney = primaryMoney - nums * moneyPer100;
        int lastMoney = primaryMoney;

        //计算策略收益率
        for (int i = 1; i < betweenDates.size(); i++) {
            LocalDate currentDate = betweenDates.get(i);
            if (i % holdingDay == 0) {
                //整除 重新选择股票
                virBeginDate = holidays.getIntervalEffectiveDate(currentDate, formativeDay);
                stockToHold = strategy.setWinner(virBeginDate, holidays.getLastValidDate(currentDate), stockPool);
                beginClose = stockHelper.calculateTotalClose(stockToHold, betweenDates.get(i - 1));
                moneyPer100 = new BigDecimal(100 * beginClose).intValue();
                nums = lastMoney / moneyPer100;
                leftMoney = lastMoney - nums * moneyPer100;
            }

            double totalClose = stockHelper.calculateTotalClose(stockToHold, currentDate);
            totalMoney = new BigDecimal(totalClose * 100).intValue() * nums + leftMoney; //当前总资产

            dailyYieldRates.add(DoubleUtils.formatDouble((double) (totalMoney - lastMoney) / lastMoney, 4));
            accumulationYieldRates.add(DoubleUtils.formatDouble((double) (totalMoney - primaryMoney) / primaryMoney, 4));
            lastMoney = totalMoney;
        }

        //基准收益率 取股票池中所有股票的平均收益
        ArrayList<List<Double>> maps = stockHelper.getPrimaryRate(stockPool, betweenDates);

        calculateHelper.setDailyYieldRates(dailyYieldRates);
        calculateHelper.setAccumulationYieldRates(accumulationYieldRates);
        calculateHelper.setDailyPrimaryRates(maps.get(0));
        calculateHelper.setAccumulationPrimaryRates(maps.get(1));

        double beta = calculateHelper.getBeta();
        double alpha = calculateHelper.getAlpha();
        double primaryYearYield = calculateHelper.getPrimaryYearRate();
        double yearYield = calculateHelper.getFieldYearRate();
        double shapeRatio = calculateHelper.getShapeRatio();
        double maxDrawDown = calculateHelper.getMaxDrawDown();

        List<String> dateList = betweenDates.parallelStream().map(LocalDate::toString).sorted().collect(Collectors.toList());

        String jsonString = JSON.toJSONString(new StrategyResultVO(maps.get(1), accumulationYieldRates, dateList, beta, alpha, shapeRatio, maxDrawDown, yearYield, primaryYearYield));
        System.out.println(jsonString);

    }
}
