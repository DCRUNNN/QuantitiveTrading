package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.service.personnel.PersonnalInfoService;
import cn.edu.nju.p.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 2017/6/10.
 */
@Service
public class PersonnalInfoServiceImpl implements PersonnalInfoService{
    @Autowired
    private ClientDao clientDao;

    @Override
    public void addClient(String userName,String phoneNumber,String password){
        clientDao.addClient(userName,phoneNumber,password);
    }

    @Override
    public ClientVO getClientInfo(String phoneNumber){
        ClientVO vo = clientDao.selectClient(phoneNumber);
        return vo;
    }

    @Override
    public void updateClient(String phone_number, String user_name,String sex,String email,String unit,String place){
        clientDao.updateClient(phone_number,user_name,sex,email,unit,place);
    }

    @Override
    public void updatePass(String phone_number,String password){
        clientDao.updatePass(phone_number,password);
    }
}
