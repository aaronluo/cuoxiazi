/***********************************************
 * Filename        : DishAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/02/2015
 ************************************************/

package com.innovaee.eorder.action.member;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.UserLevelNotFoundException;
import com.innovaee.eorder.service.MemberShipServcie;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.innovaee.eorder.vo.UserLevelVO;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: MemberAction
 * @Description: 会员等级管理Action类
 *
 * @version V1.0
 */

@Conversion(
        conversions={
                @TypeConversion(key="level.discount", 
                        converter="com.innovaee.eorder.utils.FloatConverter"),
                @TypeConversion(key="level.levelScore",
                        converter="com.innovaee.eorder.utils.IntegerConverter")
        }
)
public class LevelAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Resource
    private MemberShipServcie memberService;

    public static final String FUNCTION_DESC = "UserLevel";

    List<UserLevel> levels;
    
    UserLevelVO level;

    /**
     * 显示会员等级列表Action
     * 
     * @return
     */
    public String list() {
        level = new UserLevelVO();
        // 取得当前页面需要的会员等级信息集合
        getLevelList(); 

        // 刷新系统菜单
        this.refreshPageData();

        return SUCCESS;
    }



    public String add(){
       level = new UserLevelVO();
        
       getLevelList(); 
       refreshPageData();
        return SUCCESS;
    }
    
    public String save() {
        logger.debug(level);
        
        try {
            //1. 检查传入的会员等级属性是否合法
            if(!checkLevelVO()) {
                return ERROR;
            } else {
                //2. 创建新的会员等级
                memberService.addUserLevel(level);
                setMessage(MessageUtil.getMessage("level_add_success", level.getName()));   
            }
        } catch (DuplicateNameException e) {
            logger.error(e.getMessage());
            setMessage(e.getMessage());
            
            return ERROR;
        }finally{
            getLevelList(); 
            refreshPageData();
        }
        
        return SUCCESS;
    }

    public String remove() {
        try {
            memberService.deleteUserLevel(level.getId());
            setMessage(MessageUtil.getMessage("level_remove_success"));
        } catch (UserLevelNotFoundException e) {
            logger.error(e.getMessage());
            
            setMessage(e.getMessage());
            return ERROR;
        }finally{
            getLevelList(); 
            refreshPageData();
        }
        
        return SUCCESS;
    }
    
    public List<UserLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<UserLevel> levels) {
        this.levels = levels;
    }

    public UserLevelVO getLevel() {
        return level;
    }

    public void setLevel(UserLevelVO level) {
        this.level = level;
    }
    
    /**
     * 检查一个会员等级的设定是否合法
     */
    private boolean checkLevelVO() {
        boolean isValidVO = true;
        if(level.getDiscount() < 1f || level.getDiscount() > 10f ) {
            isValidVO = false;
            setMessage(MessageUtil.getMessage("level_discount_rule"));
        }
        
        if(StringUtil.isEmpty(level.getName())) {
            isValidVO = false;
            setMessage(MessageUtil.getMessage("level_name_rule"));
        }
        
        if(level.getLevelScore() < 0) {
            isValidVO = false;
            setMessage(MessageUtil.getMessage("level_score_rule"));
        }
        
        return isValidVO;
    }    
    
    /**
     * 获取用户等级列表
     */
    private void getLevelList() {
        try {
            count = memberService.getAllUserLevels().size();
            pageTotal = memberService.getUserLevePageCount(Constants.PAGE_SIZE);
            
//            int curPage = 1;
            
            if(pageInput != null) {
                pageNow = pageInput;
            }

            if (pageNow < 1) {
                pageNow = 1;
            } else if (pageNow > pageTotal) {
                pageNow = pageTotal;
            }
            
            levels = memberService.getUserLevelsByPage(pageNow, Constants.PAGE_SIZE);
        } catch (Exception exception) {
           logger.error(exception.getMessage());
           //异常消息
           this.setMessage(exception.getMessage());
        }
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
