package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.service.exhibition.CompanyInfoService;
import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dc on 2017/6/8.
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyInfoServiceImplTest {

    @Autowired
    private CompanyInfoService companyInfoService;

    @Test
    public void getCompanyInfoVO() throws Exception {
        String code = "000001";
        CompanyInfoVO vo = companyInfoService.getCompanyInfoVO(code);
        System.out.println(vo.toString());
    }

    @Test
    public void getCompanyAnnouncementVOList() throws Exception {
        String code = "000025";
        List<CompanyAnnouncementVO> list = companyInfoService.getCompanyAnnouncementVOList(code);
        for (CompanyAnnouncementVO vo : list) {
            System.out.println(vo.toString());
        }
    }

    @Test
    public void getCompanyNewsVOList() throws Exception {
        String code = "002036";
        List<CompanyNewsVO> list = companyInfoService.getCompanyNewsVOList(code);
        for (CompanyNewsVO vo : list) {
            System.out.println(vo.toString());
        }
    }
}
