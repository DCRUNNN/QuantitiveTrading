package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.TestStockDao;
import cn.edu.nju.p.dao.daoimpl.TestStockDaoImpl;
import cn.edu.nju.p.po.TestStockPO;
import cn.edu.nju.p.service.exhibition.TestStockService;
import cn.edu.nju.p.vo.TestStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dc on 2017/6/8.
 */
@Service
public class TestStockServiceImpl implements TestStockService {

    @Autowired
    private TestStockDaoImpl testStockDao;

    @Override
    public TestStockVO getTestStockVO(String code) {
        TestStockPO po = testStockDao.getTestStockPO(code);
        return new TestStockVO(po);
    }

    public static void main(String[] args) {
        TestStockServiceImpl test = new TestStockServiceImpl();
        System.out.println(test.getTestStockVO("000001").toString());
    }
}
