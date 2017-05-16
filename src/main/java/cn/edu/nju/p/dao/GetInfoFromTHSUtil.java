package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.CompanyInfoPO;
import cn.edu.nju.p.po.CompanyNewsPO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell- on 2017/5/16.
 */
public class GetInfoFromTHSUtil {

    private static final String THS_URL="http://stockpage.10jqka.com.cn/";
    private static final GetInfoFromTHSUtil INSTANCE=new GetInfoFromTHSUtil(); //单例模式

    public static GetInfoFromTHSUtil getInstance(){
        return INSTANCE;
    }

    /**
     * Jsoup初始化
     * @param URL
     * @return
     */
    public Document getDocument(String URL) {
        try{
            return Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据股票代码得到股票名称
     * @param code
     * @return 股票名称
     */
    public static String getName(String code) {
        Document doc = GetInfoFromTHSUtil.getInstance().getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=m_logo fl]");
        return html.select("strong").text();
    }

    /**
     * 根据股票代码得到公司信息
     * @param code 股票代码
     * @return 公司信息PO
     */
    public CompanyInfoPO getCompanyInfo(String code) {
        CompanyInfoPO companyInfoPO=new CompanyInfoPO();

        String name = GetInfoFromTHSUtil.getName(code);
        companyInfoPO.setName(name); //设置公司名称

        Document doc = GetInfoFromTHSUtil.getInstance().getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=company_details]");//选择公司信息所在的class
        Elements data = html.select("dd");//得到公司的信息
        //设置PO属性
        for(int i=0;i<data.size();) {
            companyInfoPO.setArea(data.get(i++).text()); //设置区域
            companyInfoPO.setConcept(data.get(i++).attr("title")); //设置涉及概念
            companyInfoPO.setBusiness(data.get(i++).text()); //设置业务
            companyInfoPO.setBusinessDetail(data.get(i++).attr("title").substring(2)); //设置详细业务
            companyInfoPO.setListingDate(data.get(i++).text()); //设置上市日期
            companyInfoPO.setNetAssetPerShare(data.get(i++).text()); //设置每股净资产
            companyInfoPO.setIncomePerShare(data.get(i++).text()); //设置每股收益
            companyInfoPO.setProfit(data.get(i++).text()); //设置每股净利润
            companyInfoPO.setGrowthRate_profit(data.get(i++).text()); //设置每股净利润增长率
            companyInfoPO.setIncomeAll(data.get(i++).text()); //设置营业收入
            companyInfoPO.setCashFlowPerShare(data.get(i++).text()); //设置每股现金流
            companyInfoPO.setFundPerShare(data.get(i++).text()); //设置每股公积金
            companyInfoPO.setUndistributedProfitPerShare(data.get(i++).text()); //设置每股未分配利润
            companyInfoPO.setTotalShareCapital(data.get(i++).text()); //设置总股本
            companyInfoPO.setOutstandingShares(data.get(i++).text()); //设置流通股
        }

        return companyInfoPO;
    }

    /**
     * 返回给逻辑层的方法
     * @param code 股票代码
     * @return 公司信息VO
     */
    public CompanyInfoVO getCompanyInfoVO(String code) {
        CompanyInfoPO po = GetInfoFromTHSUtil.getInstance().getCompanyInfo(code);
        CompanyInfoVO vo = new CompanyInfoVO(po);
        return vo;
    }

    public List<CompanyNewsPO> getCompanyNewsList(String code) {
        List<CompanyNewsPO> list = new ArrayList<CompanyNewsPO>();
        Document doc = GetInfoFromTHSUtil.getInstance().getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=news_list stat][stat=f10_spqk_gsxw]");//选择公司新闻所在的class
        Elements data_title = html.select("span[class$=news_title fl]");//得到公司的新闻标题
        Elements data_date = html.select("span[class$=news_date]");
        Elements data_link = html.select("a[href]");

        for(int i=0;i<data_title.size();i++) {
            String title=data_title.get(i).text();
            String link = data_link.get(i).attr("href");
            String date = data_date.get(i).text();
            CompanyNewsPO po = new CompanyNewsPO(title, link, date);
            list.add(po);
        }
        return list;
    }

    public List<CompanyNewsVO> getCompanyNewsVOList(String code) {
        List<CompanyNewsPO> list = GetInfoFromTHSUtil.getInstance().getCompanyNewsList(code);
        List<CompanyNewsVO> result = new ArrayList<CompanyNewsVO>();
        for (CompanyNewsPO po : list) {
            CompanyNewsVO vo = new CompanyNewsVO();
            vo.setTitle(po.getTitle());
            vo.setLink(po.getLink());
            vo.setDate(po.getDate());
            result.add(vo);
        }
        return result;
    }

//    public static void main(String[] args) {
//        List<CompanyNewsVO> list = GetInfoFromTHSUtil.getInstance().getCompanyNewsVOList("000001");
//        for(int i=0;i<list.size();i++) {
//            System.out.println(list.get(i).toString());
//        }
//    }

}
