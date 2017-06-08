package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.CompanyDao;
import cn.edu.nju.p.dao.daoimpl.CompanyDaoImpl;
import cn.edu.nju.p.exception.CompanyNotFoundException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.service.exhibition.CompanyInfoService;
import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dc on 2017/6/8.
 */
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    private CompanyDao companyDao;


    @Override
    public CompanyInfoVO getCompanyInfoVO(String code) throws CompanyNotFoundException, StockNotFoundException {
        CompanyInfoVO vo = companyDao.getCompanyInfoVO(code);
        return vo;
    }

    @Override
    public List<CompanyAnnouncementVO> getCompanyAnnouncementVOList(String code) throws CompanyNotFoundException, StockNotFoundException {
        List<CompanyAnnouncementVO> list = companyDao.getCompanyAnnouncementVOList(code);
        return list;
    }

    @Override
    public List<CompanyNewsVO> getCompanyNewsVOList(String code) throws CompanyNotFoundException, StockNotFoundException {
        List<CompanyNewsVO> list = companyDao.getCompanyNewsVOList(code);
        return list;
    }

    public static void main(String[] args) throws CompanyNotFoundException {
        CompanyInfoService companyInfoService = new CompanyInfoServiceImpl();
        String code = "000001";
        CompanyInfoVO vo = companyInfoService.getCompanyInfoVO(code);
        System.out.println(vo.toString());
    }
}
