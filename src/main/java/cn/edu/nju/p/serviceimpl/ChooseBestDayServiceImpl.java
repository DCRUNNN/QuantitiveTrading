package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.service.strategy.ChooseBestDayService;
import cn.edu.nju.p.service.exhibition.MeanReversionService;
import cn.edu.nju.p.service.strategy.MomentumService;
import cn.edu.nju.p.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用全部股票作为默认的股票池
 */
@Service
public class ChooseBestDayServiceImpl implements ChooseBestDayService {

    @Autowired
    private MomentumService momentumService;

    @Autowired
    private MeanReversionService meanReversionService;

    @Autowired
    private StockDao stockDao;

    /**
     * 获得动量策略的超额收益率和策略收益率和形成期（持有期）的关系
     *
     * @param momentumParamVO 动量策略的一些参数
     * @return 画图需要的参数
     */
    @Override
    public BestDayResultVO getResultReturnsOfMomentum(MomentumParamVO momentumParamVO) {

        LocalDate beginDate = momentumParamVO.getBeginDate();
        LocalDate endDate = momentumParamVO.getEndDate();
        boolean isFormative = momentumParamVO.isFormativeDay();
        int dayNum = momentumParamVO.getDayNum();
        List<String> stockPool = momentumParamVO.getStockCodes() == null? stockDao.getAllStocks(): momentumParamVO.getStockCodes() ;

        Map<Integer,Double> abnormalReturnsMap = getAbnormalReturns(isFormative, dayNum, beginDate, endDate, stockPool);
        Map<Integer,Double> fieldWinRateMap = getFieldWins(isFormative, dayNum, beginDate, endDate, stockPool);

        return new BestDayResultVO(abnormalReturnsMap,fieldWinRateMap);
    }

    /**
     * 获得均值回归的超额收益率和策略胜率和持有期的关系
     *
     * @param vo 输入的必要的参数
     * @return 返回画图需要的参数（超额收益和策略胜率和持有期的关系）
     */
    @Override
    public BestDayResultVO getResultReturnsOfMeanReversion(MeanReversionParamForBestDayVO vo) {

        LocalDate beginDate = vo.getBeginDate();
        LocalDate endDate = vo.getEndDate();
        int meanDayNum = vo.getMeanDayNum();
        int holdingNum = vo.getHoldingNum();
        List<String> stockPool = vo.getStockPool() == null ? stockDao.getAllStocks() : vo.getStockPool();

        Map<Integer, Double> abnormalReturnMap = getAbnormalReturnsForMean(beginDate, endDate, holdingNum, meanDayNum, stockPool);
        Map<Integer, Double> fieldWinRateMap = getFieldWinsForMean(beginDate, endDate, holdingNum, meanDayNum, stockPool);

        return new BestDayResultVO(abnormalReturnMap, fieldWinRateMap);
    }


    /**
     * 获取超额收益率和形成期或持有期的关系
     * @param isFormative 输入的参数是否是形成期天数
     * @param dayNum 天数
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param stockPool 股票池
     * @return 超额收益率和形成期或持有期的关系
     */
    public Map<Integer,Double> getAbnormalReturns(boolean isFormative,int dayNum,LocalDate beginDate,LocalDate endDate,List<String> stockPool) {

        Map<Integer, Double> result = new LinkedHashMap<>();
        for (int i = 1; i <=50; i++) {
            //形成期（持有期）的天数从1开始50结束
            MomentumVO momentumVO = isFormative ?
                    new MomentumVO(dayNum, i, beginDate, endDate, stockPool) :
                    new MomentumVO(i, dayNum, beginDate, endDate, stockPool);
            MomentumResultVO momentumResultVO = momentumService.getResult(momentumVO);
            double abnormalReturns = momentumResultVO.getYearYield() - momentumResultVO.getPrimaryYearYield();
            result.put(i,abnormalReturns);
        }
        return result;
    }


    /**
     * 计算均线回归策略的超额胜率
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param holdingStockNum 每次持有的股票数目
     * @param meanDayNum 使用几日均线
     * @param stockPool 股票池
     * @return 策略的超额胜率和持有期天数的关系
     */
    public Map<Integer, Double> getAbnormalReturnsForMean(LocalDate beginDate, LocalDate endDate, int holdingStockNum, int meanDayNum, List<String> stockPool) {

        Map<Integer, Double> result = new LinkedHashMap<>();
        for (int i = 1; i <50; i++) {

            MeanReversionParamVO paramVO = new MeanReversionParamVO(stockPool, i, holdingStockNum, beginDate, endDate, meanDayNum);
            MeanReversionResultVO reversionResultVO = meanReversionService.getResult(paramVO);

            result.put(i, reversionResultVO.getYearYield() - reversionResultVO.getPrimaryYearYield());
        }

        return result;
    }


    /**
     * 获取不同形成期（持有期）在开始和结束日期之内的每个持有期超额收益率为+的比率
     * @param isFormative 输入的参数是否是形成期天数
     * @param dayNum 天数
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param stockPool 股票池
     * @return 不同形成期（持有期）的策略胜率
     */
    public Map<Integer,Double> getFieldWins(boolean isFormative,int dayNum,LocalDate beginDate,LocalDate endDate,List<String> stockPool)  {

        Map<Integer, Double> result = new LinkedHashMap<>();
        for (int i = 1; i <=50; i++) {
            //形成期（持有期）的天数从1开始50结束
            MomentumVO momentumVO = isFormative ?
                    new MomentumVO(dayNum, i, beginDate, endDate, stockPool) :
                    new MomentumVO(i, dayNum, beginDate, endDate, stockPool);
            MomentumResultVO momentumResultVO = momentumService.getResult(momentumVO);

            Map<LocalDate, Double> fieldRates = momentumResultVO.getFieldRate(); //策略的每日累加收益率
            List<Double> fieldRateValues = new ArrayList<>(fieldRates.values());

            Map<LocalDate, Double> primaryRates = momentumResultVO.getPrimaryRate(); //基准收益率
            List<Double> primaryRateValues = new ArrayList<>(primaryRates.values());

            if (isFormative) {
                //把持有期天数作为参数传递计算
                result.put(i, calFieldWinRate(fieldRateValues, primaryRateValues, i));
            }else {
                //输入的是持有期
                result.put(i, calFieldWinRate(fieldRateValues, primaryRateValues, dayNum));
            }
        }
        return result;
    }

    /**
     * 计算给定策略收益率和基准收益率的情况下的策略胜率
     * @param fieldRates 策略累加收益率
     * @param primaryRates 基准累加收益率
     * @param holdingNum 持有期天数
     * @return 策略胜率
     */
    public double calFieldWinRate(List<Double> fieldRates, List<Double> primaryRates, int holdingNum) {

        int total = 0;
        int winNum = 0;
        for (int i = 0; i < fieldRates.size(); i++) {
            if ((i + 1) % holdingNum == 0) {
                total++;
                //获取本周期的累计收益率
                double filedRateThisPeriod;
                double primaryRateThisPeriod;
                if (i - holdingNum < 0) {
                    //去除掉第一次开始计算的holdingNum - 1 -holdingNum < 0的情况
                    filedRateThisPeriod = fieldRates.get(i);
                    primaryRateThisPeriod = primaryRates.get(i);
                }else {
                    filedRateThisPeriod = fieldRates.get(i) - fieldRates.get(i - holdingNum);
                    primaryRateThisPeriod = primaryRates.get(i) - primaryRates.get(i - holdingNum);
                }

                if (filedRateThisPeriod > primaryRateThisPeriod) {
                    winNum++;
                }
            }
        }

        //把不能整除剩下的作为一个周期也计算胜率
        int length = fieldRates.size();
        total++;
        int beginIndex = length - length % holdingNum -1;
        if ((fieldRates.get(length - 1) - fieldRates.get(beginIndex)) > (primaryRates.get(length - 1) - primaryRates.get(beginIndex))) {
            winNum++;
        }

        return ((double) winNum) / total;
    }

    /**
     * 获得策略胜率和持有期长度的关系
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param holdingStockNum 持有天数
     * @param meanDayNum 使用的几日均线
     * @param stockPool 股票池
     * @return 不同持有期的策略胜率
     */
    public Map<Integer, Double> getFieldWinsForMean(LocalDate beginDate, LocalDate endDate, int holdingStockNum, int meanDayNum, List<String> stockPool) {

        Map<Integer, Double> result = new LinkedHashMap<>();
        for (int i = 1; i <50; i++) {
            MeanReversionParamVO paramVO = new MeanReversionParamVO(stockPool, i, holdingStockNum, beginDate, endDate, meanDayNum);
            MeanReversionResultVO reversionResultVO = meanReversionService.getResult(paramVO);
            List<Double> primaryRates = new ArrayList<>(reversionResultVO.getPrimaryRate().values());
            List<Double> fieldRates = new ArrayList<>(reversionResultVO.getFieldRate().values());

            result.put(i, calFieldWinRate(fieldRates, primaryRates, i));
        }

        return result;
    }
}
