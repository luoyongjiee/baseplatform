package com.cp.bp.core.exception;

/**
 * created by root 2015/8/19
 * 功能：
 */
public class SecurityException extends RuntimeException{

    private String msg;

    public SecurityException(){
        this("");
    }

    public SecurityException(String msg){
        this.msg = msg;
    }
}
