package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.exception.SectorNotExistException;
import cn.edu.nju.p.service.strategy.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * sectorService的实现类
 */
@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private StockDao stockDao;

    @Override
    public List<String> getStocks(String sector) {

        boolean isSectorExist = "创业板".equals(sector) || "中小板".equals(sector) || "主板".equals(sector);
        if (!isSectorExist) {
            throw new SectorNotExistException("Sector " + sector + " does not exist");
        }
        return stockDao.getStockBySector(sector);
    }

    @Override
    public List<String> getAllStocks() {
        return stockDao.getAllStocks();
    }
}
