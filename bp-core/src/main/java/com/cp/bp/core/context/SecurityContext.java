package com.cp.bp.core.context;

import com.cp.bp.common.crypt.Cryptoable;
import org.springframework.context.ApplicationContext;

/**
 * Created by Qifeng-Luo on 2015/11/7.
 */
public class SecurityContext {
    public final static String BEAN_ID = "security";

    private Cryptoable<?> cryptoable;

    //系统
    private String sys;

    private boolean enable;



    ApplicationContext applicationContext;


    public Cryptoable<?> getCryptoable() {
        return cryptoable;
    }

    public void setCryptoable(Cryptoable<?> cryptoable) {
        this.cryptoable = cryptoable;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
