/***********************************************
 * Filename        : LoginAction.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.login;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

/**
 * @Title: LoginAction
 * @Description: 登录Action
 *
 * @version V1.0
 */
public class LoginAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = 6040009827802629154L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Login";

    /** 数据库中对应的功能描述常量 */
    public String nextNamespace;

    /** 数据库中对应的功能描述常量 */
    public String nextAction;

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
        // 当前用户的工具栏
        List<MenuLinkVO> toolbarList = MenuUtil.getToolbarLinkVOList();

        List<MenuLinkVO> menuLink = null;
        if (null != toolbarList && 0 < toolbarList.size()) {
            // 第一个功能对应的菜单
            menuLink = MenuUtil.getMenuLinkVOList(FUNCTION_DESC);
        }

        this.setToolbarList(toolbarList);
        this.setMenuList(menuLink);
        this.setCurrentFunctionDesc(FUNCTION_DESC);
        this.setCurrentToolbar(MenuUtil.getParentFunctionDesc(FUNCTION_DESC));

        // DishAdmin
        if (null != toolbarList && 0 < toolbarList.size()) {
            MenuLinkVO parentMenuLinkVO = toolbarList.get(0);
            if (null != parentMenuLinkVO && null != parentMenuLinkVO.getList()
                    && 0 < parentMenuLinkVO.getList().size()) {
                // 取第一个功能
                MenuLinkVO menuLinkVO = parentMenuLinkVO.getList().get(0);
                String link = menuLinkVO.getLink();
                String[] str = link.split("/");
                if (null == str || 3 != str.length) {
                    return ERROR;
                } else {
                    // 设置名称空间
                    setNextNamespace(str[1]);
                    String action = str[2];
                    int dotIndex = action.indexOf(".");
                    if (-1 == dotIndex) {
                        return ERROR;
                    } else {
                        // 设置Action
                        setNextAction(action.substring(0, dotIndex));
                    }
                }
            } else {

            }
        } else {
            return ERROR;
        }

        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());

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

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public String getNextNamespace() {
        return nextNamespace;
    }

    public void setNextNamespace(String nextNamespace) {
        this.nextNamespace = nextNamespace;
    }

}