package com.cp.bp.common.crypt;

/**
 * created by root 2015/6/13
 * 功能：
 */
public interface Cryptoable<T> {

    /**
     * 获取签名的数据
     * @param data
     * @return
     */
    public T getSignData(T data);

    /**
     * 验证
     * @param data
     * @param sign
     * @return
     */
    public boolean verify(T data, T sign);

}
