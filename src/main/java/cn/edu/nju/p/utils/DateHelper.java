package cn.edu.nju.p.utils;


import cn.edu.nju.p.exception.DateNotOrderedException;
import cn.edu.nju.p.utils.holiday.VacationDates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * 日期辅助类
 */
public class DateHelper {

    /**
     * 判断输入的两个日期是否符合前面小于后面的要求
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 开始日期在结束日期之前返回true，否则false
     */
    private static boolean isOkDate(LocalDate beginDate, LocalDate endDate) {

        if(beginDate.isBefore(endDate)){
            return true;
        }
        //不符合格式的话抛出异常
        DateNotOrderedException dateNotOrderedException =  new DateNotOrderedException(beginDate+"不是在"+endDate+"之前");
        throw new RuntimeException(dateNotOrderedException);
    }


    /**
     * 返回当前日期的上一个日期
     * @param date 日期
     * @param predicate 日期过滤的条件（没有的话可以传递a->true）
     * @return 上一个不是周末的（有效日期）日期
     */
    public static LocalDate getLastDate(LocalDate date,Predicate<LocalDate> predicate) {
        date = date.plusDays(-1);
        VacationDates vacationDates = new VacationDates();
        while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || vacationDates.isVacation(date) ||
                !predicate.test(date)) {
            date = date.plusDays(-1);
        }
        return date;
    }

    /**
     * 获取去除周末的前interval个有效日期
     * @param sourceDate 当前日期
     * @param interval 间隔
     * @return 间隔前的有效日期
     */
    public static LocalDate getIntervalEffectiveDate(LocalDate sourceDate,int interval){

        LocalDate result = sourceDate;
        for (int i = 0; i < interval; i++) {
            result = getLastDate(result, a -> true);
        }
        return result;
    }

}
