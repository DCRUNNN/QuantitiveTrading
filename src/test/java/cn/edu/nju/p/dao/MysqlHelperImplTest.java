package cn.edu.nju.p.dao;

import cn.edu.nju.p.QuantradingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by soft on 2017/5/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = QuantradingApplication.class)
public class MysqlHelperImplTest {

    @Autowired
    private MysqlHelperImpl mysqlHelper;

    @Test
    public void getDataTest(){

        String year = "2012";
        try {
            mysqlHelper.getDataFromCSV(year);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}