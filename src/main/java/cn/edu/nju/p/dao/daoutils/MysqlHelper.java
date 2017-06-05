package cn.edu.nju.p.dao.daoutils;

import cn.edu.nju.p.po.StockPO;

import java.sql.SQLException;

/**
 * Created by dell- on 2017/5/12.
 */
public interface MysqlHelper {

    void getDataFromCSV(String year)throws SQLException;

    void insertIntoDataBase(String year,StockPO po)throws SQLException;
}
