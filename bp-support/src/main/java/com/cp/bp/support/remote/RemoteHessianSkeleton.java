package com.cp.bp.support.remote;

import com.caucho.hessian.io.*;
import com.caucho.hessian.server.HessianSkeleton;
import com.caucho.services.server.ServiceContext;
import com.cp.bp.core.constant.LogConstant;
import org.apache.log4j.MDC;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created by root 2015/6/1
 * 功能：
 */
public class RemoteHessianSkeleton extends HessianSkeleton {

    private static final Logger log
            = Logger.getLogger(RemoteHessianSkeleton.class.getName());

    private ApplicationContext context;





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
        MDC.put(LogConstant.INTERFACE_NAME,clazz.getSimpleName()+":"+method.getName());


        try {
            result = method.invoke(service, values);
        } catch (Exception e) {
            Throwable e1 = e;
            if (e1 instanceof InvocationTargetException)
                e1 = ((InvocationTargetException) e).getTargetException();

            log.log(Level.FINE, this + " " + e1.toString(), e1);

            out.writeFault("ServiceException", e1.getMessage(), e1);
            out.close();
            return;
        }

        // The complete call needs to be after the invoke to handle a
        // trailing InputStream
        in.completeCall();

        out.writeReply(result);

        out.close();
    }




    public interface ExceptionHandler{
        String getErrorMsg();
    }



    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
