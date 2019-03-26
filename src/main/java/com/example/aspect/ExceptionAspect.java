package com.example.aspect;

import com.example.exception.LogicException;
import com.example.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 异常处理切面
@Component("exceptionAspect")
@Aspect
@Order(1)
@Slf4j
public class ExceptionAspect {

    @Around("execution(* com..controller.*.*(..))")
    public Object exceptionHandle(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (LogicException e) { // 业务逻辑异常，返回Result
            return Result.failure(e.getMessage());
        } catch (Throwable e) { // 其他错误记日志
            log.error(e.getMessage(), e);
            return Result.failure(e.getMessage());
        }
    }

}