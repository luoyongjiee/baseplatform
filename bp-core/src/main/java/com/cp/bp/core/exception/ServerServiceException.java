package com.cp.bp.core.exception;

/**
 * created by root 2015/8/18
 * 功能：
 */
public class ServerServiceException extends Exception{
    private String retCode;

    private String msg;

    public ServerServiceException(String retCode, String msg){
        super();
        this.retCode = retCode;
        this.msg = msg;
    }
}
