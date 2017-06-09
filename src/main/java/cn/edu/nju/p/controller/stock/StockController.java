package cn.edu.nju.p.controller.stock;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.enums.ErrorCode;
import cn.edu.nju.p.service.strategy.SectorService;
import cn.edu.nju.p.utils.StockHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/stock")
    public BaseResult getSectorStocks(@RequestParam String sector) {
        List<String> stocks = sectorService.getStocks(sector);
        return new BaseResult<>(0, stocks);
    }

    @GetMapping("/check/{code}")
    public BaseResult checkStock(@PathVariable String code) {
        if (StockHelper.codeExists(code)) {
            return new BaseResult<>(0, true);
        } else {
            return new BaseResult<>(ErrorCode.STOCK_NOT_FOUND.getErrorCode(), code + " not found!");
        }
    }

    @GetMapping("/stockName/{code}")
    public BaseResult getStockName(@PathVariable String code){
        String name = sectorService.getStockName(code);
        return new BaseResult<>(0, name);
    }
}

