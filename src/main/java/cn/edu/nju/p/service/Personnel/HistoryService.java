package cn.edu.nju.p.service.Personnel;

import java.util.List;

/**
 * Created by cyz on 2017/6/7.
 */
public interface HistoryService {

    /**
     * 根据用户id返回该用户的浏览记录（股票代码和名称）
     *
     * @param userId
     * @return list
     */
    List<String> getHistory(String userId);






}
