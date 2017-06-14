package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * attention! must use the mysql on the server,because the column current_time has been modified.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class StockDaoTest {

    @Autowired
//    提示说没有找到bean??????
    private StockDao stockDao;
    private LocalDate date = LocalDate.of(2017, 6, 13);
    private String code = "002166";
    private String name = "深发展A";


    @Test
    public void getStockPO() throws Exception {
        System.out.println("=================================================");
        StockPO po = stockDao.getStockPO(code, date);
        if (po == null) {
            System.out.println("po是null！！");
        } else {
            System.out.println(po.toString());
        }
        System.out.println("==================================================");
    }

    @Test
    public void getStockOpen() throws Exception {
//        assertEquals(stockDao.getStockOpen(code, date), 18.01, 0.01);
        System.out.println(stockDao.getStockOpen(code,date));
    }

    @Test
    public void getStockHigh() throws Exception {
        assertEquals(stockDao.getStockHigh(code, date), 18.92, 0.01);
    }

    @Test
    public void getStockLow() throws Exception {
        assertEquals(stockDao.getStockLow("001", date), 18.00, 0.01);
    }

    @Test
    public void getStockClose() throws Exception {
        assertEquals(stockDao.getStockClose("000001", LocalDate.of(2012,10,20)), 18.68, 0.01);
    }

    @Test
    public void getStockVolume() throws Exception {
        System.out.println(stockDao.getStockVolume(code, date));
    }

    @Test
    public void getStockAdjClose() throws Exception {
        assertEquals(stockDao.getStockAdjClose(code, date), 6.66, 0.01);
    }

    @Test
    public void getStockName() throws Exception {
        assertEquals(stockDao.getStockName(code), "深发展A");
    }

    @Test
    public void getStockMarket() throws Exception {
        assertEquals(stockDao.getStockMarket(code), "SZ");
    }

    @Test
    public void getStockCode() throws Exception {
        assertEquals("000001", stockDao.getStockCode(name));
    }

    @Test
    public void getPOList() throws Exception {
        List<StockPO> poList = stockDao.getPOList(date.toString());
        for (int i=0;i<1000;i++) {
            System.out.println(poList.get(i).getVolume());
        }
    }


    @Test
    public void getStockSector() throws Exception {

    }

    @Test
    public void getStockBySector() throws Exception {

    }

    @Test
    public void getAllStocks() throws Exception {

        System.out.println(stockDao.getAllStocks().size() + "---->" + stockDao.getAllStocks());
    }

    @Test
    public void insertIntoStockDatabase() throws Exception {

    }

    @Test
    public void testIsOpen() throws Exception {

        String code = "000001";
        LocalDate date = LocalDate.of(2008, 1, 2);
        System.out.println(stockDao.getStockIsOpen(code,date));
    }

    @Test
    public void getIsOpenTest2() throws Exception {
        String code = "000001";
        LocalDate date = LocalDate.of(2008, 6, 12);
        System.out.println(stockDao.getStockIsOpen(code, date));
    }

    @Test
    public void testUpdate() throws Exception {
        StockPO po = new StockPO();
        po.setName("测试");
        po.setDate("2017-06-14");
        po.setCode("000001");
        po.setOpen(-1);
        po.setHigh(-2);
        po.setLow(-3);
        po.setClose(-4);
        po.setAdj_close(-4);
        po.setLastClose(-5);
        po.setVolume(666);
        po.setQuote_change("quo");
        po.setMarket("深圳呀");
        po.setIsOpen(true);

        stockDao.updateStockDatabase("2017", po);
    }
}