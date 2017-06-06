package cn.edu.nju.p.dao;

import cn.edu.nju.p.vo.ClientVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pc on 2017/6/6.
 */
@Mapper
@Repository
public interface ClientDao {

    @Select(value="select password from client_information where userId=#{userId}")
    public String getPassword(@Param("userId") String userId);

    @Select(value="select email from client_information where userId=#{userId}")
    public String getEmail(@Param("userId") String userId);

    @Select(value="select unit from client_information where userId=#{userId}")
    public String getUnit(@Param("userId") String userId);

    @Select(value="select phone_number from client_information where userId=#{userId}")
    public String getPhone_number(@Param("userId") String userId);

    @Select(value="select place from client_information where userId=#{userId}")
    public String getPlace(@Param("userId") String userId);

    @Update("update client_information set password=#{list.get(0)},email=#{list.get(1)},unit=#{list.get(2)},phone_number=#{list.get(3)},place=#{list.get(4)} where userId=#{userId}")
    public void modifyClientMessage(String userId,List<String> list);

}
