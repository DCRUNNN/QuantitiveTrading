package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.SaveStrategyPO;
import cn.edu.nju.p.po.StrategyCodePO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cyz on 2017/6/14.
 */


@Mapper
@Repository
public interface MyStrategyDao {

    @Select("select * from my_strategy where phone_number = #{phoneNumber}")
    List<StrategyCodePO> getMyStrategy(String phoneNumber);

    @Insert("insert into my_strategy (phone_number,code,strategy_name) values(#{phoneNumber},#{code},#{strategyName})")
    void addMyStrategy(@Param("phoneNumber") String phoneNumber, @Param("code") String code, @Param("strategyName") String strategyName);

    @Select("select strategy_name from my_strategy where phone_number = #{phoneNumber}")
    List<String> getMyStrategyName(String phoneNumber);

    @Select("select code from my_strategy where phone_number=#{phoneNumber} and locate(strategy_name,#{strategyName}) > 0")
    String getCode(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName);

    @Delete("delete from my_strategy where phone_number=#{phoneNumber} and strategy_name=#{strategyName}")
    void deleteStrategy(@Param("phoneNumber") String phoneNumber, @Param("strategyName") String strategyName);

    @Select("select * from my_save where phone_number=#{phoneNumber}")
    List<SaveStrategyPO> getMySaveStrategyList(String phoneNumber);

    @Delete("delete from my_save where phone_number=#{phoneNumber} and strategy_name=#{strategyName}")
    void deleteSaveStrategy(@Param("phoneNumber") String phoneNumber,@Param("strategyName")String strategyName);
}
