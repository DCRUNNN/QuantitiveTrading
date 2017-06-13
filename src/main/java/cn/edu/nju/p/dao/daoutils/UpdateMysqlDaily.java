package cn.edu.nju.p.dao.daoutils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by dell- on 2017/5/15.
 */
public class UpdateMysqlDaily {

    public void run(String code,String market) throws Exception{

//        //用工厂模式构建Scheduler的Factory，其中STD为Quartz默认的Factory
//        SchedulerFactory schedulerfactory=new StdSchedulerFactory();
//        //通过schedulerFactory获取一个调度器对象
//        Scheduler scheduler=null;
//        try{
//            //通过schedulerFactory获取一个调度器
//            scheduler=schedulerfactory.getScheduler();
//
//            //创建jobDetail实例，绑定Job实现类
//            //指明job的名称，所在组的名称，以及绑定job类
//            JobDetail job=JobBuilder.newJob(UpdateMysqlDailyJob.class).withIdentity("job1", "group1").build();
//
//            //传递参数给execute方法
//            job.getJobDataMap().put("code", code);
//            job.getJobDataMap().put("market", market);
//            //定义调度触发规则
//            //使用simpleTrigger规则
////            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
////                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8))
////                        .startNow().build();
//
//            //使用cornTrigger规则  每隔2秒钟
//            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")
//                    ).build();
//
//           //把作业和触发器注册到任务调度中
//            scheduler.scheduleJob(job, trigger);
//
//            //启动调度
//            scheduler.start();
//            try {
//                // 当前线程等待0.1秒
//                Thread.sleep(10L * 10L);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            scheduler.shutdown();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        UpdateMysqlDaily test = new UpdateMysqlDaily();
        try {
//            String[] codeList = {"000001", "000002"};
//            String market = "sz"; //新浪的市场是要小写的
//            for(String code:codeList) {
//                test.run(code, market);
//            }
            test.insertDaily();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDaily() throws Exception{

        //用工厂模式构建Scheduler的Factory，其中STD为Quartz默认的Factory
        SchedulerFactory schedulerfactory=new StdSchedulerFactory();

        //通过schedulerFactory获取一个调度器对象
        Scheduler scheduler=null;

        try{
            //通过schedulerFactory获取一个调度器
            scheduler=schedulerfactory.getScheduler();

            //创建jobDetail实例，绑定Job实现类
            //指明job的名称，所在组的名称，以及绑定job类
            JobDetail job=JobBuilder.newJob(UpdateMysqlDailyJob.class).withIdentity("job1", "group1").build();

            //定义调度触发规则
            //使用simpleTrigger规则
//            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8))
//                        .startNow().build();

            //使用cornTrigger规则  每天9点执行
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 9 * * ?")
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


}
