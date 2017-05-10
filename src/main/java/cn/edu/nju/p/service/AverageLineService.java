package cn.edu.nju.p.service;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.AverageVO;

import java.time.LocalDate;

/**
 * 获得均线数据的接口
 */
public interface AverageLineService {

    /**
     * 获取均线数据
     * @param code 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 均线数据
     */
    AverageVO getAverageVOByCode(String code, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException;

    /**
     * 获取均线数据
     * @param name 股票名称
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 均线数据
     */
    AverageVO getAverageVOByName(String name, LocalDate beginDate, LocalDate endDate)throws StockNotFoundException;
}
