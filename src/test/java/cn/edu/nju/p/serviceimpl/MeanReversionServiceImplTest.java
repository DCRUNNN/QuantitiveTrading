package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.vo.MeanReversionParamVO;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-7.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class MeanReversionServiceImplTest {

    @Autowired
    private MeanReversionServiceImpl meanReversionService;


    @Test
    public void getResult() throws Exception {

        System.out.println(meanReversionService.getResult(new MeanReversionParamVO(10,10, LocalDate.of(2012,10,20),LocalDate.of(2013,10,20),10)));
    }

    @Test
    public void shortTest() throws Exception {

        System.out.println(meanReversionService.getResult(new MeanReversionParamVO(10,10, LocalDate.of(2012,10,20),LocalDate.of(2013,1,20),10)));
    }
}