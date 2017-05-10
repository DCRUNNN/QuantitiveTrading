package cn.edu.nju.p.service.exhibition;
;

import cn.edu.nju.p.exception.DateNotEnoughException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.StockComparedVO;

import java.time.LocalDate;

/**
 * 获得比较的股票信息的接口
 */
public interface CompareService {

    /**
     * 通过股票代码获得比较股票的需要的数据
     * @param code 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 比较股票需要的数据
     */
    StockComparedVO getComparedStockByCode(String code, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException, DateNotEnoughException;

    /**
     * 通过股票名称获得比较股票的需要的数据
     * @param name 股票名称
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 比较股票需要的数据
     */
    StockComparedVO getComparedStockByName(String name, LocalDate beginDate, LocalDate endDate) throws  DateNotEnoughException, StockNotFoundException;
}
