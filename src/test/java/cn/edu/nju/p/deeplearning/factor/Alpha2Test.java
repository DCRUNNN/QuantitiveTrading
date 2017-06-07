package cn.edu.nju.p.deeplearning.factor;

import cn.edu.nju.p.utils.DateHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Alpha2Test {

    @Autowired
    private Alpha2 alpha2;

    @Test
    public void getAlpha() throws Exception {

        String stockCode = "000001";
        LocalDate localDate = LocalDate.of(2012, 2, 3);
        assertEquals(-0.6536211416973923, alpha2.getAlpha(stockCode,localDate),0.000000000001);
    }

    @Test
    public void getAlpha2Test() throws Exception {

        String code = "000002";
        LocalDate localDate = LocalDate.of(2012, 2, 3);
        assertEquals(0.039628014668706704, alpha2.getAlpha(code,localDate),0.000000000001);
    }

    @Test
    public void getAlpha3Test() throws Exception {

        String code = "000004";
        LocalDate localDate = LocalDate.of(2012, 2, 3);

        String path = "/home/xihao/文档/test000001.csv";
        File csv = new File(path);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csv, true));
        LocalDate endDate = localDate.plusDays(10);
        List<LocalDate> dates = DateHelper.getBetweenDateAndFilter(localDate, endDate, a -> true);
        for (LocalDate date : dates) {
            double alpha = alpha2.getAlpha(code, date);
            bufferedWriter.write(code + "," + date.toString() + "," + alpha);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        assertEquals(-0.5219468865405076, alpha2.getAlpha(code,localDate),0.000000000001);
    }

    @Test
    public void printTest() throws Exception {

        String path = "/home/xihao/文档/test000001.csv";
        File csv = new File(path);
        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csv, true));
        bufferedWriter.write("stockCode,date,alpha12");
        bufferedWriter.newLine();
        bufferedWriter.close();*/
        PrintWriter printWriter = new PrintWriter(new FileWriter(csv, true));
        printWriter.println("stockCode,date,alpha12");
        printWriter.close();

    }
}