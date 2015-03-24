/***********************************************
 * Filename       : DishNotFoundException.java
 * Copyright     : Copyright (c) 2014
 * Company       : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.exception.BaseException;
import com.innovaee.eorder.support.MessageUtil;


/**
 * @Title: DishNotFoundException
 * @Description: 菜品未找到异常
 * 
 * @version V1.0
 */

public class DishNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    static {
        exceptionKey = "dish_not_found_exception";
    }
    
    private String queryString;
    
    public DishNotFoundException(String queryString) {
        this.queryString = queryString;
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey,  queryString);
    }
}
