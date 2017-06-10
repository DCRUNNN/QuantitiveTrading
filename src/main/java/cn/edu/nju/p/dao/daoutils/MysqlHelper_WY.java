package cn.edu.nju.p.dao.daoutils;

import cn.edu.nju.p.po.StockPO;

import java.sql.SQLException;

/**
 * Created by dell- on 2017/6/9.
 */
public interface MysqlHelper_WY {

    void getDataFromCSV(String code)throws SQLException;

    void insertIntoDataBase(String code,StockPO po)throws SQLException;
}
