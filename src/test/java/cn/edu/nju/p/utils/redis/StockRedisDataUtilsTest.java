package cn.edu.nju.p.utils.redis;

import cn.edu.nju.p.utils.holiday.Holidays;
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
    Holidays holidays;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void getStockClose() throws Exception {
        System.out.println(dataUtils.getStockClose("000001", LocalDate.of(2006,1,17)));
        LocalDate beginDate = LocalDate.of(2012, 2, 3);
        LocalDate endDate = LocalDate.of(2013, 2, 3);
        while (beginDate.isBefore(endDate)) {
            if (!holidays.isHoliday(beginDate)) {
                System.out.println(beginDate.toString() + "--->" + dataUtils.getStockClose("000001", beginDate));
            }
            beginDate = beginDate.plusDays(1);
        }
    }

    @Test
    public void getStockOpen() throws Exception {

        LocalDate beginDate = LocalDate.of(2013, 2, 3);
        LocalDate endDate = LocalDate.of(2014, 2, 3);
        while (beginDate.isBefore(endDate)) {
            if (!holidays.isHoliday(beginDate)) {
                System.out.println(dataUtils.getStockOpen("000001", beginDate));
            }
            beginDate = beginDate.plusDays(1);
        }
    }

    @Test
    public void getStockHigh() throws Exception {
        LocalDate beginDate = LocalDate.of(2014, 2, 3);
        LocalDate endDate = LocalDate.of(2015, 2, 3);
        while (beginDate.isBefore(endDate)) {
            if (!holidays.isHoliday(beginDate)) {
                System.out.println(dataUtils.getStockHigh("000001", beginDate));
            }
            beginDate = beginDate.plusDays(1);
        }
    }

    @Test
    public void getStockVolume() throws Exception {
        LocalDate beginDate = LocalDate.of(2015, 2, 3);
        LocalDate endDate = LocalDate.of(2016, 2, 3);
        while (beginDate.isBefore(endDate)) {
            if (!holidays.isHoliday(beginDate)) {
                System.out.println(dataUtils.getStockVolume("000001", beginDate));
            }
            beginDate = beginDate.plusDays(1);
        }
    }

    @Test
    public void getStockPO() throws Exception {

        LocalDate beginDate = LocalDate.of(2016, 2, 3);
        LocalDate endDate = LocalDate.of(2016, 12, 3);
        while (beginDate.isBefore(endDate)) {
            if (!holidays.isHoliday(beginDate)) {
                System.out.println(dataUtils.getStockPO("000001", beginDate));
            }
            beginDate = beginDate.plusDays(1);
        }
    }

    @Test
    public void getMapTest() throws Exception {

        System.out.println(dataUtils.getStockDataOfDateMap(LocalDate.of(2012, 10, 19)));
    }

    @Test
    public void insertTest() throws Exception {

//        Map<String,String> map = new
    }

    @Test
    public void test002730() {

        LocalDate localDate = LocalDate.of(2014, 10, 20);
        String code = "002730";
        System.out.println(dataUtils.getStockPO(code,localDate));
    }
}