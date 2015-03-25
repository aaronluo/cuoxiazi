/***********************************************
 * Filename       : UserNotFoundException.java
 * Copyright     : Copyright (c) 2014
 * Company       : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

/**
 * @Title: UserNotFoundException
 * @Description: 用户未找到异常
 * 
 * @version V1.0
 */

public class UserNotFoundException extends BaseException {

    static {
        exceptionKey = "user_not_found_exception";
    }
    
    private static final long serialVersionUID = 1L;
    private String queryCriteria;
    
    public UserNotFoundException(String queryCriteria) {
        this.queryCriteria = queryCriteria;
    }
    
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, queryCriteria);
    }
}
