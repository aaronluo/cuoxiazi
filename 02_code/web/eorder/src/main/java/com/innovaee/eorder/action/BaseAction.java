/***********************************************
 * Filename        : BaseAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action;

import com.innovaee.eorder.vo.MenuLinkVo;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: BaseAction
 * @Description: Action基类
 *
 * @version V1.0
 */
public class BaseAction extends ActionSupport {

    /** 对象序列化ID */
    private static final long serialVersionUID = 6286143366540227001L;

    /** 工具栏列表 */
    protected List<MenuLinkVo> toolbarList = new ArrayList<MenuLinkVo>();

    /** 菜单列表 */
    protected List<MenuLinkVo> menuList = new ArrayList<MenuLinkVo>();

    /** 登录用户名 */
    private String loginName;

    /** ID */
    protected String id;

    /** 当前功能描述 */
    private String currentFunctionDesc;

    /** 当前工具栏 */
    private String currentToolbar;

    /** 总记录条数 */
    protected Integer count = 1;

    /** 总页数 */
    protected Integer pageTotal = 1;

    /** 初始化为1,默认从第一页开始显示 */
    protected Integer pageNow = 1;

    /** 用户输入的页数 */
    protected Integer pageInput = 1;

    /** 操作消息 */
    private String message;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MenuLinkVo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuLinkVo> menuList) {
        this.menuList = menuList;
    }

    public List<MenuLinkVo> getToolbarList() {
        return toolbarList;
    }

    public void setToolbarList(List<MenuLinkVo> toolbarList) {
        this.toolbarList = toolbarList;
    }

    public String getCurrentFunctionDesc() {
        return currentFunctionDesc;
    }

    public void setCurrentFunctionDesc(String currentFunctionDesc) {
        this.currentFunctionDesc = currentFunctionDesc;
    }

    public String getCurrentToolbar() {
        return currentToolbar;
    }

    public void setCurrentToolbar(String currentToolbar) {
        this.currentToolbar = currentToolbar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageInput() {
        return pageInput;
    }

    public void setPageInput(Integer pageInput) {
        this.pageInput = pageInput;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}