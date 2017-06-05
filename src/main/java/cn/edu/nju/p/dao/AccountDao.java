package cn.edu.nju.p.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by xihao on 17-6-5.
 */
@Mapper
@Repository
public interface AccountDao {

    @Insert("insert into account (phone_number,password) values (#{phoneNumber},#{password})")
    void addAccount(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Select("select password from account where phone_number =#{phoneNumber}")
    String getPassword(String phoneNumber);

}
