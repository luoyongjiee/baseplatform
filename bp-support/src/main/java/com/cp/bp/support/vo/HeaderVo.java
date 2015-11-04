package com.cp.bp.support.vo;

/**
 * created by root 2015/6/12
 * 功能：
 */
public class HeaderVo {

    //会话id
    private String sessionID;

    //系统
    private String os;

    //渠道
    private String channel;

    //接口版本
    private String version;

    //客户端版本
    private String clientVersion;

    //设备id
    private String deviceID;

    //设备名
    private String deviceName;

    //ip
    private String ip;

    private String isGrayable;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsGrayable() {
        return isGrayable;
    }

    public void setIsGrayable(String isGrayable) {
        this.isGrayable = isGrayable;
    }
}
