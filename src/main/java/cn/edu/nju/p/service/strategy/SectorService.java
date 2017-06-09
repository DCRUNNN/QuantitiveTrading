package cn.edu.nju.p.service.strategy;

import java.util.List;

/**
 * 板块相关的service
 */
public interface SectorService {


    /**
     * get all string info of a sector
     * @param sector the sector name
     * @return
     */
    List<String> getStocks(String sector);

    /**
     * get all stocks ,simply get a string formatted like "stockname+'|'+stockcode"
     * @return stockinfo list
     */
    List<String> getAllStocks();

    /**
     *
     */

    String getStockName(String code);
}
