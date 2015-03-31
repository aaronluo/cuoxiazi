/***********************************************
 * Filename       : DuplicateNameException.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/10/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

/**
 * @Title: DuplicateNameException
 * @Description: 命名重复异常
 * 
 * @version V1.0
 */
public class DuplicateNameException extends BaseException {

    private static final long serialVersionUID = 1L;
    
    private String duplicateName;

    static {
        exceptionKey = "duplicate_name_exception";
    }
    
    public DuplicateNameException(String name) {
        duplicateName = name;
    }
    
    
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, this.duplicateName);
    }
}
