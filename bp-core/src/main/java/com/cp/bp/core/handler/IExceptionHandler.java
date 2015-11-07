package com.cp.bp.core.handler;

import com.cp.bp.core.vo.ErrorInfoVo;

/**
 * Created by Qifeng-Luo on 2015/11/7.
 */
public interface IExceptionHandler {
    ErrorInfoVo resolve(Throwable t);
}
