package cn.edu.nju.p.service.Personnel;

import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */

public interface MyStockService {
    /**
     * 根据用户id得到该用户的自选股票
     *
     * @param userId 客户id
     * @return 一个包含该用户自选股的list
     */

    List<String> getMyStock(String userId);

    /**
     * 添加自选股
     * @param userId
     * @param stockCode
     */
    void addStock(String userId, String stockCode);

    void deleteStock(String userId, String code);

    void deleteAllStock(String phoneNumber);
}
