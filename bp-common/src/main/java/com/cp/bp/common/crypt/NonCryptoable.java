package com.cp.bp.common.crypt;

/**
 * created by root 2015/6/15
 * 功能：
 */
public class NonCryptoable implements Cryptoable<String>{
    public String getSignData(String data) {
        return data;
    }

    public boolean verify(String data, String sign) {
        return false;
    }
}
