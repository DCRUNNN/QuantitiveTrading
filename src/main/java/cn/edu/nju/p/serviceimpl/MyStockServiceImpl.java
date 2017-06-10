package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.MyStockDao;
import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.service.Personnel.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017/6/7.
 */

/**
 * MyStockService的实现类
 */
@Service
public class MyStockServiceImpl implements MyStockService{

    @Autowired
    private MyStockDao myStockDao;

    @Autowired
    private StockDao stockDao;


    @Override
    public List<String> getMyStock(String userId) {

        List<String> stockCodes = myStockDao.getMyStockCode(userId);
        List<String> messages = new ArrayList<>();
        for (String code : stockCodes) {
            messages.add(code + "-" + stockDao.getStockName(code));
        }
        return messages;
    }

    @Override
    public void addStock(String userId, String stockCode) {

        myStockDao.addStock(userId, stockCode);
    }

    @Override
    public void deleteStock(String userId, String code) {
        myStockDao.deleteStock(userId, code);
    }


    @Override
    public void deleteAllStock(String phoneNumber){
        myStockDao.deleteAllStock(phoneNumber);
    }
}
