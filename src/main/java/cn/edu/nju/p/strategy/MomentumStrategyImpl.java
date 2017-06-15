package cn.edu.nju.p.strategy;

import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.utils.beans.ToolSpring;
import cn.edu.nju.p.utils.redis.StockRedisDataUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xihao on 17-6-13.
 */
public class MomentumStrategyImpl implements Strategy {

    private StockHelper stockHelper;
    private StockRedisDataUtils redisDataUtils;
    private int holdingDayNum;
    private int formativeDayNum;
    private LocalDate beginDate;
    private LocalDate endDate;

    public MomentumStrategyImpl() {
        this.stockHelper = ToolSpring.getBeans(StockHelper.class);
        this.redisDataUtils = ToolSpring.getBeans(StockRedisDataUtils.class);
        this.holdingDayNum = 5;
        this.formativeDayNum = 5;
        this.beginDate = LocalDate.of(2016,1,10);
        this.endDate = LocalDate.of(2016, 12, 10);
    }

    @Override
    public List<String> setStockPool() {
        return stockHelper.getRecommendStock();
    }

    @Override
    public List<String> setWinner(LocalDate beginDate, LocalDate endDate, List<String> stockPool) {

        Map<String, Double> fieldRates = new LinkedHashMap<>();
        stockPool.forEach(stockCode -> fieldRates.put(stockCode,countRate(beginDate,endDate,stockCode)));

        //对收益率进行排序
        List<Map.Entry<String, Double>> rateList = new ArrayList<>(fieldRates.entrySet());
        rateList.sort((rate1, rate2) -> new BigDecimal(rate2.getValue()).compareTo(new BigDecimal(rate1.getValue())));

        int winnerNum = rateList.size() / 5;
        return rateList.subList(0,winnerNum)
                .parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double countRate(LocalDate beginDate, LocalDate endDate, String stockCode) {

        try {
            StockPO beginPo = redisDataUtils.getStockPO(stockCode, beginDate);
            StockPO endPo = redisDataUtils.getStockPO(stockCode, endDate);
            double beginClose = beginPo.getClose();
            double endClose = endPo.getClose();
            return (endClose - beginClose) / beginClose;
        } catch (NullPointerException ne) {
            return -99;
        }
    }
    @Override
    public LocalDate setBeginDate() {
        return beginDate;
    }

    @Override
    public LocalDate setEndDate() {
        return endDate;
    }

    @Override
    public int setHoldingDay() {
        return holdingDayNum;
    }

    @Override
    public int setFormativeDayNum() {
        return formativeDayNum;
    }
}
