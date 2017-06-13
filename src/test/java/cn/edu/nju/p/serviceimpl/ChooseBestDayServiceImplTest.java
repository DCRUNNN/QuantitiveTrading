package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.service.strategy.ChooseBestDayService;
import cn.edu.nju.p.vo.MomentumParamVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Xihao on 2017/6/14.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ChooseBestDayServiceImplTest {

    @Autowired
    private ChooseBestDayService chooseBestDayService;

    @Test
    public void getResultReturnsOfMomentum() throws Exception {

        MomentumParamVO momentumParamVO = new MomentumParamVO(LocalDate.of(2016, 5, 2), LocalDate.of(2017, 2, 5), true, 10);
        System.out.println(chooseBestDayService.getResultReturnsOfMomentum(momentumParamVO));

    }

    @Test
    public void getResultReturnsOfMomentum2() throws Exception {

        MomentumParamVO momentumParamVO = new MomentumParamVO(LocalDate.of(2016, 5, 2), LocalDate.of(2017, 2, 5), true, 9);
        System.out.println(chooseBestDayService.getResultReturnsOfMomentum(momentumParamVO));

    }

}