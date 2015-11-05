package com.cp.bp.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Qifeng-Luo on 2015/11/5.
 */
public class JsonTool {


    /**
     * 转换为json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    /**
     * 由json转换为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class clazz){
        Gson gson = new Gson();
        return (T)gson.fromJson(json,clazz);
    }

    /**
     * 由json转换为对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Type type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }
}
