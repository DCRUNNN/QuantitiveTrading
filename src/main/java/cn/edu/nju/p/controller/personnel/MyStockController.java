package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.Personnel.HistoryService;
import cn.edu.nju.p.service.Personnel.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseResult getMyStock(String userId){
        List<String> list = myStockService.getMyStock(userId);
        return new BaseResult<>(0, list);
    }

}
