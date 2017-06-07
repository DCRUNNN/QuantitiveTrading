package cn.edu.nju.p.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-7.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class DateHelperTest {

    @Test
    public void getBetweenDateAndFilter() throws Exception {

        LocalDate beginDate = LocalDate.of(2012, 2, 3);
        LocalDate endDate = LocalDate.of(2012, 5, 2);
        assertEquals("[2012-02-03, 2012-02-06, 2012-02-07, 2012-02-08, 2012-02-09, 2012-02-10, 2012-02-13, 2012-02-14, 2012-02-15, 2012-02-16, 2012-02-17, 2012-02-20, 2012-02-21, 2012-02-22, 2012-02-23, 2012-02-24, 2012-02-27, 2012-02-28, 2012-02-29, 2012-03-01, 2012-03-02, 2012-03-05, 2012-03-06, 2012-03-07, 2012-03-08, 2012-03-09, 2012-03-12, 2012-03-13, 2012-03-14, 2012-03-15, 2012-03-16, 2012-03-19, 2012-03-20, 2012-03-21, 2012-03-22, 2012-03-23, 2012-03-26, 2012-03-27, 2012-03-28, 2012-03-29, 2012-03-30, 2012-04-05, 2012-04-06, 2012-04-09, 2012-04-10, 2012-04-11, 2012-04-12, 2012-04-13, 2012-04-16, 2012-04-17, 2012-04-18, 2012-04-19, 2012-04-20, 2012-04-23, 2012-04-24, 2012-04-25, 2012-04-26, 2012-04-27, 2012-05-02]",
                DateHelper.getBetweenDateAndFilter(beginDate, endDate, a -> true).toString());
    }

}