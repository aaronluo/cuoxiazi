/***********************************************
 * Filename       : ZeroOrderItemException.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.exception.BaseException;
import com.innovaee.eorder.support.MessageUtil;

/**
 * @Title: ZeroOrderItemException
 * @Description: 当订单中菜品明细条目为零的时候，抛出此异常
 * 
 * @version V1.0
 */
public class ZeroOrderItemException extends BaseException {

    private static final long serialVersionUID = 1L;

    static {
        exceptionKey = "zero_order_item_exception";
    }

    /**
     * 构造函数
     */
    public ZeroOrderItemException() {
        
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey);
    }
}
