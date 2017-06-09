package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.ClientPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 2017/6/6.
 */
@Mapper
@Repository
public interface ClientDao {

    @Insert("insert into client_information(phone_number,password,user_name) values (#{phoneNumber},#{password},#{userName})")
    void addClient(@Param("userName") String userName, @Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Update("update client_information set password=#{client.password},user_name=#{client.userName},email=#{client.email}," +
            "unit=#{client.unit},place=#{client.place} where phone_number=#{client.phoneNumber}")
    void updateClient(@Param("client") ClientPO clientPO);

    @Select("select * from client_information where phone_number=#{phoneNumber}")
    ClientPO selectClient(String phoneNumber);

}
