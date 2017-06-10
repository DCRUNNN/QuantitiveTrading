package cn.edu.nju.p.controller.exhibition;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.dao.CompanyDao;
import cn.edu.nju.p.dao.TestStockDao;
import cn.edu.nju.p.dao.daoutils.GetDataFromSinaUtil;
import cn.edu.nju.p.po.StockPO;
import cn.edu.nju.p.po.TestStockPO;
import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by soft on 2017/5/17.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private TestStockDao testStockDao;

    @GetMapping("/info/{code}")
    public BaseResult getCompanyInfo(@PathVariable String code) {
        CompanyInfoVO infoVO =  companyDao.getCompanyInfoVO(code);
        return new BaseResult<>(0, infoVO);
    }

    @GetMapping("/news/{code}")
    public BaseResult getCompanyNews(@PathVariable String code) {
        List<CompanyNewsVO> newsVOS = companyDao.getCompanyNewsVOList(code);
        return new BaseResult<>(0, newsVOS);
    }

    @GetMapping("/details/{code}")
    public BaseResult getStockDetails(@PathVariable String code) {
        StockPO po = GetDataFromSinaUtil.getInstance().getStockCurrentData(code, "sz");
        return new BaseResult<>(0, po);
    }

    @GetMapping("/announcement/{code}")
    public BaseResult getStockAnnouncement(@PathVariable String code) {
        List<CompanyAnnouncementVO> announcementVOS = companyDao.getCompanyAnnouncementVOList(code);
        return new BaseResult<>(0, announcementVOS);
    }

    @GetMapping("/diagnosis/{code}")
    public BaseResult getStockDiagnosis(@PathVariable String code) {
        TestStockPO po = testStockDao.getTestStockPO(code);
        return new BaseResult<>(0, po);
    }
}
