package cn.edu.nju.p.dao.daoutils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by dell- on 2017/6/13.
 */

/**
 * 调用定时执行任务的类
 */
public class UpdateTodayStockRun {

    public void Run() {
        //用工厂模式构建Scheduler的Factory，其中STD为Quartz默认的Factory
        SchedulerFactory schedulerfactory=new StdSchedulerFactory();

        //通过schedulerFactory获取一个调度器对象
        Scheduler scheduler=null;

        try{
            //通过schedulerFactory获取一个调度器
            scheduler=schedulerfactory.getScheduler();

            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail job= JobBuilder.newJob(UpdateTodayStockJob.class).withIdentity("job1", "group1").build();

            //定义调度触发规则
            //使用simpleTrigger规则
//            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8))
//                        .startNow().build();

            //使用cornTrigger规则  每天9点执行
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/10 9-17 * * ?") //从早上9点到下午四点每隔十分钟执行一次任务
                    ).build();

            //把作业和触发器注册到任务调度中
            scheduler.scheduleJob(job, trigger);

            //启动调度
            scheduler.start();

            //任务结束后结束
            //scheduler.shutdown(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UpdateTodayStockRun runner = new UpdateTodayStockRun();
        try {
            runner.Run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
