package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.StrategyCodePO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dell- on 2017/6/15.
 */
@Mapper
@Repository
public interface StrategySquareDao {

    @Select("select * from strategy_square")
    List<StrategyCodePO> getAllStrategy();

    @Insert("insert into strategy_square (phone_number,strategy_name,code) values(#{phoneNumber},#{strategyName},#{code})")
    void shareStrategy(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName,@Param("code")String code);

//    @Select("select strategy_name from my_strategy where phone_number = #{phoneNumber}")
//    List<String> getStrategyName(String phoneNumber);

    @Select("select code from my_strategy where phone_number=#{phoneNumber} and locate(strategy_name,#{strategyName}) > 0")
    String getCode(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName);

    @Insert("insert into strategy_square (code) values(#{phoneNumber},#{strategyName},#{strategyName})")
    void setCode(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName);

    @Delete("delete from strategy_square where phone_number=#{phoneNumber} and strategy_name=#{strategyName}")
    void deleteStrategy(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName);

}
