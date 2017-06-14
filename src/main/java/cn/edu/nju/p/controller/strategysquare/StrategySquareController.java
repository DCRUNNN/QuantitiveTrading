package cn.edu.nju.p.controller.strategysquare;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.MyStrategyDao;
import cn.edu.nju.p.dao.StrategySquareDao;
import cn.edu.nju.p.po.StrategyCodePO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dell- on 2017/6/15.
 */
@RestController
@RequestMapping("/strategysquare")
public class StrategySquareController {

    @Autowired
    private MyStrategyDao myStrategyDao;

    @Autowired
    private StrategySquareDao squareDao;

    @PostMapping("/square")
    public BaseResult shareStrategy(@RequestBody JSONObject strategyPo) {
        String phoneNumber = strategyPo.getString("phoneNumber");
//        String code = strategyPo.getString("code");
        String strategyName = strategyPo.getString("strategyName");
        String code= myStrategyDao.getCode(phoneNumber,strategyName);
        squareDao.shareStrategy(phoneNumber, strategyName,code);
        return new BaseResult(0, "share a strategy sucessfully!");
    }

    @GetMapping
    public BaseResult getAllStrategy() {
        List<StrategyCodePO> codePOS = squareDao.getAllStrategy();
        return new BaseResult<>(0, codePOS);
    }

    @PostMapping("/deleteStrategy")
    public BaseResult deleteStock(@RequestParam String phoneNumber, @RequestParam String strategyName) {
        squareDao.deleteStrategy(phoneNumber, strategyName);
        return new BaseResult<>(0, "删除成功!");
    }
}
