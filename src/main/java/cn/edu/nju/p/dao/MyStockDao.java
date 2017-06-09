package cn.edu.nju.p.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

}
