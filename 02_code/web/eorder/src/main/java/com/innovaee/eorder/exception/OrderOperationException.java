package com.innovaee.eorder.exception;

import com.innovaee.eorder.support.MessageUtil;

public class OrderOperationException extends BaseException {
    static {
        exceptionKey = "order_operation_exception";
    }
    private static final long serialVersionUID = 1L;

    private String operation;
    
    public OrderOperationException(String operation) {
        this.operation = operation;
    }
    
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, operation);
    }
}
