package com.cp.bp.core.exception;

/**
 * created by root 2015/8/19
 * 功能：
 */
public class SecurityContextException extends Exception{

    private String msg;

    public SecurityContextException(){
        this("");
    }

    public SecurityContextException(String msg){
        this.msg = msg;
    }
}
