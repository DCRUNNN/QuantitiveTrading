package cn.edu.nju.p.utils;

import cn.edu.nju.p.utils.holiday.VacationDates;
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
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class VacationDatesTest {

    @Autowired
    private VacationDates vacationDates;

    @Test
    public void isVacation() throws Exception {

        LocalDate date = LocalDate.of(2012, 1, 31);
        System.out.println(vacationDates.isVacation(date));
    }

    @Test
    public void isVacationTest() throws Exception {

        LocalDate date = LocalDate.of(2017, 10, 6);
        System.out.println(vacationDates.isVacation(date));

    }

}