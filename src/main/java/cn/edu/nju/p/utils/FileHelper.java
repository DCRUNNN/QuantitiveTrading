package cn.edu.nju.p.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 往日志文件里面写进数据，和从日志文件读取数据
 */
public class FileHelper {

    private String logPath;

    public FileHelper(String logPath){
        this.logPath = logPath;
    }
    /**
     * 往log文件写入日志信息
     * @param message 信息
     */
    public void writeMessage(String message){

        FileWriter fw = null;
        try {
            fw =  new FileWriter(logPath,true);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PrintWriter pw = new PrintWriter(fw,true);
        pw.println(sdf.format(Calendar.getInstance().getTime())+"  "+message);

        try {
            fw.close();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
