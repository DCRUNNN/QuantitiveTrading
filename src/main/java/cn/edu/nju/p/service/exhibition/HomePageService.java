package cn.edu.nju.p.service.exhibition;

import cn.edu.nju.p.exception.StockNoneException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.StockMarketVO;
import cn.edu.nju.p.vo.StockVO;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cyz on 2017/6/6.
 */
public interface HomePageService{


    /**
     * 获取当天的股票行情
     * @param date 日期
     * @return 返回当日的股票数据
     */
    StockMarketVO getMarketVO(LocalDate date) throws StockNoneException, StockNotFoundException;

    /**
     * 获取当日的所有股票行情
     *
     * @param date 日期
     * @return 返回当日所有股票的信息
     *
     */

    List<StockVO> getStockVO(LocalDate date);


}
