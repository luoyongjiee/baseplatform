package com.cp.bp.core.support;

import com.cp.bp.common.crypt.Cryptoable;
import com.cp.bp.common.util.JsonTool;
import com.cp.bp.core.context.SecurityContext;
import com.cp.bp.core.exception.ServiceException;
import com.cp.bp.core.vo.UspData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Created by Qifeng-Luo on 2015/11/7.
 */
public class ClientService extends JsonSupport {

    private Logger log = LoggerFactory.getLogger(ServerService.class);

    private SecurityContext secrityContext;

    private ApplicationContext applicationContext;


    public String toRequest(Object obj) {
        String data = JsonTool.toJson(obj);
        UspData uspData = new UspData();
        //主数据
        uspData.setData(data);
        uspData.setSys(getSecrity().getSys());
        //时间
        long time = System.currentTimeMillis();
        uspData.setTimestamp(time);
        Cryptoable<String> crypt = (Cryptoable<String>) getSecrity().getCryptoable();
        uspData.setSignature(crypt.getSignData(data + "@" + time));

        String jsondata = JsonTool.toJson(uspData);
        //打印请求数据
        String viewData = jsondata.replaceAll("\\\\", "");

        viewData = viewData.replaceAll("password\":\"[\\w]*\"", "pwd\":\"******\"");

        log.info("json数据:{}" , viewData);
        return jsondata;
    }


    public Object parseResponse(String json, Class clazz) {
        return fromRawJson(json, clazz);
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (null == context) {
            log.error("初始化spring容器异常");
            throw new ServiceException("系统繁忙，请稍后再试！");
        }

        this.applicationContext = context;
    }

    public SecurityContext getSecrity() throws SecurityException {
        if (secrityContext == null) {
            secrityContext = (SecurityContext) applicationContext.getBean(SecurityContext.BEAN_ID);

            if (secrityContext == null) {
                log.error("[spring容器的安全上下文[security]不能为空！]");
                throw new SecurityException("[spring容器的安全上下文[security]不能为空！]");
            }
        }
        return secrityContext;
    }


}
