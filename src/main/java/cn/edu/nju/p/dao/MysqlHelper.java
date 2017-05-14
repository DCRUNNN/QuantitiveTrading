package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.StockPO;

/**
 * Created by dell- on 2017/5/12.
 */
public interface MysqlHelper {

    void getDataFromCSV(String year);

    void insertIntoDataBase(String year,StockPO po);
}
