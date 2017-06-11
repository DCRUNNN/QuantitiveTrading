package cn.edu.nju.p.aop;

import cn.edu.nju.p.exception.StockNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by soft on 2017/5/15.
 */
@Aspect
@Component
public class DaoExceptionHandleAop {

    //it seems impossible to use @annotation on an interface method,so abandon the @annotation
    @Pointcut(value = "execution(* cn.edu.nju.p.dao.StockDao.*(..))") //implies all the methods of stock dao
    public void getStockInfo(){}


    @Around(value = "getStockInfo()")
    public Object getStockInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object result = proceedingJoinPoint.proceed();
        if (result == null) {
            Object[] args = proceedingJoinPoint.getArgs();
            if (args.length >= 2) {
                String stockCode = "";
                if (args[0] instanceof String) {
                    stockCode = (String) proceedingJoinPoint.getArgs()[0];
                }
                if (args[1] instanceof LocalDate) {
                    LocalDate date = (LocalDate) proceedingJoinPoint.getArgs()[1];
                    throw new StockNotFoundException(stockCode+" "+date+" "+proceedingJoinPoint.getSignature().getName() + " could not find the stock!");
                }else {
                    throw new StockNotFoundException(proceedingJoinPoint.getSignature().getName() + " could not find the stock!");                }
            }
        }
        return result;
    }

}
