package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.StockPO;

import java.util.Map;

/**
 * Created by dell- on 2017/6/10.
 */
public interface StockDataTxtHelper {

    public Map<String,Map<String,StockPO>> getStockData(String year);

}
