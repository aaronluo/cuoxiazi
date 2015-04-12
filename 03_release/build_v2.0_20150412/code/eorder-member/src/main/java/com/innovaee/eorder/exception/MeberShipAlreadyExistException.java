/***********************************************
 * Filename       : MemberShipServcie.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/24/2015
 ************************************************/

package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

/**
 * @Title: MemberShipServcie
 * @Description: 用户会员管理服务接口
 * 
 * @version V1.0
 */
public class MeberShipAlreadyExistException extends BaseException {

    static {
        exceptionKey = "member_ship_already_existing_exception";
    }
    
    private static final long serialVersionUID = 1L;

    private Long existingMembershipId;
    
    public MeberShipAlreadyExistException(Long id) {
        this.existingMembershipId = id;
    }
    
    @Override
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, ""+existingMembershipId);
    }
}
