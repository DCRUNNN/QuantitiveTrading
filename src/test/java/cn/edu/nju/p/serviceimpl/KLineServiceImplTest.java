package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.service.exhibition.KLineService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by soft on 2017/5/18.
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class KLineServiceImplTest {

    @Autowired
    private KLineService kLineService;

    @Test
    public void getKLineVOSByCode() throws Exception {

        LocalDate beginDate = LocalDate.of(2012, 10, 20);
        LocalDate endDate = LocalDate.of(2012, 10, 30);
        String stockCode = "000001";
        System.out.println(kLineService.getKLineVOSByCode(stockCode, beginDate, endDate));
    }

}