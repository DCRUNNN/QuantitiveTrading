package cn.edu.nju.p.utils.ema;

import cn.edu.nju.p.QuantradingApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by soft on 2017/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MACDUtilsTest {

    @Autowired
    private MACDUtils macdUtils;

    @Test
    public void getEmaValue() throws Exception {
        System.out.println(macdUtils.getEmaValue(12, LocalDate.of(2012, 10, 20), "000001"));
    }

    @Test
    public void getDea() throws Exception {
        System.out.println(macdUtils.getDea(9,LocalDate.of(2012, 10, 20), "000001"));
    }
}