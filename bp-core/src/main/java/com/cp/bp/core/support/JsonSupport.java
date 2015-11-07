package com.cp.bp.core.support;


import com.cp.bp.common.util.JsonTool;
import com.cp.bp.core.vo.UspData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * created by root 2015/8/18
 * 功能：
 */
public class JsonSupport {
    private Logger log = LoggerFactory.getLogger(JsonSupport.class);

    public <T> T fromRawJson(String data, Class<T> clazz) {

        UspData uspData = JsonTool.fromJson(data, UspData.class);

        String viewData = data.replaceAll("\\\\", "");

        viewData = viewData.replaceAll("password\":\"[\\w]*\"", "pwd\":\"******\"");

        log.info("json数据：[" + viewData + "]");

        return JsonTool.fromJson(uspData.getData(), clazz);
    }

    public String toRawJson(String data, String status, String retCode, String retCodeDesc) {
        UspData uspData = new UspData();

        uspData.setData(JsonTool.toJson(data));

        if (status != null) {
            uspData.setStatus(status);
        }

        if (retCode != null) {
            uspData.setRetCode(retCode);
        }


        if (retCodeDesc != null) {
            uspData.setRetCodeDec(retCodeDesc);
        }

        return JsonTool.toJson(uspData);
    }


    public static void main(String[] args) {
        Map<String, Map<String, String>> mapMap = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("password", "1231254325");
        map.put("id", "fdiogrfg");
        mapMap.put("kk", map);
        UspData uspData = new UspData();
        uspData.setData(JsonTool.toJson(mapMap));
        JsonSupport jsonSupport = new JsonSupport();
        jsonSupport.fromRawJson(JsonTool.toJson(uspData), UspData.class);

        String str = "aa4d32ee85 ";


    }

}
