package cn.edu.nju.p.dao;


import cn.edu.nju.p.po.StockPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface StockDao {

	/**
	 * 通过股票代码和查询日期得到一个股票PO
	 * @param code 股票代码
	 * @param date 查询的日期 格式是2017-03-09
	 * @return 若存在符合条件的股票PO则返回该PO，否则返回null
	 */
	@Select(value = "SELECT * FROM t_stock_" + "${date.getYear()}" + " WHERE code=#{code} AND date=#{date}")
	StockPO getStockPO(@Param("code") String code, @Param("date") LocalDate date);

	/**
	 * 得到该股票当天的开盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的开盘价
	 */
	@Select(value = "SELECT open FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	@Cacheable("getStockOpen")
	Double getStockOpen(@Param("code") String code, @Param("date") LocalDate date) ;

	/**
	 * 得到该股票当天的最高价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的最高价
	 */
	@Select(value="SELECT high FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockHigh(@Param("code") String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天的最低价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的最低价
	 */
	@Select(value="SELECT low FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockLow(@Param("code") String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天的收盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的收盘价
	 */
//
	@Cacheable("getStockClose") // use concurrent internal storage as cache to accelerate the calculation
	@Select(value="SELECT close FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockClose(@Param("code") String code, @Param("date") LocalDate date) ;
	
	/**
	 * 得到该股票当天的成交量
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的成交量
	 */
	@Select(value="SELECT volume FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	@Cacheable("getVolumes")
	Integer getStockVolume(@Param("code")String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天复盘后的收盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天复盘后的收盘价
	 */
	@Select(value="SELECT adj_close FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockAdjClose(@Param("code") String code, @Param("date") LocalDate date);
		
	/**
	 * 得到该股票的名字
	 * @param code 代码
	 * @return 股票名字
	 */
	@Select(value="SELECT name FROM t_stock_2011 WHERE code=#{code} limit 1")
	String getStockName(String code);
	
	/**
	 * 得到该股票的市场名称
	 * @param code 股票代码 不含市场
	 * @return 市场名称
	 */
	@Select(value="SELECT market FROM t_stock_2011 WHERE code=#{code} limit 1")
	String getStockMarket(String code);

	/**
	 * 根据股票名称得到股票的代码
	 * @param name 股票名称
	 * @return 股票代码
	 */
	@Select(value="SELECT code FROM t_stock_2011 WHERE name=#{name} limit 1")
	String getStockCode(String name);

	/**
	 * 根据查询日期得到当天所有股票的PO
	 * @param date 查询日期
	 * @return 当天所有股票PO
	 */
	@Select(value = "SELECT * FROM t_stock_" + "${date.split('-')[0]}" + " WHERE date=#{date}")
	@Results(
			value = {
			@Result(property = "date",column = "date",javaType = String.class,jdbcType = JdbcType.DATE),
			@Result(property = "open",column = "open",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "high",column = "high",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "low",column = "low",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "close",column = "close",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "volume",column = " volume",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
			@Result(property = "adj_close",column = "adj_close",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "code",column = "code",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "name",column = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "market",column = "market",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "time",column = "time",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "currentPrice",column = "current_price",javaType = Double.class,jdbcType = JdbcType.DOUBLE)
	})
	List<StockPO> getPOList(@Param("date") String date);

	/**
	 * 根据股票代码得到所在的股票板块
	 * @param code
	 * @return 股票所属板块
	 */
	String getStockSector(String code);
	
	/**
	 * 获取某个板块内的所有股票
	 * @param sector 板块
	 * @return 该板块内的股票 以list返回
	 */
	List<String> getStockBySector(String sector);

	
	/**
	 * 返回所有股票
	 * @return all the stock codes
	 */
	@Select(value="SELECT distinct code from t_stock_2016")
	@Cacheable("allStocks")
	List<String> getAllStocks();

	@Insert("INSERT INTO t_stock_"+"${year}"+"(date,open,high,low,close,volume,adj_close,code,name,market,time,current_price)" +
			" VALUES(#{StockPO.date},#{StockPO.open},#{StockPO.high},#{StockPO.low},#{StockPO.close},#{StockPO.volume},#{StockPO.adj_close},#{StockPO.code},#{StockPO.name},#{StockPO.market},#{StockPO.time},#{StockPO.currentPrice})")
	void insertIntoStockDatabase(@Param("year") String year,@Param("StockPO") StockPO po) throws SQLException;
}
