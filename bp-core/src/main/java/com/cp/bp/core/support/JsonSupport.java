package com.cp.bp.core.support;


import com.cp.bp.common.util.JsonTool;
import com.cp.bp.core.vo.UspData;
import org.apache.log4j.Logger;


/**
 * created by root 2015/8/18
 * 功能：
 */
public abstract class JsonSupport {
   private Logger log = Logger.getLogger(JsonSupport.class);

    public <T> T fromRawJson(String data,Class<T> clazz){

        UspData uspData = JsonTool.fromJson(data, UspData.class);

        data = data.replaceAll("\"password\":\".*\"","\"password\":\"******\"");

//        log.info("json数据：["+data+"]");

        return JsonTool.fromJson(uspData.getData(),clazz);
    }

    public String toRawJson(String data,String status,String retCode,String retCodeDesc){
        UspData uspData = new UspData();

        uspData.setData(JsonTool.toJson(data));

        if(status != null){
            uspData.setStatus(status);
        }

        if(retCode != null){
            uspData.setRetCode(retCode);
        }


        if(retCodeDesc != null){
            uspData.setRetCodeDec(retCodeDesc);
        }

        return JsonTool.toJson(uspData);
    }



}
