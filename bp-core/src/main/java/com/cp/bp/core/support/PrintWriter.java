package com.cp.bp.core.support;


import com.cp.bp.core.context.ContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * created by root 2015/6/15
 * 功能：
 */
public class PrintWriter {

    Logger logger = LoggerFactory.getLogger(PrintWriter.class);

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        //方法名
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        //类名
        String clazzName =method.getClass().getSimpleName();

        //入口参数
        String args = Arrays.asList(pjp.getArgs()).toString();
        logger.info("调用接口{}.{}，入参：{}",clazzName,methodName,args);
        Object result = pjp.proceed();

        long time = System.currentTimeMillis() - ContextHolder.getContextInfo().getBeginTime();
        logger.info("调用接口{}.{}结束，调用时间：{}",clazzName,methodName,time);
        return result;
    }
}
