package cn.edu.nju.p.aop;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.enums.ErrorCode;
import cn.edu.nju.p.utils.StockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * an aop to validate stock code to see whether the stock code exists
 */
@Aspect
@Component
public class StockCodeValidationAop {

    @Pointcut("execution(* cn.edu.nju.p.controller.exhibition.*.*(..))")
    public void getCompanyInfoAndNews() {
    }

    @Autowired
    private StockHelper stockHelper;

    /*@Around("getCompanyInfoAndNews()")
    public Object stockCodeExistCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String code = (String) proceedingJoinPoint.getArgs()[0];
        if (! stockHelper.codeExists(code)) {
            return new BaseResult(ErrorCode.STOCK_NOT_FOUND.getErrorCode(), "Code of "+ code + " Not Exists!");
        }
        return proceedingJoinPoint.proceed();
    }*/
}
