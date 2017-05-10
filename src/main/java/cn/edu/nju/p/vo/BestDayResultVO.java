package cn.edu.nju.p.vo;

import java.util.Map;

/**
 * Created by xihao on 17-4-3.
 */
public class BestDayResultVO {

    private Map<Integer,Double> abnormalReturns; //超额收益
    private Map<Integer,Double> filedWins; //策略胜率

    public BestDayResultVO(Map<Integer, Double> abnormalReturns, Map<Integer, Double> filedWins) {
        this.abnormalReturns = abnormalReturns;
        this.filedWins = filedWins;
    }

    public Map<Integer, Double> getAbnormalReturns() {
        return abnormalReturns;
    }

    public void setAbnormalReturns(Map<Integer, Double> abnormalReturns) {
        this.abnormalReturns = abnormalReturns;
    }

    public Map<Integer, Double> getFiledWins() {
        return filedWins;
    }

    public void setFiledWins(Map<Integer, Double> filedWins) {
        this.filedWins = filedWins;
    }

    @Override
    public String toString() {
        return "BestDayResultVO{" +
                "abnormalReturns=" + abnormalReturns +
                ", filedWins=" + filedWins +
                '}';
    }
}
