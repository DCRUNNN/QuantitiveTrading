package cn.edu.nju.p.vo;

import cn.edu.nju.p.po.KLinePO;

import java.time.LocalDate;
import java.util.List;


/**
 * KLine需要使用到的VO
 * name 股票名称
 * code 股票代码
 * date 日期
 * isGoHigh 是否走高
 * low 最低指数
 * high 最高指数
 * open 开盘指数
 * close 收盘指数
 * range 最高指数与最低指数之差
 * dBetweenOpenAndClose 开盘收盘指数之差的绝对值
 */
public class KLineVO {

    private String stockName;
    private List<List<Object>> klineData;

    public KLineVO(String stockName, List<List<Object>> klineData) {
        this.stockName = stockName;
        this.klineData = klineData;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public List<List<Object>> getKlineData() {
        return klineData;
    }

    public void setKlineData(List<List<Object>> klineData) {
        this.klineData = klineData;
    }

    @Override
    public String toString() {
        return "KLineVO{" +
                "stockName='" + stockName + '\'' +
                ", klineData=" + klineData +
                '}';
    }
}
