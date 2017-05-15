package cn.edu.nju.p.aop;

import cn.edu.nju.p.exception.StockNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by soft on 2017/5/15.
 */
//@Aspect
public class DaoExceptionHandleAop {

    @Pointcut("execution(* cn.edu.nju.p.dao.StockDao.*(..))") //implies all the methods of stock dao
    public void getStockInfo(){}

    @Around(value = "getStockInfo()")
    public Object getStockInfo(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            if (result == null) {
                throw new StockNotFoundException(proceedingJoinPoint.getSignature().getName() + " could not find the stock!");
            }
        } catch (Throwable throwable) {
            /**
             * to be complete
             */
        }
        return result;
    }
}
