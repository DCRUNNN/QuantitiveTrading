package cn.edu.nju.p.strategy;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-6-13.
 */
public interface Strategy {

    List<String> setStockPool();

    List<String> setWinner(LocalDate beginDate, LocalDate endDate, List<String> stockPool);

    LocalDate setBeginDate();

    LocalDate setEndDate();

    int setHoldingDay();

    int setFormativeDayNum();

}

