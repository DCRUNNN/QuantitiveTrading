package cn.edu.nju.p.strategy;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.utils.StockHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-6-13.
 */
public class MomentumStrategyImpl implements Strategy {

    @Autowired
    private StockHelper stockHelper;

    @Override
    public List<String> stockPool() {

        return stockHelper.getRecommendStock();
    }

    @Override
    public List<String> getWinner() {


        return null;
    }

    /**
     * 初始化参数
     *
     * @param beginDate  　开始日期
     * @param endDate    　结束日期
     * @param holdingDay 　持有期
     */
    @Override
    public void initParam(LocalDate beginDate, LocalDate endDate, int holdingDay) {


    }
}
