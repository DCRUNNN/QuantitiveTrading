package cn.edu.nju.p.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-5.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void addAccount() throws Exception {
        accountDao.addAccount("test", "test");
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals("test",accountDao.getPassword("test"));
    }

}