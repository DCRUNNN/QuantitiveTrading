package cn.edu.nju.p.dao.daoutils;

import cn.edu.nju.p.dao.StockDao;
import cn.edu.nju.p.po.StockPO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.SQLException;

/**
 * Created by dell- on 2017/6/9.
 */
public class MysqlHelper_WYImpl implements MysqlHelper_WY {

    public static final String FILE_PATH = "D://StockData//";

    @Autowired
    private StockDao stockDao;

    @Override
    public void getDataFromCSV(String code) throws SQLException {
        String path = FILE_PATH + code + ".csv";
        String encoding = "GBK";
        FileInputStream is = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            File file = new File(path);
//            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            is = new FileInputStream(file);
            reader = new InputStreamReader(is, encoding);
            br = new BufferedReader(reader);
            String content = br.readLine();
            content = br.readLine();//读取文件标题

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try{
                    br.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insertIntoDataBase(String code, StockPO po) throws SQLException {

    }
}
