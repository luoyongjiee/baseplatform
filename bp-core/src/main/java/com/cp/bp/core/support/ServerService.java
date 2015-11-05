package com.cp.bp.core.support;


/**
 * created by root 2015/8/18
 * 功能：
 */
public abstract class ServerService extends JsonSupport {

//    private Logger log = Logger.getLogger(ServerService.class);
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
//        return toJson(resp,CommunicationEnum.SUCCESSS.value(),retCode,retCodeDesc);
//    }
//
//    public Object parseRequest(String jsonData, Class clazz) throws ServerServiceException {
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
//            UspContext.setHeader(baseVo.getHeader());
//
//
//            if(UspContext.getContextInfo() == null){
//                UspContext.setContextInfo(new UspContext.ContextInfo());
//                UspContext.getContextInfo().setBeginTime(System.currentTimeMillis());
//                UspContext.getContextInfo().setBaseVo(baseVo);
//            }
//
//
//
//            MDC.put(UspLogEnum.USER_ID, UserIDBuilder.build(baseVo.getCustType(), baseVo.getCustName(), baseVo.getCertType(), baseVo.getCertID()));
//
//            MDC.put(UspLogEnum.SESSION_ID, baseVo.getJsid());
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
//    public SecurityContext getSecrity() throws SecurityContextException {
//        if (secrityContext == null) {
//            secrityContext = (SecurityContext) applicationContext.getBean(SecurityContext.BEAN_ID);
//
//            if (secrityContext == null) {
//                log.info("[spring容器的安全上下文[security]不能为空！]");
//                throw new SecurityContextException("[spring容器的安全上下文[security]不能为空！]");
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
