package cn.edu.nju.p.dao.daoutils;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by dell- on 2017/6/7.
 */
public class GetDataFromYahooUtil {


    public static void main(String[] args) {
        try {
            URL url = new URL("http://finance.yahoo.com/d/quotes.csv?s=000001.sz&f=d1snohgl1vv");
            URLConnection urlConn = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line=bufferedReader.readLine();
            System.out.println(line);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
