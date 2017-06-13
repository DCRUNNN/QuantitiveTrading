package cn.edu.nju.p.utils.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-13.
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class StockRedisDataUtilsTest {

    @Autowired
    private StockRedisDataUtils dataUtils;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void getStockClose() throws Exception {
        System.out.println(dataUtils.getStockClose("000001", LocalDate.of(2006,1,17)));
    }

    @Test
    public void getStockOpen() throws Exception {
    }

    @Test
    public void getStockHigh() throws Exception {
    }

    @Test
    public void getStockVolume() throws Exception {
    }

    @Test
    public void getStockPO() throws Exception {
    }

    @Test
    public void insertTest() throws Exception {

//        Map<String,String> map = new
    }
}