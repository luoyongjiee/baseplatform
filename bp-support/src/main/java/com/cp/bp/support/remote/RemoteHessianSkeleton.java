package com.cp.bp.support.remote;

import com.caucho.hessian.io.*;
import com.caucho.hessian.server.HessianSkeleton;
import com.caucho.services.server.ServiceContext;
import com.cp.bp.common.crypt.Cryptoable;
import com.cp.bp.common.util.JsonTool;
import com.cp.bp.core.constant.LogConstant;
import com.cp.bp.core.context.SecurityContext;
import com.cp.bp.core.exception.ServiceException;
import com.cp.bp.core.handler.IExceptionHandler;
import com.cp.bp.core.handler.impl.DefualtExceptionHandler;
import com.cp.bp.core.handler.impl.SimpleExceptionHandler;
import com.cp.bp.core.vo.ErrorInfoVo;
import com.cp.bp.core.vo.UspData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * created by root 2015/6/1
 * 功能：
 */
public class RemoteHessianSkeleton extends HessianSkeleton implements ApplicationContextAware{

    private static final Logger log
            = LoggerFactory.getLogger(RemoteHessianSkeleton.class.getName());

    private ApplicationContext context;

    private  SecurityContext secrityContext;

    private final static Map<Class,IExceptionHandler> EXCEPTION_HANDLER_MAPPING;

    private IExceptionHandler DEFAULT_EXCEPTION_HANDER = new DefualtExceptionHandler();

    static {
        EXCEPTION_HANDLER_MAPPING = new HashMap<Class,IExceptionHandler>();
        EXCEPTION_HANDLER_MAPPING.put(ServiceException.class,new SimpleExceptionHandler());

    }





    /**
     * Create a new hessian skeleton.
     *
     * @param service the underlying service object.
     * @param apiClass the API interface
     */
    public RemoteHessianSkeleton(Object service, Class apiClass)
    {
        super(service,apiClass);

    }


    /**
     * Invoke the object with the request from the input stream.
     *
     * @param in the Hessian input stream
     * @param out the Hessian output stream
     */
    public void invoke(Object service,
                       AbstractHessianInput in,
                       AbstractHessianOutput out)
            throws Exception
    {
        ServiceContext context = ServiceContext.getContext();

        // backward compatibility for some frameworks that don't read
        // the call type first
        in.skipOptionalCall();

        // Hessian 1.0 backward compatibility
        String header;
        while ((header = in.readHeader()) != null) {
            Object value = in.readObject();

            context.addHeader(header, value);
        }

        String methodName = in.readMethod();
        int argLength = in.readMethodArgLength();

        Method method;

        method = getMethod(methodName + "__" + argLength);

        if (method == null)
            method = getMethod(methodName);

        if (method != null) {
        }
        else if ("_hessian_getAttribute".equals(methodName)) {
            String attrName = in.readString();
            in.completeCall();

            String value = null;

            if ("java.api.class".equals(attrName))
                value = getAPIClassName();
            else if ("java.home.class".equals(attrName))
                value = getHomeClassName();
            else if ("java.object.class".equals(attrName))
                value = getObjectClassName();

            out.writeReply(value);
            out.close();
            return;
        }
        else if (method == null) {
            out.writeFault("NoSuchMethodException",
                    "The service has no method named: " + in.getMethod(),
                    null);
            out.close();
            return;
        }

        Class<?> []args = method.getParameterTypes();

        if (argLength != args.length && argLength >= 0) {
            out.writeFault("NoSuchMethod",
                    "method " + method + " argument length mismatch, received length=" + argLength,
                    null);
            out.close();
            return;
        }

        Object []values = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            // XXX: needs Marshal object
            values[i] = in.readObject(args[i]);
        }

        Object result = null;

        //获取接口名
        Class clazz = method.getDeclaringClass();
        MDC.put(LogConstant.INTERFACE_NAME, clazz.getSimpleName() + "." + method.getName());


        try {
            result = method.invoke(service, values);
        } catch (Exception e) {
//            Throwable e1 = e;
//            if (e1 instanceof InvocationTargetException)
//                e1 = ((InvocationTargetException) e).getTargetException();
//
//            log.log(Level.FINE, this + " " + e1.toString(), e1);
//

            IExceptionHandler handler = EXCEPTION_HANDLER_MAPPING.get(e.getClass());

            //没有对应的异常解释器，用默认的
            if (null == handler){
                handler = DEFAULT_EXCEPTION_HANDER;
            }

            ErrorInfoVo errorInfo = handler.resolve(e);

            UspData uspData = new UspData();
            //时间
            long time = System.currentTimeMillis();
            uspData.setTimestamp(time);


            Cryptoable<String> crypt = (Cryptoable<String>) getSecrity().getCryptoable();
            String data = "";
            //签名
            uspData.setSignature(crypt.getSignData(data + "@" + time));
            //返回码
            uspData.setRetCode(errorInfo.getRetCode());
            //返回描述
            uspData.setRetCodeDec(errorInfo.getRetDesc());
            out.writeReply(JsonTool.toJson(uspData));
            out.close();
            return;
        }

        // The complete call needs to be after the invoke to handle a
        // trailing InputStream
        in.completeCall();

        out.writeReply(result);

        out.close();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       if(applicationContext ==null){
           log.error("初始化spring容器失败");
           throw new ServiceException("系统繁忙，请稍后再试！");
       }

        this.context = applicationContext;
    }

    public ApplicationContext getApplicationContext(){
        return this.context;
    }

    public SecurityContext getSecrity() throws SecurityException {
        if (secrityContext == null) {
            secrityContext = (SecurityContext) getApplicationContext().getBean(SecurityContext.BEAN_ID);

            if (secrityContext == null) {
                log.error("[spring容器的安全上下文[security]不能为空！]");
                throw new SecurityException("[spring容器的安全上下文[security]不能为空！]");
            }
        }
        return secrityContext;
    }
}
