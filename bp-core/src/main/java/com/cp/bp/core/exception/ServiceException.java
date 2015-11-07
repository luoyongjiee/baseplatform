package com.cp.bp.core.exception;

/**
 * created by root 2015/8/18
 * 功能：
 */
public class ServiceException extends RuntimeException{
    private String retCode;

    private String msg;

    public ServiceException(String msg){
        super();
        this.retCode = retCode;
        this.msg = msg;
    }

    public ServiceException(String retCode, String msg){
        super();
        this.retCode = retCode;
        this.msg = msg;
    }
}
