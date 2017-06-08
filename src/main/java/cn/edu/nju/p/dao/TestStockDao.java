package cn.edu.nju.p.dao;

import cn.edu.nju.p.po.TestStockPO;

/**
 * Created by dell- on 2017/6/8.
 */
public interface TestStockDao {

    public TestStockPO getTestStockPO(String code);
}
