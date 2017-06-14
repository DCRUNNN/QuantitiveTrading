package cn.edu.nju.p.dao.daoutils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by dell- on 2017/6/13.
 */
public class UpdateTodayStockJob implements Job {

    public UpdateTodayStockJob() {

    }

    /**
     * 定时执行的任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello World! "+new Date());
    }
}
