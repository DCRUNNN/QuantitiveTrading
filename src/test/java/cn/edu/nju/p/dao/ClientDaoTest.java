package cn.edu.nju.p.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by xihao on 17-6-9.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ClientDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void addUserTest(){

        String userName = "asdasd嘻哈哦";
        String password = "123";
        String phoneNumber = "139272000312";

        clientDao.addClient(userName, phoneNumber, password);
    }

    @Test
    public void getTest(){

        String phoneNumber = "123123123";
        System.out.println(clientDao.selectClient(phoneNumber).getUserName());

    }
}