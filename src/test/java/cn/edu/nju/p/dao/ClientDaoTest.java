package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.ClientPO;
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

        String userName = "我摘";
        String password = "123";
        String phoneNumber = "1asasdasd3927200q1qq0311q2";

        clientDao.addClient(userName, phoneNumber, password);
    }

    @Test
    public void getTest(){

        String phoneNumber = "13927200q1q0311q2";
        System.out.println(clientDao.selectClient(phoneNumber).getUserName());

    }

    @Test
    public void updateTest(){

        ClientPO clientPO = new ClientPO();
        clientPO.setPhoneNumber("13927200q1q0311q" +
                "2");
        clientPO.setEmail("asdasdasd");
        clientPO.setPlace("南京大学");
        clientPO.setUserName("asdasdasdasdasd");
        clientDao.updateClient(clientPO);

    }
}