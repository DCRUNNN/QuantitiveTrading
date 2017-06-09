package cn.edu.nju.p.controller.stock;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.exception.StockNoneException;
import cn.edu.nju.p.service.exhibition.HomePageService;
import cn.edu.nju.p.vo.StockMarketVO;
import cn.edu.nju.p.vo.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cyz on 2017/6/6.
 */
@RestController
@RequestMapping("/homepage")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/market/{date}")
    public BaseResult getMarketVO(@PathVariable String date){
        LocalDate currentDate = LocalDate.parse(date);
        StockMarketVO marketVO=null;
        try {
           StockMarketVO vo=homePageService.getMarketVO(currentDate);
           marketVO=vo;
        }catch (StockNoneException e){
            e.printStackTrace();
        }
        return new BaseResult<>(0, marketVO);
    }

    @GetMapping("/allstock/{date}")
    public BaseResult getAllStock(@PathVariable String date){
        List<StockVO> voList=homePageService.getStockVO(LocalDate.parse(date));
        return new BaseResult<>(0, voList);
    }
}
