package cn.edu.nju.p.service;

import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.KLineVO;

import java.time.LocalDate;
import java.util.List;

/**
 * 构建K线提供的接口
 */
public interface KLineService {

    /**
     * 通过股票代码获得KLine需要的所有VO
     * @param code 股票代码
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 返回每一天的KLine数据
     */
    List<KLineVO> getKLineVOSByCode(String code, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException;


    /**
     * 通过股票名称获得KLine需要的所有VO
     * @param name 股票名称
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 返回每一天的KLine数据
     */
    List<KLineVO> getKLineVOSByName(String name, LocalDate beginDate, LocalDate endDate) throws StockNotFoundException;

}
