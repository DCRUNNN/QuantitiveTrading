package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.ClientPO;
import cn.edu.nju.p.vo.ClientVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by pc on 2017/6/6.
 */
@Mapper
@Repository
public interface ClientDao {

    @Insert(value = "insert into client_information(phone_number,user_name,password) values (#{phoneNumber},#{userName},#{password})")
    void addClient(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber , @Param("password") String password);

    @Update(value = "update client_information set sex=#{sex},user_name=#{user_name},email=#{email}," +
            "unit=#{unit},place=#{place} where phone_number=#{phone_number}")
    void updateClient(@Param("phone_number") String phone_number,@Param("user_name") String user_name,@Param("sex") String sex,@Param("email") String email,@Param("unit") String unit,@Param("place") String place);

    @Select(value = "select * from client_information where phone_number=#{phoneNumber}")
    ClientVO selectClient(String phoneNumber);

    @Update(value = "update client_information set password=#{password} where phone_number=#{phone_number}")
    void updatePass(@Param("phone_number") String phone_number,@Param("password") String password);

}
