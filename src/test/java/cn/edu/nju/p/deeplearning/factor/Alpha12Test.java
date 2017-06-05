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
        System.out.println(alpha12.getAlpha(stockCode, localDate));
    }

}