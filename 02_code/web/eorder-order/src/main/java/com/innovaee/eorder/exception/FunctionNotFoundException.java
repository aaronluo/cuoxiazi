/***********************************************
 * Filename       : FunctionNotFoundException.java
 * Copyright      : Copyright (c) 2015
 * Company        : Innovaee
 * Created        : 04/10/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.support.MessageUtil;

/**
 * @Title: FunctionNotFoundException
 * @Description: 功能不存在异常
 * 
 * @version V1.0
 */
public class FunctionNotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;

    static {
        exceptionKey = "function_not_found";
    }

    private String queryCriteria;

    public FunctionNotFoundException(String queryCriteria) {
        this.queryCriteria = queryCriteria;
    }

    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, queryCriteria);
    }
}