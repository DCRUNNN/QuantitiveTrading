package cn.edu.nju.p.po;

import java.util.ArrayList;

/**
 * CompareVO 比较两只股票时候使用的股票信息
 * highest 一段日期之间的最高指数
 * lowest 一段日期的最低指数
 * hasIncreased 这段时间之内是否上涨
 * amountOfChange 涨幅/跌幅
 * closeList 每日的收盘指数
 * logList 每日的对数收益率
 * squareDifference 这段时间的收益率方差
 */
public class StockComparedPO {
	 private String stockCode;
	 private String stockName;
	 private double highest;
	 private double lowest;
	 private boolean hasIncreased;
	 private double amountOfChange;
	 private ArrayList<Double> closeList;
	 private ArrayList<Double> logList;
	 private double squareDifference;
	 
	 /**
	  * 
	  * @param stockCode 股票代码
	  * @param stockName 股票名称
	  * @param highest 最高支书
	  * @param lowest 最低指数
	  * @param hasIncreased 是否上涨
	  * @param amountOfChange 涨幅/跌幅
	  * @param closeList 每日收盘指数
	  * @param logList 每日对数收益率
	  * @param squareDifference 对数收益率方差
	  */
	 public StockComparedPO(String stockCode,String stockName,double highest, double lowest, boolean hasIncreased, double amountOfChange, ArrayList<Double> closeList, ArrayList<Double> logList, double squareDifference){
		 this.stockCode = stockCode;
	     this.stockName = stockName;
	     this.highest = highest;
	     this.lowest = lowest;
	     this.hasIncreased = hasIncreased;
	     this.amountOfChange = amountOfChange;
	     this.closeList = closeList;
	     this.logList = logList;
	     this.squareDifference = squareDifference;
	 }
	 
	 public double getHighest() {
		 return highest;
	 }

	 public double getLowest() {
		 return lowest;
	 }

	 public boolean isHasIncreased() {
	     return hasIncreased;
	 }

	 public double getAmountOfChange() {
		 return amountOfChange;
	 }

	 public ArrayList<Double> getCloseList() {
		 return closeList;
	 }
	 
	 public ArrayList<Double> getLogList() {
		 return logList;
	 }

	 public double getSquareDifference() {
		 return squareDifference;
	 }

	 public String getStockCode() {
		 return stockCode;
	 }

	 public String getStockName() {
		 return stockName;
	 }
	 
	 @Override
	 public String toString() {
	     return "StockComparedVO{" +
	             "stockCode='" + stockCode + '\'' +
	             ", stockName='" + stockName + '\'' +
	             ", highest=" + highest +
	             ", lowest=" + lowest +
	             ", hasIncreased=" + hasIncreased +
	             ", amountOfChange=" + amountOfChange +
	             ", closeList=" + closeList +
	             ", logList=" + logList +
	             ", squareDifference=" + squareDifference +
	             '}';
	 }
	 
}