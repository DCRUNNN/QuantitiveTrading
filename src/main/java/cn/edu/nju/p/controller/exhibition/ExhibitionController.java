package cn.edu.nju.p.controller.exhibition;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.exhibition.KLineService;
import cn.edu.nju.p.service.strategy.MeanReversionService;
import cn.edu.nju.p.service.strategy.MomentumService;
import cn.edu.nju.p.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by soft on 2017/5/17.
 */
@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {

    @Autowired
    private KLineService kLineService;

    @Autowired
    private MomentumService momentumService;

    @Autowired
    private MeanReversionService meanReversionService;

    @GetMapping("/kline/{code}")
    public BaseResult getKLineDataByCode(@PathVariable String code/*, @CookieValue(value = "phoneNumber", required = false) String phoneNumber
            */, @RequestParam String beginDate, @RequestParam String endDate) {

        LocalDate begin = LocalDate.parse(beginDate);
        LocalDate end = LocalDate.parse(endDate);
        KLineVO kLineVO = kLineService.getKLineVOSByCode(code, begin, end);
        return new BaseResult<>(0, kLineVO);
    }

    @GetMapping("/momentumStrategy")
    public BaseResult runMomentumStrategy(@RequestParam int dayNumFormative, @RequestParam int dayNumHolding, @RequestParam String beginDate, @RequestParam String endDate, @RequestParam(value = "stockPool[]", required = false) List<String> stockPool) {

        MomentumVO momentumVO = new MomentumVO(dayNumFormative, dayNumHolding, LocalDate.parse(beginDate), LocalDate.parse(endDate), stockPool);
        MomentumResultVO resultVO = momentumService.getResult(momentumVO);
        return new BaseResult<>(0, resultVO);
    }

    @GetMapping("/meanReversionStrategy")
    public BaseResult runMeanReversionStrategy(@RequestParam int meanDayNum, @RequestParam int holdingDayNum, @RequestParam int holdingStockNum, @RequestParam String beginDate, @RequestParam String endDate, @RequestParam(value = "stockPool[]", required = false) List<String> stockPool) {

        MeanReversionParamVO meanReversionParamVO = new MeanReversionParamVO(stockPool, holdingDayNum, holdingStockNum, LocalDate.parse(beginDate), LocalDate.parse(endDate), meanDayNum);
        MeanReversionResultVO reversionResultVO = meanReversionService.getResult(meanReversionParamVO);
        return new BaseResult<>(0, reversionResultVO);
    }

}
