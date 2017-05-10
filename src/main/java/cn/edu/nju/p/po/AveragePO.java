package cn.edu.nju.p.po;

import java.time.LocalDate;
import java.util.List;

public class AveragePO {

	 private List<Double> closeList;
	 private List<LocalDate> validDates;
	 private String stockCode;
	 private String stockName;

	 public AveragePO(String stockCode,String stockName){
		 this.stockCode=stockCode;
		 this.stockName=stockName;
	 }
	 
	 public String getStockCode(){
		 return stockCode;
	 }
	 
	 public void setStockCode(String stockCode){
		 this.stockCode=stockCode;
	 }
	 
	 public String getStockName(){
		 return stockName;
	 }
	 
	 public void setStockName(String stockName){
		 this.stockName=stockName;
	 }
	 
	 public List<Double> getCloseList(){
		 return closeList;
	 }
	 
	 public List<LocalDate> getValidDates(){
		 return validDates;
	 }
	 
	 @Override
	    public String toString() {
	        return "AveragePO{" +
	                "closeList=" + closeList +
	                ", validDates=" + validDates +
	                ", stockCode='" + stockCode + '\'' +
	                ", stockName='" + stockName + '\'' +
	                '}';
	    }
}
