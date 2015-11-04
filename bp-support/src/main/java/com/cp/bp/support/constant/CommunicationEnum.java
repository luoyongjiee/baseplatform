package com.cp.bp.support.constant;

/**
 * created by root 2015/8/18
 * 功能：
 */
public enum CommunicationEnum {

    SUCCESSS("200"),FAILURE_INNER_EXCEPTION("500"),FAILURE_COMMUNICATION_REJECT("600");

    private String value;

    private CommunicationEnum(String value){
        this.value = value;
    }

    public String value(){
        return this.value;
    }
}
