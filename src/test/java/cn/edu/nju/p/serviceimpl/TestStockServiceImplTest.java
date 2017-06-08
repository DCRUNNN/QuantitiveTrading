package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.service.exhibition.TestStockService;
import cn.edu.nju.p.vo.TestStockVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by dell- on 2017/6/8.
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStockServiceImplTest {

    @Autowired
    private TestStockService testStockService;

    @Test
    public void getTestStockVO() throws Exception {
        String code = "000001";
        TestStockVO vo = testStockService.getTestStockVO(code);
        System.out.println(vo.toString());

    }
}