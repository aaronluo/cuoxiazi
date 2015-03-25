/***********************************************
 * Filename       : UpdateUserLevelException.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/23/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.support.MessageUtil;

/**
 * @Title: UpdateUserLevelException
 * @Description: 更新会员等级失败异常
 * 
 * @version V1.0
 */
public class UpdateUserLevelException extends BaseException {

    static {
        exceptionKey="update_user_level_exception";
    }
    
    private static final long serialVersionUID = 1L;

    private String operationOnUserlevel;
    
    public UpdateUserLevelException(String operationOnUserlevel) {
        super();
        this.operationOnUserlevel = operationOnUserlevel;
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, operationOnUserlevel);
    }
}
