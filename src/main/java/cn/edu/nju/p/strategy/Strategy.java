package cn.edu.nju.p.strategy;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-6-13.
 */
public interface Strategy {

    List<String> stockPool();

    List<String> getWinner();

    /**
     * 初始化参数
     * @param beginDate　开始日期
     * @param endDate　结束日期
     * @param holdingDay　持有期
     */
    void initParam(LocalDate beginDate, LocalDate endDate, int holdingDay);
}
