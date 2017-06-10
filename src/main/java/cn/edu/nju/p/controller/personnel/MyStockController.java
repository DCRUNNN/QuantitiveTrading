package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.Personnel.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */
@RestController
@RequestMapping("/personnel")

public class MyStockController {

    @Autowired
    private MyStockService myStockService;

    @GetMapping("/mystock/{userId}")
    public BaseResult getMyStock(@PathVariable String userId){
        List<String> list = myStockService.getMyStock(userId);
        return new BaseResult<>(0, list);
    }

    @GetMapping("/addStock")
    public BaseResult addStock(@RequestParam String userId,@RequestParam String stockCode){

        myStockService.addStock(userId,stockCode);
        return new BaseResult<>(0, "插入成功！");
    }

    @GetMapping("/deleteStock")
    public BaseResult deleteStock(@RequestParam String phoneNumber, @RequestParam String stockCode) {
        myStockService.deleteStock(phoneNumber, stockCode);
        return new BaseResult<>(0, "删除成功!");
    }

}
