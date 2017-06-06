package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.ClientDao;
import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.service.Personnel.PersonnalInfoService;
import cn.edu.nju.p.vo.ClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 2017/6/6.
 */

/**
 * PersonnalInfoService的实现类
 */

@Service
public class PersonnalInfoServiceImpl implements PersonnalInfoService {
    @Autowired
    private ClientDao clientDao;

    /**
     * 根据userId得到该客户的所有信息
     *
     * @param userId 客户用户名
     * @return 返回一个包含该用户信息的VO
     */
    public ClientVO getClientVO(String userId) {
        String password = clientDao.getPassword(userId);
        String email = clientDao.getEmail(userId);
        String unit = clientDao.getUnit(userId);
        String phone_number = clientDao.getPhone_number(userId);
        String place = clientDao.getPlace(userId);

        ClientPO po = new ClientPO(userId, password, email, unit, phone_number, place);
        ClientVO vo = new ClientVO(po);
        return vo;
    }
}