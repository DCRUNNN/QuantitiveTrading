package cn.edu.nju.p.service.strategy;

import cn.edu.nju.p.exception.StockNoneException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.StockMarketVO;

import java.time.LocalDate;

/**
 * Created by Shinelon on 2017/3/6.
 */
public interface StockMarketService {

    /**
     * 获取当天的股票行情
     * @param date 日期
     * @return 返回当日的股票数据
     */
    StockMarketVO getMarketVO(LocalDate date) throws StockNoneException, StockNotFoundException;
}
