package cn.edu.nju.p.dao;

import cn.edu.nju.p.dao.daoimpl.StockDaoImpl;
import cn.edu.nju.p.po.StockPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell- on 2017/6/11.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class StockDaoImplTest {

    @Autowired
    StockDao stockDao;

    @Test
    public void insert() throws Exception {
        StockDaoImpl impl=new StockDaoImpl();
        LocalDate date = LocalDate.of(2008, 8, 8);
        Map<String, Map<String, StockPO>> map = StockDaoImpl.getMap(date); //得到2008年的数据

        Set<Map.Entry<String, Map<String, StockPO>>> entrySet=map.entrySet();
        try{
            for (Map.Entry<String, Map<String, StockPO>> set : entrySet) {
                Map<String, StockPO> inMap = set.getValue();
                Set<Map.Entry<String, StockPO>> inSet = inMap.entrySet();
                for (Map.Entry<String, StockPO> set2 : inSet) {
                    StockPO po = set2.getValue();
                    stockDao.insertIntoStockDatabase("2017", po);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
