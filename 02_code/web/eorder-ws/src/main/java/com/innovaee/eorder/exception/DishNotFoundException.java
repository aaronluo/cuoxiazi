package com.innovaee.eorder.exception;

import com.innovaee.eorder.exception.BaseException;
import com.innovaee.eorder.support.MessageUtil;

public class DishNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    static {
        exceptionKey = "dish_not_found_exception";
    }
    
    private Long dishId;
    
    public DishNotFoundException(Long dishId) {
        this.dishId = dishId;
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, "dishId:" + dishId);
    }
}
