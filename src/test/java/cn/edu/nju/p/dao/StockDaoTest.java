package cn.edu.nju.p.dao;

import cn.edu.nju.p.QuantradingApplication;
import cn.edu.nju.p.po.StockPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
/**
 * Created by dell- on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QuantradingApplication.class)
public class StockDaoTest {

//    @Autowired
    //提示说没有找到bean??????
//    private StockDao stockDao;

    @Test
    public void getStockPO() throws Exception {
//        StockPO po = stockDao.getStockPO("000001", "2017-05-11");
//        System.out.println(po.getName());
    }

    @Test
    public void getStockOpen() throws Exception {

    }

    @Test
    public void getStockHigh() throws Exception {
//        System.out.println(stockDao.getStockOpen("000001","2017-05-11"));
    }

    @Test
    public void getStockLow() throws Exception {

    }

    @Test
    public void getStockClose() throws Exception {

    }

    @Test
    public void getStockVolume() throws Exception {

    }

    @Test
    public void getStockAdjClose() throws Exception {

    }

    @Test
    public void getStockName() throws Exception {
//        System.out.println(stockDao.getStockName("000001"));

    }

    @Test
    public void getStockMarket() throws Exception {

    }

    @Test
    public void getStockCode() throws Exception {

    }

    @Test
    public void getStockCloseByName() throws Exception {

    }

    @Test
    public void getStockLowByName() throws Exception {

    }

    @Test
    public void getStockHighByName() throws Exception {

    }

    @Test
    public void getStockOpenByName() throws Exception {

    }

    @Test
    public void getStockVolumeByName() throws Exception {

    }

    @Test
    public void getPOList() throws Exception {

    }

    @Test
    public void getPOCodes() throws Exception {

    }

    @Test
    public void getStockSector() throws Exception {

    }

    @Test
    public void getStockBySector() throws Exception {

    }

    @Test
    public void getAllStocks() throws Exception {

    }

}