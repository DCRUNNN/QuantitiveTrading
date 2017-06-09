package cn.edu.nju.p.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */
@Mapper
@Repository
public interface MyStockDao {

    @Insert("insert into my_stock(userId,stock_code) values (#{userId},#{stockCode})")
    void addStock(@Param("userId") String userId, @Param("stockCode") String stockCode);

    @Select(value="select stock_code from my_stock where userId = #{userId}")
    List<String> getMyStockCode(String userId);

    @Delete("delete from my_stock where userId=#{phoneNumber} and stock_code=#{code}")
    void deleteStock(@Param("phoneNumber") String phoneNumber, @Param("code") String code);

}
