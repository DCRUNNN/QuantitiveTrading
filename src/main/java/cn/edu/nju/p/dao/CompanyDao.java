package cn.edu.nju.p.dao;

import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;

import java.util.List;

/**
 * Created by dell- on 2017/5/17.
 */
public interface CompanyDao {

    CompanyInfoVO getCompanyInfoVO(String code);

    List<CompanyNewsVO> getCompanyNewsVOList(String code);

    List<CompanyAnnouncementVO> getCompanyAnnouncementVOList(String code);
}
