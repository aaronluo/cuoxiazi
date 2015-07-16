/***********************************************
 * Filename        : LoginAction.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.login;

import com.innovaee.eorder.action.BaseAction;

/**
 * @Title: LoginAction
 * @Description: 登录Action
 *
 * @version V1.0
 */
public class LoginAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = 6040009827802629154L;

    /**
     * 进入登录页面
     * 
     * @return
     */
    public String login() {
        return SUCCESS;
    }

    /**
     * 进入主页
     * 
     * @return
     */
    public String doLogin() {
        return SUCCESS;
    }

    /**
     * 退出系统
     * 
     * @return
     */
    public String logout() {
        return SUCCESS;
    }

}