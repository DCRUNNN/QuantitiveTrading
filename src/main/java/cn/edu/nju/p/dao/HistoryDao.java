package cn.edu.nju.p.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by pc on 2017/6/7.
 */
@Mapper
@Repository
public interface HistoryDao {

    @Select(value = "select history1 from History where userId=#{userId}")
    String getHistory1(String userId);

    @Select(value = "select history2 from History where userId=#{userId}")
    String getHistory2(String userId);

    @Select(value = "select history3 from History where userId=#{userId}")
    String getHistory3(String userId);

    @Select(value = "select history4 from History where userId=#{userId}")
    String getHistory4(String userId);

    @Select(value = "select history5 from History where userId=#{userId}")
    String getHistory5(String userId);

    @Select(value = "select history6 from History where userId=#{userId}")
    String getHistory6(String userId);

    @Select(value = "select history7 from History where userId=#{userId}")
    String getHistory7(String userId);

    @Select(value = "select history8 from History where userId=#{userId}")
    String getHistory8(String userId);

    @Select(value = "select history9 from History where userId=#{userId}")
    String getHistory9(String userId);

    @Select(value = "select history10 from History where userId=#{userId}")
    String getHistory10(String userId);








}
