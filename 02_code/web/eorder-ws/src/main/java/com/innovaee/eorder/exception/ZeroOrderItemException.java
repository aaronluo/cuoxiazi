package com.innovaee.eorder.exception;

import com.innovaee.eorder.exception.BaseException;
import com.innovaee.eorder.support.MessageUtil;

public class ZeroOrderItemException extends BaseException {

    private static final long serialVersionUID = 1L;

    static {
        exceptionKey = "zero_order_item_exception";
    }

    public ZeroOrderItemException() {
        
    }
    
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey);
    }
}
