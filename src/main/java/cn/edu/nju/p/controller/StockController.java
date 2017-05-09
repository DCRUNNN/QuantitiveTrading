package cn.edu.nju.p.controller;

import cn.edu.nju.p.po.StockPo;
import cn.edu.nju.p.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * Created by xihao on 17-5-9.
 */
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock/{stockCode}")
    public StockPo stockInfo(@PathVariable String stockCode){
        return stockService.getStockInfo(stockCode, null);
    }
}
