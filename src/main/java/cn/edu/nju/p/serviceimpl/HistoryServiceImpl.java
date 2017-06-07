package cn.edu.nju.p.serviceimpl;

import cn.edu.nju.p.dao.HistoryDao;
import cn.edu.nju.p.service.Personnel.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyz on 2017/6/7.
 */

/**
 * HistoryService的实现类
 */
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryDao historyDao;

    /**
     * 根据用户id返回该用户的浏览记录（股票代码和名称）
     *
     * @param userId
     * @return HistoryVO
     */
    public List<String> getHistory(String userId){
        List<String> list = new ArrayList<>();
        list.add(historyDao.getHistory1(userId));
        list.add(historyDao.getHistory2(userId));
        list.add(historyDao.getHistory3(userId));
        list.add(historyDao.getHistory4(userId));
        list.add(historyDao.getHistory5(userId));
        list.add(historyDao.getHistory6(userId));
        list.add(historyDao.getHistory7(userId));
        list.add(historyDao.getHistory8(userId));
        list.add(historyDao.getHistory9(userId));
        list.add(historyDao.getHistory10(userId));

        return list;
    }


}
