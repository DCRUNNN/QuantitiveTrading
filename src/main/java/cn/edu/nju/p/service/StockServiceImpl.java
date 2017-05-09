package cn.edu.nju.p.service;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * Created by xihao on 17-5-9.
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public StockPo getStockInfo(String code, LocalDate date) {
        return stockDao.getStockPo(code,date);
    }
}
