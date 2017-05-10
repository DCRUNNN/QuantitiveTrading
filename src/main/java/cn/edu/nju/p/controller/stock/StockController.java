package cn.edu.nju.p.controller.stock;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.service.strategy.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * the stock controller
 */
@RestController
public class StockController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private StockDao stockDao;

    @GetMapping("/stock")
    public BaseResult getSectorStocks(@RequestParam String sector) {
        List<String> stocks = sectorService.getStocks(sector);
        return new BaseResult<>(stocks);
    }


}
