package cn.edu.nju.p.utils.holiday;

import cn.edu.nju.p.cache.RedisCache;
import cn.edu.nju.p.dao.StockDao;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by xihao on 17-6-13.
 */
@Component
public class Holidays {

    private StockDao stockDao;

    private RedisCache redisCache;

    private static final String VERTIFICATION = "000002"; //使用万科A来获取所有的假日

    private List<LocalDate> holidays;

    @Autowired
    public Holidays(StockDao stockDao,RedisCache redisCache){

        this.stockDao = stockDao;
        this.redisCache = redisCache;

        holidays = new ArrayList<>();
//        initRedis(); //把所有节假日数据插入redis
        initHoliday();
//        initNotHoliday(); //把所有非节假日数据插入redis
    }

    private void initNotHoliday() {
        List<String> allValidDates = new ArrayList<>();
        LocalDate beginDate = LocalDate.of(2006, 1, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 12);
        while (beginDate.isBefore(endDate) || beginDate.isEqual(endDate)) {
            //beginDate <= endDate
            if (!isHoliday(beginDate)) {
                allValidDates.add(beginDate.toString());
                System.out.println("add a valid date ---->" + beginDate.toString());
            }
            beginDate = beginDate.plusDays(1);
        }
        allValidDates.addAll(Arrays.asList("2017-06-13","2017-06-14","2017-06-15","2017-06-16"));
        String jsonValidDates = JSON.toJSONString(allValidDates);
        System.out.println(jsonValidDates);
        redisCache.putCache("validDates", jsonValidDates);
    }

    /**
     * 直接获取holidays ----> json
     */
    private void initHoliday(){
        System.out.println("initing holidays--------");
        String holidayJson = redisCache.getCache("holidays",String.class);
        holidays = JSON.parseArray(holidayJson, LocalDate.class);
        System.out.println("holidays init over");
    }

    /**
     * 往redis中插入holidays --> List<LocalDate>.json
     */
    private void initRedis(){

        LocalDate beginDate = LocalDate.of(2006, 1, 1);
        LocalDate endDate = LocalDate.of(2017, 6, 12);

        while (beginDate.isBefore(endDate)) {

            if (stockDao.getStockPO(VERTIFICATION, beginDate) == null) {
                //数据库没有数据就是假日
                System.out.println("add a holiday --------> " + beginDate.toString());
                holidays.add(beginDate);
            }
            beginDate = beginDate.plusDays(1);
        }

        String jsonHoliday = JSON.toJSONString(holidays);
        System.out.println(jsonHoliday);
        redisCache.putCache("holidays", jsonHoliday);
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

    public List<LocalDate> getBetweenDatesAndFilter(LocalDate beginDate, LocalDate endDate, Predicate<LocalDate> filterCondition) {

        List<LocalDate> dateList = new ArrayList<>();
        while (beginDate.isBefore(endDate)) {
            dateList.add(beginDate);
            beginDate = beginDate.plusDays(1);
        }
        dateList.add(endDate);

        Predicate<LocalDate> notHoliday = localDate -> !isHoliday(localDate);

        dateList = dateList.parallelStream()
                .filter(notHoliday.and(filterCondition))
                .sorted()
                .collect(Collectors.toList());

        return dateList;
    }

    /**
     * 获取dayNum天前的有效日期
     * @param date
     * @param dayNum
     * @return
     */
    public LocalDate getIntervalEffectiveDate(LocalDate date, int dayNum) {

        LocalDate lastDate = null;
        for (int i = 0; i < dayNum; i++) {
            lastDate = date.minusDays(1);
            while (holidays.contains(lastDate)) {
                lastDate = lastDate.minusDays(1);
            }
        }
        return lastDate;
    }

    /**
     * 获得上一个有效日期
     * @param date 当前日期
     * @return
     */
    public LocalDate getLastValidDate(LocalDate date) {
        LocalDate lastDate = date.minusDays(1);
        while (holidays.contains(lastDate)) {
            lastDate = lastDate.minusDays(1);
        }
        return lastDate;
    }
}
