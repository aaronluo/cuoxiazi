/***********************************************
 * Filename       : UserLevelNotFoundExcpetion.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/19/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

/**
 * @Title: UserLevelNotFoundExcpetion
 * @Description: 会员等级不存在异常
 * 
 * @version V1.0
 */
public class UserLevelNotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;

    static{
        exceptionKey = "user_level_not_found_exception";
    }
    
    private String queryString;
    
    public UserLevelNotFoundException(String queryString) {
        this.queryString = queryString;
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, queryString);
    }
}
