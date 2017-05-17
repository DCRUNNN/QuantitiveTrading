package cn.edu.nju.p.dao;


import cn.edu.nju.p.annotation.StockNotFoundCheck;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.po.StockPO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StockDao {

	/**
	 * 通过股票代码和查询日期得到一个股票PO
	 *
	 * @param code 股票代码
	 * @param date 查询的日期 格式是2017-03-09
	 * @return 若存在符合条件的股票PO则返回该PO，否则返回null
	 */
/*	@Results(id = "stockpo", value = {
			@Result(id=true,property = "date",column = "date",javaType = String.class,jdbcType = JdbcType.DATE),
			@Result(property = "open",column = "open",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "high",column = "high",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "low",column = "low",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "close",column = "close",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(property = "volume",column = " volume",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
			@Result(property = "adjClose",column = "adj_close",javaType = Double.class,jdbcType = JdbcType.DOUBLE),
			@Result(id=true,property = "code",column = "code",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "name",column = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "market",column = "market",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "time",column = "time",javaType = String.class,jdbcType = JdbcType.VARCHAR),
			@Result(property = "currentPrice",column = "currentprice",javaType = Double.class,jdbcType = JdbcType.DOUBLE)
	})*/
	@Select(value = "SELECT * FROM t_stock_" + "${date.getYear()}" + " WHERE code=#{code} AND date=#{date}")
	@StockNotFoundCheck
	StockPO getStockPO(@Param("code") String code, @Param("date") LocalDate date);

	public List<StockPO> findStockPOByMap(Map<String, LocalDate> params);

	/**
	 * 得到该股票当天的开盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的开盘价
	 */
	@StockNotFoundCheck
	@Select(value = "SELECT open FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockOpen(@Param("code") String code, @Param("date") LocalDate date) ;

	/**
	 * 得到该股票当天的最高价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的最高价
	 */
	@StockNotFoundCheck
	@Select(value="SELECT high FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockHigh(@Param("code") String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天的最低价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的最低价
	 */
	@StockNotFoundCheck
	@Select(value="SELECT low FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockLow(@Param("code") String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天的收盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的收盘价
	 */
	@StockNotFoundCheck
	@Select(value="SELECT close FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockClose(@Param("code") String code, @Param("date") LocalDate date) ;
	
	/**
	 * 得到该股票当天的成交量
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天的成交量
	 */
	@StockNotFoundCheck
	@Select(value="SELECT volume FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Integer getStockVolume(@Param("code")String code, @Param("date") LocalDate date);
	
	/**
	 * 得到该股票当天复盘后的收盘价
	 * @param code 股票代码和市场名称 eg 000001.SZ
	 * @param date 查询日期
	 * @return 该股票当天复盘后的收盘价
	 */
	@StockNotFoundCheck
	@Select(value="SELECT adj_close FROM t_stock_"+"${date.getYear()}"+" WHERE code=#{code} AND date=#{date}")
	Double getStockAdjClose(@Param("code") String code, @Param("date") LocalDate date);
		
	/**
	 * 得到该股票的名字
	 * @param code 代码
	 * @return 股票名字
	 */
	@StockNotFoundCheck
	@Select(value="SELECT name FROM t_stock_2011 WHERE code=#{code} limit 1")
	String getStockName(String code);
	
	/**
	 * 得到该股票的市场名称
	 * @param code 股票代码 不含市场
	 * @return 市场名称
	 */
	@StockNotFoundCheck
	@Select(value="SELECT market FROM t_stock_2011 WHERE code=#{code} limit 1")
	String getStockMarket(String code);

	/**
	 * 根据股票名称得到股票的代码
	 * @param name 股票名称
	 * @return 股票代码
	 */
	@StockNotFoundCheck
	@Select(value="SELECT code FROM t_stock_2011 WHERE name=#{name} limit 1")
	String getStockCode(String name);

	/**
	 * 根据股票名称得到当天股票的收盘价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票收盘价
	 */
	@Select(value="SELECT close FROM t_stock_"+"${date.getYear()}"+" WHERE name=#{name} AND date=#{date}")
	Double getStockCloseByName(@Param("name") String name, @Param("date") LocalDate date) ;

	
	/**
	 * 根据股票名称得到当天股票的最低价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票最低价
	 */
	@Select(value="SELECT low FROM t_stock_"+"${date.getYear()}"+" WHERE name=#{name} AND date=#{date}")
	Double getStockLowByName(@Param("name") String name, @Param("date") LocalDate date);

	/**
	 * 根据股票名称得到当天股票最高价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票最高价
	 */
	@Select(value="SELECT high FROM t_stock_"+"${date.getYear()}"+" WHERE name=#{name} AND date=#{date}")
	Double getStockHighByName(@Param("name") String name, @Param("date") LocalDate date);

	/**
	 * 根据股票名称得到当天股票开盘价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票开盘价
	 */
	@Select(value="SELECT open FROM t_stock_"+"${date.getYear()}"+" WHERE name=#{name} AND date=#{date}")
	Double getStockOpenByName(@Param("name") String name, @Param("date") LocalDate date);

	/**
	 * 根据股票名称得到当天股票成交量
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票成交量
	 */
	@Select(value="SELECT volume FROM t_stock_"+"${date.getYear()}"+" WHERE name=#{name} AND date=#{date}")
	Integer getStockVolumeByName(@Param("name") String name, @Param("date") LocalDate date);

	/**
	 * 根据查询日期得到当天所有股票的PO
	 * @param date 查询日期
	 * @return 当天所有股票PO
	 */

	List<StockPO> getPOList(String date);

	/**
	 * 获取所有有数据股票的股票代码
	 * @return 股票代码的列表
	 */
	@Select(value="SELECT distinct code from t_stock_2016")
	List<String> getPOCodes();
	
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
	 * @return 以name,code的形式返回
	 */
	List<String> getAllStocks();

	@Insert("INSERT INTO t_stock_"+"${year}"+"(date,open,high,low,close,volume,adj_close,code,name,market,time,currentprice)" +
			" VALUES(#{StockPO.date},#{StockPO.open},#{StockPO.high},#{StockPO.low},#{StockPO.close},#{StockPO.volume},#{StockPO.adjClose},#{StockPO.code},#{StockPO.name},#{StockPO.market},#{StockPO.time},#{StockPO.currentPrice})")
	void insertIntoStockDatabase(@Param("year") String year,@Param("StockPO") StockPO po) throws SQLException;
}
