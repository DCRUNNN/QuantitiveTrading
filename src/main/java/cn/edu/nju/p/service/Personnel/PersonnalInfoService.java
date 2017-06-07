package cn.edu.nju.p.service.Personnel;

import cn.edu.nju.p.vo.ClientVO;

/**
 * Created by cyz on 2017/6/6.
 */
public interface PersonnalInfoService {
    /**
     * 根据userId返回该用户的所有信息
     *
     * @param userId  客户用户名
     * @return 返回一个包含该用户信息的VO
     */
    public ClientVO getClientVO(String userId);
}
