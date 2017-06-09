package cn.edu.nju.p.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Created by xihao on 17-6-5.
 */
@Component
public class VacationDates {

    private LocalDate[] vacations = new LocalDate[160];

    public VacationDates(){
        //-----------------2009年休市日(去除周末)------------------------------
        int index = 0;
        vacations[index++] = LocalDate.of(2009, 1, 1);
        vacations[index++] = LocalDate.of(2009, 1, 2);
        vacations[index++] = LocalDate.of(2009, 1, 26);
        vacations[index++] = LocalDate.of(2009, 1, 27);
        vacations[index++] = LocalDate.of(2009, 1, 28);
        vacations[index++] = LocalDate.of(2009, 1, 29);
        vacations[index++] = LocalDate.of(2009, 1, 30);
        vacations[index++] = LocalDate.of(2009, 4, 6);
        vacations[index++] = LocalDate.of(2009, 5, 1);
        vacations[index++] = LocalDate.of(2009, 5, 28);
        vacations[index++] = LocalDate.of(2009, 5, 29);
        vacations[index++] = LocalDate.of(2009, 10,1);
        vacations[index++] = LocalDate.of(2009, 10,2);
        vacations[index++] = LocalDate.of(2009, 10,5);
        vacations[index++] = LocalDate.of(2009, 10,6);
        vacations[index++] = LocalDate.of(2009, 10,7);
        vacations[index++] = LocalDate.of(2009, 10,8);
        //--------------------------------------------------------------------

        //-----------------2010年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2010, 1, 1);
        vacations[index++] = LocalDate.of(2010, 2, 15);
        vacations[index++] = LocalDate.of(2010, 2, 16);
        vacations[index++] = LocalDate.of(2010, 2, 17);
        vacations[index++] = LocalDate.of(2010, 2, 18);
        vacations[index++] = LocalDate.of(2010, 2, 19);
        vacations[index++] = LocalDate.of(2010, 4, 5);
        vacations[index++] = LocalDate.of(2010, 5, 3);
        vacations[index++] = LocalDate.of(2010, 6, 14);
        vacations[index++] = LocalDate.of(2010, 6, 15);
        vacations[index++] = LocalDate.of(2010, 6, 16);
        vacations[index++] = LocalDate.of(2010, 9, 22);
        vacations[index++] = LocalDate.of(2010, 9, 23);
        vacations[index++] = LocalDate.of(2010, 9, 24);
        vacations[index++] = LocalDate.of(2010, 10, 1);
        vacations[index++] = LocalDate.of(2010, 10, 4);
        vacations[index++] = LocalDate.of(2010, 10, 5);
        vacations[index++] = LocalDate.of(2010, 10, 6);
        vacations[index++] = LocalDate.of(2010, 10, 7);
        //--------------------------------------------------------------------

        //-----------------2011年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2011, 1, 1);
        vacations[index++] = LocalDate.of(2011, 2, 2);
        vacations[index++] = LocalDate.of(2011, 2, 3);
        vacations[index++] = LocalDate.of(2011, 2, 6);
        vacations[index++] = LocalDate.of(2011, 2, 7);
        vacations[index++] = LocalDate.of(2011, 2, 8);
        vacations[index++] = LocalDate.of(2011, 4, 4);
        vacations[index++] = LocalDate.of(2011, 4, 5);
        vacations[index++] = LocalDate.of(2011, 5, 2);
        vacations[index++] = LocalDate.of(2011, 6, 6);
        vacations[index++] = LocalDate.of(2011, 9, 12);
        vacations[index++] = LocalDate.of(2011, 10, 3);
        vacations[index++] = LocalDate.of(2011, 10, 4);
        vacations[index++] = LocalDate.of(2011, 10, 5);
        vacations[index++] = LocalDate.of(2011, 10, 6);
        vacations[index++] = LocalDate.of(2011, 10, 7);
        //-----------------------------------------------------------------------

        //-----------------2012年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2012, 1, 2);
        vacations[index++] = LocalDate.of(2012, 1, 3);
        vacations[index++] = LocalDate.of(2012, 1, 23);
        vacations[index++] = LocalDate.of(2012, 1, 24);
        vacations[index++] = LocalDate.of(2012, 1, 25);
        vacations[index++] = LocalDate.of(2012, 1, 26);
        vacations[index++] = LocalDate.of(2012, 1, 27);
        vacations[index++] = LocalDate.of(2012, 4, 2);
        vacations[index++] = LocalDate.of(2012, 4, 3);
        vacations[index++] = LocalDate.of(2012, 4, 4);
        vacations[index++] = LocalDate.of(2012, 4, 30);
        vacations[index++] = LocalDate.of(2012, 5, 1);
        vacations[index++] = LocalDate.of(2012, 6, 22);
        vacations[index++] = LocalDate.of(2012, 10, 1);
        vacations[index++] = LocalDate.of(2012, 10, 2);
        vacations[index++] = LocalDate.of(2012, 10, 3);
        vacations[index++] = LocalDate.of(2012, 10, 4);
        vacations[index++] = LocalDate.of(2012, 10, 5);
        //-----------------------------------------------------------------------

        //-----------------2013年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2013, 1, 1);
        vacations[index++] = LocalDate.of(2013, 1, 2);
        vacations[index++] = LocalDate.of(2013, 1, 3);
        vacations[index++] = LocalDate.of(2013, 2, 11);
        vacations[index++] = LocalDate.of(2013, 2, 12);
        vacations[index++] = LocalDate.of(2013, 2, 13);
        vacations[index++] = LocalDate.of(2013, 2, 14);
        vacations[index++] = LocalDate.of(2013, 2, 15);
        vacations[index++] = LocalDate.of(2013, 4, 4);
        vacations[index++] = LocalDate.of(2013, 4, 5);
        vacations[index++] = LocalDate.of(2013, 4, 29);
        vacations[index++] = LocalDate.of(2013, 4, 30);
        vacations[index++] = LocalDate.of(2013, 5, 1);
        vacations[index++] = LocalDate.of(2013, 6, 10);
        vacations[index++] = LocalDate.of(2013, 6, 11);
        vacations[index++] = LocalDate.of(2013, 6, 12);
        vacations[index++] = LocalDate.of(2013, 9, 19);
        vacations[index++] = LocalDate.of(2013, 9, 20);
        vacations[index++] = LocalDate.of(2013, 10, 1);
        vacations[index++] = LocalDate.of(2013, 10, 2);
        vacations[index++] = LocalDate.of(2013, 10, 3);
        vacations[index++] = LocalDate.of(2013, 10, 4);
        vacations[index++] = LocalDate.of(2013, 10, 7);
        //-----------------------------------------------------------------------

        //-----------------2014年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2014, 1, 1);
        vacations[index++] = LocalDate.of(2014, 1, 2);
        vacations[index++] = LocalDate.of(2014, 1, 3);
        vacations[index++] = LocalDate.of(2014, 1, 31);
        vacations[index++] = LocalDate.of(2014, 2, 3);
        vacations[index++] = LocalDate.of(2014, 2, 4);
        vacations[index++] = LocalDate.of(2014, 2, 5);
        vacations[index++] = LocalDate.of(2014, 2, 6);
        vacations[index++] = LocalDate.of(2014, 4, 7);
        vacations[index++] = LocalDate.of(2014, 5, 1);
        vacations[index++] = LocalDate.of(2014, 5, 2);
        vacations[index++] = LocalDate.of(2014, 9, 8);
        vacations[index++] = LocalDate.of(2014, 10, 1);
        vacations[index++] = LocalDate.of(2014, 10, 2);
        vacations[index++] = LocalDate.of(2014, 10, 3);
        vacations[index++] = LocalDate.of(2014, 10, 6);
        vacations[index++] = LocalDate.of(2014, 10, 7);
        //-----------------------------------------------------------------------

        //-----------------2015年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2015, 1, 1);
        vacations[index++] = LocalDate.of(2015, 1, 2);
        vacations[index++] = LocalDate.of(2015, 2,18);
        vacations[index++] = LocalDate.of(2015, 2,19);
        vacations[index++] = LocalDate.of(2015, 2,20);
        vacations[index++] = LocalDate.of(2015, 2,23);
        vacations[index++] = LocalDate.of(2015, 2,24);
        vacations[index++] = LocalDate.of(2015, 4,6);
        vacations[index++] = LocalDate.of(2015, 5,1);
        vacations[index++] = LocalDate.of(2015, 6,22);
        vacations[index++] = LocalDate.of(2015, 10,1);
        vacations[index++] = LocalDate.of(2015, 10,2);
        vacations[index++] = LocalDate.of(2015, 10,5);
        vacations[index++] = LocalDate.of(2015, 10,6);
        vacations[index++] = LocalDate.of(2015, 10,7);
        //-----------------------------------------------------------------------

        //-----------------2016年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2016, 1, 1);
        vacations[index++] = LocalDate.of(2016, 2, 8);
        vacations[index++] = LocalDate.of(2016, 2, 9);
        vacations[index++] = LocalDate.of(2016, 2, 10);
        vacations[index++] = LocalDate.of(2016, 2, 11);
        vacations[index++] = LocalDate.of(2016, 2, 12);
        vacations[index++] = LocalDate.of(2016, 4, 4);
        vacations[index++] = LocalDate.of(2016, 5, 2);
        vacations[index++] = LocalDate.of(2016, 6, 9);
        vacations[index++] = LocalDate.of(2016, 6, 10);
        vacations[index++] = LocalDate.of(2016, 9, 15);
        vacations[index++] = LocalDate.of(2016, 9, 16);
        vacations[index++] = LocalDate.of(2016, 10, 3);
        vacations[index++] = LocalDate.of(2016, 10, 4);
        vacations[index++] = LocalDate.of(2016, 10, 5);
        vacations[index++] = LocalDate.of(2016, 10, 6);
        vacations[index++] = LocalDate.of(2016, 10, 7);
        //-----------------------------------------------------------------------

        //-----------------2017年休市日(去除周末)------------------------------
        vacations[index++] = LocalDate.of(2017, 1, 2);
        vacations[index++] = LocalDate.of(2017, 1, 27);
        vacations[index++] = LocalDate.of(2017, 1, 30);
        vacations[index++] = LocalDate.of(2017, 1, 31);
        vacations[index++] = LocalDate.of(2017, 2, 1);
        vacations[index++] = LocalDate.of(2017, 2, 2);
        vacations[index++] = LocalDate.of(2017, 4, 4);
        vacations[index++] = LocalDate.of(2017, 4, 5);
        vacations[index++] = LocalDate.of(2017, 5, 1);
        vacations[index++] = LocalDate.of(2017, 5, 2);
        vacations[index++] = LocalDate.of(2017, 5, 3);
        vacations[index++] = LocalDate.of(2017, 5, 29);
        vacations[index++] = LocalDate.of(2017, 5, 30);
        vacations[index++] = LocalDate.of(2017, 10, 2);
        vacations[index++] = LocalDate.of(2017, 10, 3);
        vacations[index++] = LocalDate.of(2017, 10, 4);
        vacations[index++] = LocalDate.of(2017, 10, 5);
        vacations[index++] = LocalDate.of(2017, 10, 6);
        //-----------------------------------------------------------------------
    }

    public boolean isVacation(LocalDate date) {
        int a = Arrays.binarySearch(vacations, date);
        return a >= 0;
    }
}
