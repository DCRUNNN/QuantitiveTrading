package cn.edu.nju.p.aop;

import cn.edu.nju.p.baseresult.BaseResult;
import cn.edu.nju.p.enums.ErrorCode;
import cn.edu.nju.p.utils.StockHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by soft on 2017/5/17.
 */
@Aspect
@Component
public class StockCodeValidationAop {

    @Pointcut("execution(* cn.edu.nju.p.controller.exhibition.CompanyController.*(..))")
    public void getCompanyInfoAndNews(){}

    @Around("getCompanyInfoAndNews()")
    public Object stockCodeExistCheck(ProceedingJoinPoint proceedingJoinPoint) {
        String code = (String) proceedingJoinPoint.getArgs()[0];
        if (! StockHelper.codeExists(code)) {
            return new BaseResult(ErrorCode.STOCK_NOT_FOUND.getErrorCode(), "Code of "+ code + " Not Exists!");
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
