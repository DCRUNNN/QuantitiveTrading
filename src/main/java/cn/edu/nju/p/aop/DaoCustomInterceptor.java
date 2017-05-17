package cn.edu.nju.p.aop;

import cn.edu.nju.p.exception.StockNotFoundException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * the custom method interceptor
 */
@Component
public class DaoCustomInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object result = methodInvocation.proceed();
        if (result == null) {
            String methodName = methodInvocation.getMethod().getName();
            throw new StockNotFoundException(methodName + " could not found the Stock!");
        }
        return result;
    }
}
