package com.cp.bp.common.util;

/**
 * Created by Qifeng-Luo on 2015/11/7.
 */
public class UserIDBuilder {

    public static String build(String certType, String certID, String custType, String custName) {
        StringBuilder builder = new StringBuilder();
        builder = builder.append(certType).append("_").append(certID).append("_");
        builder = builder.append(custType).append("_").append(custName);
        return builder.toString();
    }
}
