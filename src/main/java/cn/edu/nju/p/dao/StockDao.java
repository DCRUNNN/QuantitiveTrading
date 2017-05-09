package cn.edu.nju.p.dao;


import cn.edu.nju.p.po.StockPo;

import java.time.LocalDate;

/**
 * Created by xihao on 17-5-9.
 */
public interface StockDao {

    StockPo getStockPo(String code, LocalDate date);

}
