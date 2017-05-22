package cn.edu.nju.p.controller.exhibition;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.service.exhibition.AverageLineService;
import cn.edu.nju.p.service.exhibition.KLineService;
import cn.edu.nju.p.vo.AverageVO;
import cn.edu.nju.p.vo.KLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by soft on 2017/5/17.
 */
@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {

    @Autowired
    private KLineService kLineService;

    @Autowired
    private AverageLineService averageLineService;

    @GetMapping("/kline/{code}")
    public BaseResult getKLineDataByCode(@PathVariable String code, @RequestParam String beginDate, @RequestParam String endDate) {
        LocalDate begin = LocalDate.parse(beginDate);
        LocalDate end = LocalDate.parse(endDate);
        KLineVO kLineVO = kLineService.getKLineVOSByCode(code, begin, end);
        return new BaseResult(0, kLineVO);
    }

    @GetMapping("/averageline/{code}")
    public BaseResult getAverageLineDataByCode(@PathVariable String code, @RequestParam String beginDate, @RequestParam String endDate) {
        LocalDate begin = LocalDate.parse(beginDate);
        LocalDate end = LocalDate.parse(endDate);
        AverageVO averageVO = averageLineService.getAverageVOByCode(code, begin, end);
        return new BaseResult(0, averageVO);
    }
}
