package cn.edu.nju.p.utils;


import cn.edu.nju.p.exception.DateNotOrderedException;
import org.springframework.cache.annotation.Cacheable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param predicate 手动输入的对去掉周末之后的日期的筛选条件
     * @return 返回去掉周末的日期列表
     */
    public static List<LocalDate> getBetweenDateAndFilter(LocalDate beginDate, LocalDate endDate,Predicate<LocalDate> predicate) {

        List<LocalDate> dateList = new ArrayList<>();

        //获得输入日期之间的所有日期
        if(isOkDate(beginDate,endDate)){
            while (beginDate.isBefore(endDate)) {
                dateList.add(beginDate);
                beginDate = beginDate.plusDays(1);
            }
            dateList.add(endDate);
        }

        //过滤周末日期
        Predicate<LocalDate> notWeekend = localDate -> !(localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY);

        //直接在此处对dateList进行过滤
        return dateList.stream()
                .filter(notWeekend.and(predicate))
                .collect(Collectors.toList());
    }

    /**
     * 返回当前日期的上一个日期
     * @param date 日期
     * @param predicate 日期过滤的条件（没有的话可以传递a->true）
     * @return 上一个不是周末的（有效日期）日期
     */
    public static LocalDate getLastDate(LocalDate date,Predicate<LocalDate> predicate) {

        date = date.plusDays(-1);
        while (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                !predicate.test(date)){
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

    /**
     * 获取两个日期之间的日期天数，包括周末
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 天数
     */
    public static int getBetweenDayNum(LocalDate beginDate,LocalDate endDate) {

        if (isOkDate(beginDate, endDate)) {

            int days = 0;
            while (beginDate.isBefore(endDate) || beginDate.isEqual(endDate)) {
                days++;
                beginDate = beginDate.plusDays(1);
            }
            return days;
        }

        return 0;
    }
}
