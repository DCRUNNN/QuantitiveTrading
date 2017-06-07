package cn.edu.nju.p.deeplearning.factor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-6.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class Alpha26Test {

    @Autowired
    private Alpha26 alpha26;

    @Test
    public void getAlpha() throws Exception {

        System.out.println(alpha26.getAlpha("000002", LocalDate.of(2013, 3, 5)));
    }

}