package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.service.exhibition.AverageLineService;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import cn.edu.nju.p.vo.AverageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取均线数据的接口的实现
 */
@Service
public class AverageLineServiceImpl implements AverageLineService {

    private int[] values = new int[]{5, 10, 30, 60,120,240};

    @Autowired
    private StockDao stockDao;

    /**
     * 获取均线数据
     * @param code      股票代码
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 均线数据
     */
    @Override
    public AverageVO getAverageVOByCode(String code, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException {

        List<LocalDate> validDates = DateHelper.getBetweenDateAndFilter(beginDate,endDate, date-> StockHelper.isValidByCode(code,date));

        List<Map<LocalDate, Double>> lines = new ArrayList<>();//保存结果，5,10,30,60,120,240每条线一个map
        String stockName = stockDao.getStockName(code);

        for (int level : values) {
            if (validDates.size() > level) {
                Map<LocalDate, Double> result = new LinkedHashMap<>();
                validDates.forEach(date -> result.put(date,getAverage(level,date,code,true)));
                lines.add(result);
            }
        }
        return new AverageVO(lines,code,stockName);
    }

    /**
     * 获取均线数据
     *
     * @param name      股票名称
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 均线数据
     */
    @Override
    public AverageVO getAverageVOByName(String name, LocalDate beginDate, LocalDate endDate) throws  StockNotFoundException {

        String stockCode = stockDao.getStockCode(name);
        return getAverageVOByCode(stockCode, beginDate, endDate);
    }

    /**
     * 获得当日的均线值
     * @param meanDayNum 几日均线
     * @param currentDate 当前日期
     * @param codeOrName 股票代码或者股票名称
     * @param isCode 是否是代码
     * @return 平均值
     */
    private double getAverage(int meanDayNum, LocalDate currentDate, String codeOrName, boolean isCode) {

        double total = 0.0;
        for (int i = 0; i < meanDayNum; i++) {
            if (isCode) {
                try {
                    total += stockDao.getStockClose(codeOrName, currentDate.toString());
                } catch (StockNotFoundException e) {
                    throw new RuntimeException(e);
                }
                currentDate = DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByCode(codeOrName, date));
            } else {
                try {
                    total += stockDao.getStockCloseByName(codeOrName, currentDate.toString());
                } catch (StockNotFoundException e) {
                    throw new RuntimeException(e);
                }
                currentDate = DateHelper.getLastDate(currentDate, date -> StockHelper.isValidByName(codeOrName, date));
            }
        }

        return total / meanDayNum;
    }
}
