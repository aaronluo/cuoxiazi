/***********************************************
 * Filename        : DishAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/02/2015
 ************************************************/

package com.innovaee.eorder.action.member;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.MemberShipServcie;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: MemberAction
 * @Description: 会员等级管理Action类
 *
 * @version V1.0
 */
public class LevelAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private MemberShipServcie memberService;

    public static final String FUNCTION_DESC = "UserLevel";

    List<UserLevel> levels;

    /**
     * 显示会员等级列表Action
     * 
     * @return
     */
    public String list() {
        // 取得当前页面需要的会员等级信息集合
        try {
            count = memberService.getAllUserLevels().size();
            pageTotal = memberService.getUserLevePageCount(Constants.PAGE_SIZE);
            if (pageNow < 1) {
                pageNow = 1;
            } else if (pageNow > pageTotal) {
                pageNow = pageTotal;
            }
            
            levels = memberService.getUserLevelsByPage(pageNow,
                    Constants.PAGE_SIZE);
        } catch (Exception exception) {
           logger.error(exception.getMessage());
           //异常消息
           this.setMessage(exception.getMessage());
        } 

        // 刷新系统菜单
        this.refreshPageData();

        return SUCCESS;
    }

    public List<UserLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<UserLevel> levels) {
        this.levels = levels;
    }

    /**
     * 刷新页面数据
     */
    private void refreshPageData() {
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

        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());
    }
}
