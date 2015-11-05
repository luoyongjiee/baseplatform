package com.cp.bp.support.remote;

import com.cp.bp.core.context.ContextHolder;
import com.cp.bp.support.constant.LogConstant;
import org.apache.log4j.MDC;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




/**
 * created by root 2015/6/1
 * 功能：
 */
public class RemoteHessianServiceExporter extends RemoteHessianExporter implements HttpRequestHandler{


    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取真实的ip
        String ip = request.getHeader("x-forwarded-for");

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (!"POST".equals(request.getMethod())) {
            throw new HttpRequestMethodNotSupportedException(request.getMethod(),
                    new String[] {"POST"}, "HessianServiceExporter only supports POST requests");
        }


        response.setContentType(CONTENT_TYPE_HESSIAN);

        ContextHolder.ContextInfo contextInfo = new ContextHolder.ContextInfo();
        contextInfo.setBeginTime(System.currentTimeMillis());
        ContextHolder.setContextInfo(contextInfo);


        MDC.put(LogConstant.IP,ip);
        try {
            invoke(request.getInputStream(), response.getOutputStream());
        }
        catch (Throwable ex) {

        }finally {
            MDC.remove(LogConstant.IP);
            MDC.remove(LogConstant.INTERFACE_NAME);
            MDC.remove(LogConstant.SESSION_ID);
            MDC.remove(LogConstant.USER_ID);
            ContextHolder.clearContextInfo();
            ContextHolder.clearHeader();
        }

    }
}
