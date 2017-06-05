package cn.edu.nju.p.deeplearning.factor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Alpha12Test {

    @Autowired
    private Alpha12 alpha12;

    @Test
    public void getAlpha() throws Exception {

        String stockCode = "000001";
        LocalDate localDate = LocalDate.of(2012, 2, 3);
        assertEquals(-0.26136777700475045,alpha12.getAlpha(stockCode,localDate),0.000000000001);
    }

    @Test
    public void getAlpha2Test() throws Exception {

        String code = "000002";
        LocalDate localDate = LocalDate.of(2012, 2, 3);
        assertEquals(0.4201615779544145,alpha12.getAlpha(code,localDate),0.000000000001);
    }

    @Test
    public void getAlpha3Test() throws Exception {

        String code = "000004";
        LocalDate localDate = LocalDate.of(2012, 2, 3);
        assertEquals(-0.013081359844710252,alpha12.getAlpha(code,localDate),0.000000000001);
    }
}