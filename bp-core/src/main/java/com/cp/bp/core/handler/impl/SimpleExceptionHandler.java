package com.cp.bp.core.handler.impl;

import com.cp.bp.core.handler.IExceptionHandler;
import com.cp.bp.core.vo.ErrorInfoVo;

/**
 * Created by Qifeng-Luo on 2015/11/7.
 */
public class SimpleExceptionHandler implements IExceptionHandler{

    public ErrorInfoVo resolve(Throwable t) {
        return null;
    }
}
