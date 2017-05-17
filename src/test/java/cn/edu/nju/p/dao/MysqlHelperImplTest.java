package cn.edu.nju.p.dao;


import cn.edu.nju.p.QuantradingApplication;
import cn.edu.nju.p.dao.StockDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by soft on 2017/5/17.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = QuantradingApplication.class)
public class MysqlHelperImplTest {
    @Autowired
    private MysqlHelperImpl helper;

    @Test
    public void getDataFromCSV() throws Exception {
        helper.getDataFromCSV("2016");
    }
}