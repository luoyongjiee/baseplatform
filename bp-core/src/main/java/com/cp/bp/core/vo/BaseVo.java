package com.cp.bp.core.vo;

/**
 * created by root 2015/6/12
 * 功能：
 */
public class BaseVo {

    //头部信息
    private HeaderVo header;
    //证件类型
    private String certType;
    //证件号码
    private String certID;
    //客户类型
    private String custType;
    //客户姓名
    private String custName;


    public HeaderVo getHeader() {
        return header;
    }

    public void setHeader(HeaderVo header) {
        this.header = header;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertID() {
        return certID;
    }

    public void setCertID(String certID) {
        this.certID = certID;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

}
