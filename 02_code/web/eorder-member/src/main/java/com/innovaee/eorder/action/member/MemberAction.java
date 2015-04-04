/***********************************************
 * Filename        : DishAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/02/2015
 ************************************************/

package com.innovaee.eorder.action.member;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.service.MemberShipServcie;

import javax.annotation.Resource;


/**
 * @Title: MemberAction
 * @Description: 会员管理Action类
 *
 * @version V1.0
 */
public class MemberAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    
    @Resource
    private MemberShipServcie memberService;
    
}
