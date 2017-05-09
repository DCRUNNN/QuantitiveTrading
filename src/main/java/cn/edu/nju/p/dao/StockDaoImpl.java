package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.StockPo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by xihao on 17-5-9.
 */
@Repository
public class StockDaoImpl implements StockDao {

    @Override
    public StockPo getStockPo(String code, LocalDate date) {

        StockPo stockPo = new StockPo();
        stockPo.setName("xihao");
        stockPo.setCode("123");
        stockPo.setDate(LocalDate.of(2012, 2, 3));
        return stockPo;
    }
}
