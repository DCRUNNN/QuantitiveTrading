package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.vo.MomentumVO;
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
public class MomentumServiceImplTest {

    @Autowired
    private MomentumServiceImpl momentumService;

    @Test
    public void getResult() throws Exception {

        MomentumVO momentumVO = new MomentumVO(10, 20, LocalDate.of(2016, 3, 5), LocalDate.of(2016, 5, 20));
        System.out.println(momentumService.getResult(momentumVO));
    }

    @Test
    public void getResultLongTest() throws Exception {

        MomentumVO momentumVO = new MomentumVO(10, 20, LocalDate.of(2016, 3, 5), LocalDate.of(2017, 5, 20));
        System.out.println(momentumService.getResult(momentumVO));
    }

    @Test
    public void getRsultShortTest() throws Exception{
        MomentumVO momentumVO = new MomentumVO(10, 20, LocalDate.of(2016, 6, 5), LocalDate.of(2016, 8, 20));
        System.out.println(momentumService.getResult(momentumVO));
    }
}