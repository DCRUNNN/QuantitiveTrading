package cn.edu.nju.p.utils.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * Created by Xihao on 2017/6/14.
 */
@Component
public class ToolSpring extends WebApplicationObjectSupport {

    private static ApplicationContext applicationContext = null;

    @Override
    protected void initApplicationContext(ApplicationContext context) {
        super.initApplicationContext(context);
        if (ToolSpring.applicationContext == null) {
            ToolSpring.applicationContext = context;
            System.out.println("初始化bean获取类成功！");
        }

    }

    public static  <T>  T getBeans(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

}
