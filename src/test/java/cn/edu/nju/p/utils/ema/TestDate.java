package cn.edu.nju.p.utils.ema;

import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by soft on 2017/5/18.
 */
public class TestDate {

    @Test
    public void dateTest(){

        LocalDate current = LocalDate.now();
        System.out.println(current.getDayOfWeek());

    }
}
