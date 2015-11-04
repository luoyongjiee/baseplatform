package com.cp.bp.support.context;


import com.cp.bp.support.vo.BaseVo;
import com.cp.bp.support.vo.HeaderVo;

/**
 * created by root 2015/6/13
 * 功能：
 */
public class UspContext {

    private static ThreadLocal<ContextInfo>  contextInfoThreadLocal = new ThreadLocal<ContextInfo>();

    private static ThreadLocal<HeaderVo> headerThreadLocal = new ThreadLocal<HeaderVo>();

    public static void setContextInfo(ContextInfo contextInfo){
        contextInfoThreadLocal.set(contextInfo);
    }

    public static ContextInfo getContextInfo(){
       return contextInfoThreadLocal.get();
    }

    public static HeaderVo getHeader(){
        return headerThreadLocal.get();
    }

    public static void setHeader(HeaderVo header){
        headerThreadLocal.set(header);
    }

    public static void clearContextInfo(){
        contextInfoThreadLocal.remove();
    }

    public static void clearHeader(){
        headerThreadLocal.remove();
    }

    public static class ContextInfo {
        //开始访问时间
        private long beginTime;

        private BaseVo baseVo;

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public BaseVo getBaseVo() {
            return baseVo;
        }

        public void setBaseVo(BaseVo baseVo) {
            this.baseVo = baseVo;
        }

    }
}
