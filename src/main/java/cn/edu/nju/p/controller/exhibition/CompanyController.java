package cn.edu.nju.p.controller.exhibition;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.CompanyDao;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by soft on 2017/5/17.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyDao companyDao;

    @GetMapping("/info/{code}")
    public BaseResult getCompanyInfo(@RequestParam String code) {
        CompanyInfoVO infoVO =  companyDao.getCompanyInfoVO(code);
        return new BaseResult(0, infoVO);
    }

    @GetMapping("/news/{code}")
    public BaseResult getCompanyNews(@RequestParam String code) {
        List<CompanyNewsVO> newsVOS = companyDao.getCompanyNewsVOList(code);
        return new BaseResult(0, newsVOS);
    }
}
