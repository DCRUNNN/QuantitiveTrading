package cn.edu.nju.p.controller.personnel;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.MyStrategyDao;
import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.dao.StrategySquareDao;
import cn.edu.nju.p.po.StrategyCodePO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by pc on 2017/6/14.
 */
@RestController
@RequestMapping("/strategy")
public class MyStrategyController {

    @Autowired
    private MyStrategyDao myStrategyDao;

    @Autowired
    private StrategySquareDao squareDao;

    @GetMapping("/{phoneNumber}")
    public BaseResult getMyStrategy(@PathVariable String phoneNumber){

        List<String> strategyName = myStrategyDao.getMyStrategyName(phoneNumber);
        return new BaseResult<>(0, strategyName);
    }

    @GetMapping("/detail/{phoneNumber}")
    public BaseResult getMyStrategyDetail(@PathVariable String phoneNumber) {

        List<StrategyCodePO> codePOS = myStrategyDao.getMyStrategy(phoneNumber);
        return new BaseResult<>(0, codePOS);
    }

    @PostMapping
    public BaseResult addAStrategy(@RequestBody JSONObject strategyPo) {

        String phoneNumber = strategyPo.getString("phoneNumber");
        String code = strategyPo.getString("code");
        String strategyName = strategyPo.getString("strategyName");
        myStrategyDao.addMyStrategy(phoneNumber, code, strategyName);
        return new BaseResult(0, "add a strategy sucessfully!");
    }

    @PostMapping("/deleteStrategy")
    public BaseResult deleteStock(@RequestParam String phoneNumber, @RequestParam String strategyName) {
        myStrategyDao.deleteStrategy(phoneNumber, strategyName);
        return new BaseResult<>(0, "删除成功!");
    }

    @GetMapping
    public BaseResult getStrategyCode(@RequestParam String phoneNumber, @RequestParam String strategyName) {

        String code = myStrategyDao.getCode(phoneNumber, strategyName);
        return new BaseResult(0, code);
    }


}
