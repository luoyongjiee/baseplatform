package com.cp.bp.core.support;


import com.cp.bp.common.util.JsonTool;
import com.cp.bp.core.constant.CommunicationEnum;
import com.cp.bp.core.constant.LogConstant;
import com.cp.bp.core.context.ContextHolder;
import com.cp.bp.core.context.SecurityContext;
import com.cp.bp.core.vo.BaseVo;
import com.cp.bp.core.vo.UspData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;

/**
 * created by root 2015/8/18
 * 功能：
 */
public abstract class ServerService extends JsonSupport {

//    private Logger log = LoggerFactory.getLogger(ServerService.class);
//
//    private SecurityContext secrityContext;
//
//    private ApplicationContext applicationContext;
//
//    public static final long INTERVAL = 1L * 1000L * 60L;
//
//    public String toResponse(String retCode) {
//        return toResponse(null,retCode);
//    }
//
//    public String toResponse(Object resp,String retCode) {
//        return toResponse(resp,retCode,null);
//    }
//
//    public String toResponse(Object resp,String retCode, String retCodeDesc) {
//        return toJson(resp, CommunicationEnum.SUCCESSS.value(),retCode,retCodeDesc);
//    }
//
//    public Object parseRequest(String jsonData, Class clazz)  {
//
//        if (!secrityContext.isCrypt()) {
//            return super.fromRawJson(jsonData, UspData.class);
//        }
//
//        UspData uspData = fromJson(jsonData, clazz);
//
//
//        Object request = JsonTool.fromJson(uspData.getData(), clazz);
//        if (request instanceof BaseVo) {
//            BaseVo baseVo = (BaseVo) request;
//
//            ContextHolder.setHeader(baseVo.getHeader());
//
//
//            if(ContextHolder.getContextInfo() == null){
//                ContextHolder.setContextInfo(new ContextHolder.ContextInfo());
//                ContextHolder.getContextInfo().setBeginTime(System.currentTimeMillis());
//                ContextHolder.getContextInfo().setBaseVo(baseVo);
//            }
//
//
//
//            MDC.put(LogConstant.USER_ID, UserIDBuilder.build(baseVo.getCustType(), baseVo.getCustName(), baseVo.getCertType(), baseVo.getCertID()));
//
//            MDC.put(LogConstant.SESSION_ID, baseVo.getJsid());
//        }else{
//
//        }
//        return request;
//
//    }
//
//
//    //校验时间
//    private boolean validateTimestamp(long time) {
//        return System.currentTimeMillis() - time > INTERVAL ? false : true;
//    }
//
//
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    public SecurityContext getSecrity() throws SecurityException {
//        if (secrityContext == null) {
//            secrityContext = (SecurityContext) applicationContext.getBean(SecurityContext.BEAN_ID);
//
//            if (secrityContext == null) {
//                log.info("[spring容器的安全上下文[security]不能为空！]");
//                throw new SecurityException("[spring容器的安全上下文[security]不能为空！]");
//            }
//        }
//        return secrityContext;
//    }
//
//
//    public <T> UspData fromJson(String data, Class<T> clazz) throws ServerServiceException {
//        UspData uspData = JsonTool.fromJson(data, UspData.class);
//
//        Cryptoable<String> cryptoable = (Cryptoable<String>) secrityContext.getCryptoable();
//        boolean success = cryptoable.verify(uspData + "@" + uspData.getTimestamp(), uspData.getSignature());
//
//        if (!success) {
//            throw new ServerServiceException(RetCode.Common.FAILURE_SERVICE, "验证签名失败！");
//        }
//
//        success = validateTimestamp(uspData.getTimestamp());
//
//        if (!success) {
//            throw new ServerServiceException(RetCode.Common.FAILURE_SERVICE, "时间过期！");
//        }
//
//
//        return uspData;
//    }
//
//    public String toJson(Object obj,String status,String retCode,String retCodeDesc) {
//        if(!secrityContext.isCrypt()){
//            return super.toRawJson(JsonTool.toJson(obj),status,retCode,retCodeDesc);
//        }
//
//        Cryptoable<String> cryptoable = (Cryptoable<String>) secrityContext.getCryptoable();
//
//        UspData uspData = new UspData();
//
//        uspData.setData(JsonTool.toJson(obj));
//
//        uspData.setTimestamp(System.currentTimeMillis());
//
//        uspData.setSignature(cryptoable.getSignData(uspData.getData()+"@"+uspData.getTimestamp()));
//
//        if(status != null){
//            uspData.setStatus(status);
//        }
//
//        if(retCode != null){
//            uspData.setRetCode(retCode);
//        }
//
//        if(retCodeDesc != null){
//            uspData.setRetCodeDec(retCodeDesc);
//        }
//
//
//        return JsonTool.toJson(uspData);
//    }
}
