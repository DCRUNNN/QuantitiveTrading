package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.Personnel.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pc on 2017/6/6.
 */
@RestController
@RequestMapping("/personnel")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/{userId}")
    public BaseResult getHistory(@PathVariable String userId){
        List<String> list=historyService.getHistory(userId);
        return new BaseResult<>(0, list);
    }


}
