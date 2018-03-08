package cn.edu.nju.p.dao.daoimpl;

import cn.edu.nju.p.dao.MarketPerformanceDao;
import cn.edu.nju.p.po.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell- on 2017/6/8.
 */
public class MarketPerformanceDaoImpl implements MarketPerformanceDao {

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

    public static String createParam(int type) {
        String result = "";
        switch (type) {
            case 0:
                result = "order_199112_desc";
                break;
            case 1:
                result = "order_199112_asc";
                break;
            case 2:
                result = "order_13_desc";
                break;
            case 3:
                result = "order_1968584_desc";
                break;
            case 4:
                result = "order_1771976_desc";
                break;
            case 5:
                result = "order_2034120_desc";
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public List<SameMarketStockPO> getSameMarketStockInfo(String code, int type) {
        String param = MarketPerformanceDaoImpl.createParam(type);
        List<SameMarketStockPO> list = new ArrayList<SameMarketStockPO>();

        Document doc = getDocument(THS_URL + code + "/");
        Elements html = doc.select("[class=hydb_column a_blue_d_all data_table]");//选择公司新闻所在的class
//        Element html = doc.head();
        Elements data=html.select("tr[class$=row_0]");
        System.out.println(doc);
        return null;
    }


    public static void main(String[] args) {
        MarketPerformanceDaoImpl test=new MarketPerformanceDaoImpl();
        test.getSameMarketStockInfo("000001", 0);

    }

}
