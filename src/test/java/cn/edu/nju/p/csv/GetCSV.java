package cn.edu.nju.p.csv;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.deeplearning.factor.Alpha101;
import cn.edu.nju.p.deeplearning.factor.Alpha2;
import cn.edu.nju.p.deeplearning.factor.Alpha26;
import cn.edu.nju.p.utils.DateHelper;
import cn.edu.nju.p.utils.StockHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by xihao on 17-6-6.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class GetCSV {

    @Autowired
    Alpha2 alpha2;

    @Autowired
    Alpha26 alpha26;

    @Autowired
    Alpha101 alpha101;

    @Autowired
    StockDao stockDao;

    @Test
    public void writeCsv() throws Exception {

        String code = "000001";
        String path = "/home/xihao/文档/test000001.csv";
        File csv = new File(path);
        PrintWriter printWriter = new PrintWriter(new FileWriter(csv, true));
        printWriter.println("stockCode,date,alpha2,alpha26,alpha101,logClose");

        LocalDate beginDate = LocalDate.of(2012, 1, 1);
        LocalDate endDate = beginDate.plusYears(1);

        //两个日期之间的有效日期
        List<LocalDate> dates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, aDate -> StockHelper.isValidByCode(code, aDate));

        for (LocalDate date : dates) {
            double valueOfAlpha2 = alpha2.getAlpha(code, date);
            double valueOfAlpha26 = alpha26.getAlpha(code, date);
            double valueOfAlpha101 = alpha101.getAlpha(code, date);
            LocalDate lastDate = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
            double logClose = Math.log(divide(stockDao.getStockClose(code, date), stockDao.getStockClose(code, lastDate)));
            System.out.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.flush();
        }
        printWriter.close();
    }

    private double divide(double a, double b) {

        BigDecimal bigDecimal_a = new BigDecimal(a);
        BigDecimal bigDecimal_b = new BigDecimal(b);
        return bigDecimal_a.divide(bigDecimal_b, RoundingMode.HALF_UP).doubleValue();
    }

    @Test
    public void getDataOf000002()throws Exception {

        String code = "000002";
        String path = "/home/xihao/文档/000002.csv";
        File csv = new File(path);
        PrintWriter printWriter = new PrintWriter(new FileWriter(csv, true));
        printWriter.println("stockCode,date,alpha2,alpha26,alpha101,logClose");

        LocalDate beginDate = LocalDate.of(2012, 1, 1);
        LocalDate endDate = beginDate.plusYears(1);

        //两个日期之间的有效日期
        List<LocalDate> dates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, aDate -> StockHelper.isValidByCode(code, aDate));

        for (LocalDate date : dates) {
            double valueOfAlpha2 = alpha2.getAlpha(code, date);
            double valueOfAlpha26 = alpha26.getAlpha(code, date);
            double valueOfAlpha101 = alpha101.getAlpha(code, date);
            LocalDate lastDate = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
            double logClose = Math.log(divide(stockDao.getStockClose(code, date), stockDao.getStockClose(code, lastDate)));
            System.out.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.flush();
        }
        printWriter.close();
    }

    @Test
    public void getDataOf000004()throws Exception {

        String code = "000004";
        String path = "/home/xihao/文档/000004.csv";
        File csv = new File(path);
        PrintWriter printWriter = new PrintWriter(new FileWriter(csv, true));
        printWriter.println("stockCode,date,alpha2,alpha26,alpha101,logClose");

        LocalDate beginDate = LocalDate.of(2012, 1, 1);
        LocalDate endDate = beginDate.plusYears(1);

        //两个日期之间的有效日期
        List<LocalDate> dates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, aDate -> StockHelper.isValidByCode(code, aDate));

        for (LocalDate date : dates) {
            double valueOfAlpha2 = alpha2.getAlpha(code, date);
            double valueOfAlpha26 = alpha26.getAlpha(code, date);
            double valueOfAlpha101 = alpha101.getAlpha(code, date);
            LocalDate lastDate = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
            double logClose = Math.log(divide(stockDao.getStockClose(code, date), stockDao.getStockClose(code, lastDate)));
            System.out.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.flush();
        }
        printWriter.close();
    }

    @Test
    public void getDataOf000002Additional()throws Exception {

        String code = "000002";
        String path = "/home/xihao/文档/000002_add.csv";
        File csv = new File(path);
        PrintWriter printWriter = new PrintWriter(new FileWriter(csv, true));
        printWriter.println("stockCode,date,alpha2,alpha26,alpha101,logClose");

        LocalDate beginDate = LocalDate.of(2013, 1, 1);
        LocalDate endDate = beginDate.plusYears(4);

        //两个日期之间的有效日期
        List<LocalDate> dates = DateHelper.getBetweenDateAndFilter(beginDate, endDate, aDate -> StockHelper.isValidByCode(code, aDate));

        for (LocalDate date : dates) {
            double valueOfAlpha2 = alpha2.getAlpha(code, date);
            double valueOfAlpha26 = alpha26.getAlpha(code, date);
            double valueOfAlpha101 = alpha101.getAlpha(code, date);
            LocalDate lastDate = DateHelper.getLastDate(date, aDate -> StockHelper.isValidByCode(code, aDate));
            double logClose = Math.log(divide(stockDao.getStockClose(code, date), stockDao.getStockClose(code, lastDate)));
            System.out.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.println(code + "," + date.toString() + "," + valueOfAlpha2 + "," + valueOfAlpha26 + "," + valueOfAlpha101 + "," + logClose);
            printWriter.flush();
        }
        printWriter.close();
    }
}
