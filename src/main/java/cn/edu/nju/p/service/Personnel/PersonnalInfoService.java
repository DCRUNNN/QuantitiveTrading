package cn.edu.nju.p.service.Personnel;

import cn.edu.nju.p.vo.ClientVO;

import java.util.List;

/**
 * Created by pc on 2017/6/10.
 */
public interface PersonnalInfoService {

    /**
     *
     * @param phoneNumber
     * @return
     */
    ClientVO getClientInfo(String phoneNumber);

    /**
     *
     * @param phone_number
     * @param user_name
     * @param sex
     * @param email
     * @param unit
     * @param place
     */
    void updateClient(String phone_number, String user_name,String sex,String email,String unit,String place);

    /**
     *
     * @param userName
     * @param phoneNumber
     */
        void addClient(String userName,String phoneNumber,String password);

    /**
     *
     * @param phone_number
     */
    void updatePass(String phone_number,String password);
}
