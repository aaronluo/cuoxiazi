/***********************************************
 * Filename       : CategoryNotFoundException.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/10/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;


/**
 * @Title: CategoryNotFoundException
 * @Description: 菜品分类不存在异常
 * 
 * @version V1.0
 */
public class CategoryNotFoundException extends BaseException {
    private static final long serialVersionUID = 1L;
    
    static {
        exceptionKey = "category_not_found";
    }
    
    private String queryCriteria;
    
    public CategoryNotFoundException(String queryCriteria){
        this.queryCriteria = queryCriteria;
    }
    
    
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, queryCriteria);
    }
}
