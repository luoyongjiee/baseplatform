package com.cp.bp.support.vo;

/**
 * created by root 2015/8/18
 * 功能：
 */
public class UspData {
    //报文数据
    private String data;

    //时间
    private long timestamp;

    //系统
    private String sys;

    //签名
    private String signature;

    //返回码
    private String retCode;

    //返回码描述
    private String retCodeDec;

    //通讯状态
    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetCodeDec() {
        return retCodeDec;
    }

    public void setRetCodeDec(String retCodeDec) {
        this.retCodeDec = retCodeDec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
