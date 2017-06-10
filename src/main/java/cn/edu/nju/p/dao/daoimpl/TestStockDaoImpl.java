package cn.edu.nju.p.dao.daoimpl;

import cn.edu.nju.p.dao.TestStockDao;
import cn.edu.nju.p.po.TestStockPO;
import io.swagger.models.auth.In;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by dc on 2017/6/8.
 */
@Repository
public class TestStockDaoImpl implements TestStockDao {

    private static final String THS_URL = "http://stockpage.10jqka.com.cn/";

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

    @Override
    public TestStockPO getTestStockPO(String code) {

        Document doc = getDocument(THS_URL + code + "/");

        Elements html = doc.select("[class=clearfix analyze]");//得到诊股所在的class

        Elements pointEle = html.select("[class=analyze-img fl]");

        double point = Double.parseDouble(pointEle.select("[class=analyze-num]").text()); //评分
        String overView = pointEle.select("[class=analyze-tips mt7]").text(); //综合判断
        String beat = pointEle.select("[class=analyze-tips]").text(); //打败了多少股票

        Elements trend = html.select("[class=analyze-txt fr]");//趋势所在的class
        Element shortEle = trend.select("[class=analyze-txt-cont]").get(0);
        Element midEle = trend.select("[class=analyze-txt-cont]").get(1);
        Element longEle = trend.select("[class=analyze-txt-cont]").get(2);
        Elements conclude = trend.select("[class=txt-phra]");

        String shortTrend = shortEle.select("[class=txt-main]").text();//短期趋势
        String midTrend = midEle.select("[class=txt-main]").text();//中期趋势
        String longTrend = longEle.select("[class=txt-main]").text();//长期趋势
        String conclusion = conclude.text().split(" ")[0];//诊断
        String date = conclude.select("[class=date]").text().replace("[","").replace("]","");//诊断日期

        Elements aspect = html.select("[class=analyze-level fr]").select("[class=analyze-stars]");
        String technical_test = aspect.get(0).select("[class=cred]").text();
        String fund_test = aspect.get(1).select("[class=cred]").text();
        String info_test = aspect.get(2).select("[class=cred]").text();
        String industry_test = aspect.get(3).select("[class=cred]").text();
        String basic_test = aspect.get(4).select("[class=cred]").text();

        TestStockPO po = new TestStockPO(point, overView, beat, shortTrend, midTrend, longTrend, conclusion, date, technical_test, fund_test, info_test, industry_test, basic_test);
        return po;
    }

    public static void main(String[] args) {
        TestStockDaoImpl run = new TestStockDaoImpl();
        TestStockPO po = run.getTestStockPO("000505");
        System.out.println(po.toString());
    }
}
