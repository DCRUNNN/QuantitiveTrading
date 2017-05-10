package cn.edu.nju.p.dao;


import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.po.StockPO;
import java.util.List;

public interface StockDao {

	/**
	 * 通过股票代码和查询日期得到一个股票PO
	 * @param code 股票代码
	 * @param date 查询的日期 格式是2017-03-09
	 * @return 若存在符合条件的股票PO则返回该PO，否则返回null
	 */
	StockPO getStockPO(String code, String date) throws StockNotFoundException;
	
	
	/**
	 * 通过股票代码得到一个股票PO
	 * @param code 股票代码
	 * @return 若存在符合条件的股票PO则返回该PO，否则返回null
	 */
	StockPO getStockPO(String code) throws StockNotFoundException;
	
	
	/**
	 * 得到该股票当天的开盘价
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天的开盘价
	 */
	double getStockOpen(String code, String date) throws StockNotFoundException;
	
	/**
	 * 得到该股票当天的最高价
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天的最高价
	 */
	double getStockHigh(String code, String date)throws StockNotFoundException;
	
	/**
	 * 得到该股票当天的最低价
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天的最低价
	 */
	double getStockLow(String code, String date)throws StockNotFoundException;
	
	/**
	 * 得到该股票当天的收盘价
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天的收盘价
	 */
	double getStockClose(String code, String date) throws StockNotFoundException;
	
	/**
	 * 得到该股票当天的成交量
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天的成交量
	 */
	int getStockVolumn(String code, String date)throws StockNotFoundException;
	
	/**
	 * 得到该股票当天复盘后的收盘价
	 * @param code 股票代码
	 * @param date 查询日期
	 * @return 该股票当天复盘后的收盘价
	 */
	double getStockAdjClose(String code, String date)throws StockNotFoundException;
		
	/**
	 * 得到该股票的名字
	 * @param code 代码
	 * @return 股票名字
	 */
	String getStockName(String code)throws StockNotFoundException;
	
	/**
	 * 得到该股票的市场名称
	 * @param code 股票代码
	 * @return 市场名称
	 */
	String getStockMarket(String code)throws StockNotFoundException;

	/**
	 * 根据股票名称得到股票的代码
	 * @param name 股票名称
	 * @return 股票代码
	 */
	String getStockCode(String name)throws StockNotFoundException;

	/**
	 * 根据股票名称得到当天股票的收盘价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票收盘价
	 */
	double getStockCloseByName(String name, String date) throws StockNotFoundException;

	
	/**
	 * 根据股票名称得到当天股票的最低价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票最低价
	 */
	double getStockLowByName(String name, String date)throws StockNotFoundException;

	/**
	 * 根据股票名称得到当天股票最高价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票最高价
	 */
	double getStockHighByName(String name, String date)throws StockNotFoundException;

	/**
	 * 根据股票名称得到当天股票开盘价
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票开盘价
	 */
	double getStockOpenByName(String name, String date)throws StockNotFoundException;

	/**
	 * 根据股票名称得到当天股票成交量
	 * @param name 股票名称
	 * @param date 查询日期
	 * @return 股票成交量
	 */
	int getStockVolumnByName(String name, String date)throws StockNotFoundException;

	/**
	 * 根据查询日期得到当天所有股票的PO
	 * @param date 查询日期
	 * @return 当天所有股票PO
	 */
	List<StockPO> getPOList(String date)throws StockNotFoundException;

	/**
	 * 获取所有有数据股票的股票代码
	 * @return 股票代码的列表
	 */
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
	
}
