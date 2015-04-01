/***********************************************
 * Filename        : Order.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/30/2014
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

/**
 * @Title: BaseEntity
 * @Description: 实体基类
 * 
 * @version V1.0
 */
public class OrderNotFoundException extends BaseException {
    static {
        exceptionKey = "order_not_found_exception";
    }
    
    private static final long serialVersionUID = 1L;

    private String queryString;
    
    public OrderNotFoundException(String queryString) {
        this.queryString = queryString;
    }
    
    public String getMessage() {
        return MessageUtil.getMessage("exceptionKey", queryString);
    }
}
