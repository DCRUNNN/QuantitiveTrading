package cn.edu.nju.p.deeplearning.factor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

/**
 * Created by xihao on 17-6-5.
 */
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class Alpha101Test {

    @Autowired
    private Alpha101 alpha101;

    @Test
    public void getAlphaTest(){

        String code = "000001";
        LocalDate localDate = LocalDate.of(2012, 4, 5);
        System.out.println(alpha101.getAlpha(code, localDate));
    }
}