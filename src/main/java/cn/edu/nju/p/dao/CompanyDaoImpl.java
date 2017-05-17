package cn.edu.nju.p.dao;

import cn.edu.nju.p.vo.CompanyAnnouncementVO;
import cn.edu.nju.p.vo.CompanyInfoVO;
import cn.edu.nju.p.vo.CompanyNewsVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell- on 2017/5/17.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    private static final String THS_URL="http://stockpage.10jqka.com.cn/";

    /**
     * Jsoup初始化
     * @param URL
     * @return
     */
    private Document getDocument(String URL) {
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
    private String getName(String code) {
        Document doc = getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=m_logo fl]");
        return html.select("strong").text();
    }

    /**
     * 根据股票代码得到公司信息 以VO形式返回
     * @param code
     * @return
     */
    @Override
    public CompanyInfoVO getCompanyInfoVO(String code) {
        CompanyInfoVO vo=new CompanyInfoVO();

        String name = getName(code);
        vo.setName(name); //设置公司名称

        Document doc = getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=company_details]");//选择公司信息所在的class
        Elements data = html.select("dd");//得到公司的信息
        //设置VO属性
        for(int i=0;i<data.size();) {
            vo.setArea(data.get(i++).text()); //设置区域
            vo.setConcept(data.get(i++).attr("title")); //设置涉及概念
            vo.setBusiness(data.get(i++).text()); //设置业务
            vo.setBusinessDetail(data.get(i++).attr("title").substring(2)); //设置详细业务
            vo.setListingDate(data.get(i++).text()); //设置上市日期
            vo.setNetAssetPerShare(data.get(i++).text()); //设置每股净资产
            vo.setIncomePerShare(data.get(i++).text()); //设置每股收益
            vo.setProfit(data.get(i++).text()); //设置每股净利润
            vo.setGrowthRate_profit(data.get(i++).text()); //设置每股净利润增长率
            vo.setIncomeAll(data.get(i++).text()); //设置营业收入
            vo.setCashFlowPerShare(data.get(i++).text()); //设置每股现金流
            vo.setFundPerShare(data.get(i++).text()); //设置每股公积金
            vo.setUndistributedProfitPerShare(data.get(i++).text()); //设置每股未分配利润
            vo.setTotalShareCapital(data.get(i++).text()); //设置总股本
            vo.setOutstandingShares(data.get(i++).text()); //设置流通股
        }

        return vo;
    }

    /**
     * 根据股票代码得到公司新闻 以VO形式返回
     * @param code
     * @return
     */
    @Override
    public List<CompanyNewsVO> getCompanyNewsVOList(String code) {
        List<CompanyNewsVO> list = new ArrayList<CompanyNewsVO>();
        Document doc = getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=news_list stat][stat=f10_spqk_gsxw]");//选择公司新闻所在的class
        Elements news_title = html.select("span[class$=news_title fl]");//得到公司的新闻标题
        Elements news_date = html.select("span[class$=news_date]");//得到公司新闻日期
        Elements news_link = html.select("a[href]");//得到公司新闻链接

        for(int i=0;i<news_title.size();i++) {
            String title=news_title.get(i).text();
            String link = news_link.get(i).attr("href");
            String date = news_date.get(i).text();
            CompanyNewsVO vo = new CompanyNewsVO(title, link, date);
            list.add(vo);
        }
        return list;
    }


    @Override
    public List<CompanyAnnouncementVO> getCompanyAnnouncementVOList(String code) {
        List<CompanyAnnouncementVO> list = new ArrayList<CompanyAnnouncementVO>();
        Document doc = getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=news_list stat][stat=f10_spqk_gsgg]");//选择公司公告所在的class

        Elements announcement_title = html.select("span[class$=news_title fl]");//得到公司公告标题
        Elements announcement_date = html.select("span[class$=news_date]");//得到公司公告日期
        Elements announcement_link = html.select("a[href]");//得到公司公告链接

        for(int i=0;i<announcement_title.size();i++) {
            String title=announcement_title.get(i).text();
            String link = announcement_link.get(i).attr("href");
            String date = announcement_date.get(i).text();
            CompanyAnnouncementVO vo = new CompanyAnnouncementVO(title, link, date);
            list.add(vo);
        }
        return list;
    }

    public static void main(String[] args) {
        CompanyDaoImpl test = new CompanyDaoImpl();
        System.out.println(test.getCompanyInfoVO("000001").toString());
        List<CompanyAnnouncementVO> list = test.getCompanyAnnouncementVOList("000001");
        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
