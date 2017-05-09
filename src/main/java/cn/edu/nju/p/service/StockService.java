package cn.edu.nju.p.service;

import cn.edu.nju.p.po.StockPo;

import java.time.LocalDate;

/**
 * Created by xihao on 17-5-9.
 */
public interface StockService {

    StockPo getStockInfo(String code, LocalDate date);
}
