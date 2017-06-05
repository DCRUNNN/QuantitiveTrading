package cn.edu.nju.p.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-5.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class StockHelperTest {

    @Test
    public void getValidStockTest() throws Exception {

        LocalDate currentDate = LocalDate.of(2012, 2, 3);
        System.out.println(StockHelper.getAllValidStocksLastTenDay(currentDate));

    }

    @Test
    public void hasStoppedTest() {

        String code = "000010";
        LocalDate beginDate = LocalDate.of(2012, 2, 3);
        LocalDate endDate = beginDate.minusYears(1);
        System.out.println(StockHelper.hasStopped(code,endDate,beginDate));
    }

    @Test
    public void getAllStockTest() {
        LocalDate date = LocalDate.of(2012, 2, 3);
        System.out.println(StockHelper.getAllValidStocksLastTenDay(date));
    }
}