package com.cp.bp.core.support;


import com.cp.bp.core.context.ContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by root 2015/6/15
 * 功能：
 */
public class PrintWriter {

    Logger logger = LoggerFactory.getLogger(PrintWriter.class);

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] values = pjp.getArgs();

        String info = String.format("开始调用接口的入参：%s", (String) values[0]);
        logger.info(info);
        Object result = pjp.proceed();

        long time = System.currentTimeMillis() - ContextHolder.getContextInfo().getBeginTime();

        info = String.format("结束调用接口的时间[%s],出参：%s", String.valueOf(time),(String) result);
        logger.info(info);
        return result;
    }
}
