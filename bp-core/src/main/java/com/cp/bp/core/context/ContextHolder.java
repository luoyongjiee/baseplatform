package com.cp.bp.core.context;

import com.cp.bp.core.vo.BaseVo;
import com.cp.bp.core.vo.HeaderVo;

/**
 * Created by Qifeng-Luo on 2015/11/5.
 */
public class ContextHolder {
    private static ThreadLocal<ContextInfo>  contextInfoHolder = new ThreadLocal<ContextInfo>();

    private static ThreadLocal<HeaderVo> headerHolder = new ThreadLocal<HeaderVo>();

    public static void setContextInfo(ContextInfo contextInfo){
        contextInfoHolder.set(contextInfo);
    }

    public static ContextInfo getContextInfo(){
        return contextInfoHolder.get();
    }

    public static HeaderVo getHeader(){
        return headerHolder.get();
    }

    public static void setHeader(HeaderVo header){
        headerHolder.set(header);
    }

    public static void clearContextInfo(){
        contextInfoHolder.remove();
    }

    public static void clearHeader(){
        headerHolder.remove();
    }

    public static class ContextInfo {
        //请求时间
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
