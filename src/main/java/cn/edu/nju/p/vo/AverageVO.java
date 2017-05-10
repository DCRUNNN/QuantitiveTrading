package cn.edu.nju.p.vo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 均线画图需要的数据
 */
public class AverageVO {

    private List<Map<LocalDate,Double>> averageLines;

    private String stockCode;
    private String stockName;

    public AverageVO(List<Map<LocalDate, Double>> averageLines, String stockCode, String stockName) {
        this.averageLines = averageLines;
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

    public List<Map<LocalDate, Double>> getAverageLines() {
        return averageLines;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    @Override
    public String toString() {
        return "AverageVO{" +
                "averageLines=" + averageLines +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                '}';
    }
}
