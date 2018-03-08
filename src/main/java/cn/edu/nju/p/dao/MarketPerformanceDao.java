package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.SameMarketStockPO;

import java.util.List;

/**
 * Created by dell- on 2017/6/8.
 */
public interface MarketPerformanceDao {

    public List<SameMarketStockPO> getSameMarketStockInfo(String code, int type);
}
