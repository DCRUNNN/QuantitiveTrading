package cn.edu.nju.p.dao.daoimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by dell- on 2017/6/9.
 */
public class StockDaoImpl implements StockDao {
    @Override
    public StockPO getStockPO(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Double getStockOpen(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Double getStockHigh(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Double getStockLow(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Double getStockClose(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Integer getStockVolume(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public Double getStockAdjClose(@Param("code") String code, @Param("date") LocalDate date) {
        return null;
    }

    @Override
    public String getStockName(String code) {
        return null;
    }

    @Override
    public String getStockMarket(String code) {
        return null;
    }

    @Override
    public String getStockCode(String name) {
        return null;
    }

    @Override
    public List<StockPO> getPOList(@Param("date") String date) {
        return null;
    }

    @Override
    public String getStockSector(String code) {
        return null;
    }

    @Override
    public List<String> getStockBySector(String sector) {
        return null;
    }

    @Override
    public List<String> getAllStocks() {
        return null;
    }

    @Override
    public void insertIntoStockDatabase(@Param("year") String year, @Param("StockPO") StockPO po) throws SQLException {

    }
}
