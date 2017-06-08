package cn.edu.nju.p.service.exhibition;

import cn.edu.nju.p.exception.CompanyNotFoundException;
import cn.edu.nju.p.exception.StockNoneException;
import cn.edu.nju.p.exception.StockNotFoundException;
import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;

import java.util.List;

/**
 * Created by dc on 2017/6/8.
 */
public interface CompanyInfoService {

    /**
     *
     * @param code 股票代码
     * @return 返回该股票对应的公司信息，以一个VO形式返回
     */
    CompanyInfoVO getCompanyInfoVO(String code) throws CompanyNotFoundException, StockNotFoundException;

    /**
     *
     * @param code 股票代码
     * @return 返回该股票对应的公司公告，以一个VOList形式返回
     */
    List<CompanyAnnouncementVO> getCompanyAnnouncementVOList(String code) throws CompanyNotFoundException, StockNotFoundException;

    /**
     *
     * @param code 股票代码
     * @return 返回该股票对应的公司新闻，以一个VOList形式返回
     */
    List<CompanyNewsVO> getCompanyNewsVOList(String code) throws CompanyNotFoundException, StockNotFoundException;

}
