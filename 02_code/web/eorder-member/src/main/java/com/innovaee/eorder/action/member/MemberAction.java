/***********************************************
 * Filename        : MemberAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/02/2015
 ************************************************/

package com.innovaee.eorder.action.member;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.service.MemberShipServcie;
import com.innovaee.eorder.service.RoleService;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

/**
 * @Title: MemberAction
 * @Description: 会员管理Action类
 *
 * @version V1.0
 */
public class MemberAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    public static final String FUNCTION_DESC = "MemberShip";
    /** Spring DI 会员服务实例 **/
    @Resource
    private MemberShipServcie memberService;
    /** Spring DI 用户服务实例 **/
    @Resource
    private UserService userService;
    /** 角色服务类对象 */
    @Resource
    private RoleService roleService;
    /** 页面显示用户会员列表 **/
    private List<User> users = new ArrayList<User>();
    /** 接受用户创建信息的值对象实例 */
    private User user = new User();
    /** 页面显示会员等级列表 **/
    private List<UserLevel> levels = new ArrayList<UserLevel>();
    /** 选中会员等级实体 **/
    private UserLevel level = new UserLevel();
    /**新会员等级积分*/
//    private int newScore;
//
//    public int getNewScore() {
//        return newScore;
//    }
//
//    public void setNewScore(int newScore) {
//        this.newScore = newScore;
//    }

    /**
     * 显示特定会员等级下的会员分页列表
     * 
     * @return
     */
    public String list() {
        // 获取会员等级下拉列表数据
        levels = this.memberService.getAllUserLevels();
        // 刷新会员分页
        refreshMemberList();
        // 刷新菜单
        this.refreshPageData();
        
        return SUCCESS;
    }

    /**
     * 响应ADD按钮的Action
     * 
     * @return
     */
    public String add() {
        try {
            level = memberService.getUserLevelById(level.getId());
            levels = memberService.getAllUserLevels();
            user = new User();
        } catch (Exception exception) {
            logger.error(exception.getMessage());

            this.setMessage(exception.getMessage());
            return ERROR;
        } finally {

            this.refreshPageData();
        }

        return SUCCESS;
    }

    /** 创建新用户 */
    public String save() {
        try {
            level = memberService.getUserLevelById(level.getId());
            // 验证输入用户名是否为手机号
           
            if (!StringUtil.isMobileNO(user.getCellphone())) {
                logger.error(MessageUtil.getMessage("cellphone_invalid"));
                this.setMessage(MessageUtil.getMessage("cellphone_invalid"));
                
                return ERROR;
            } else {
                user.setCellphone(user.getCellphone().trim());
                final User checkUser = userService.findUserByUserName(user.getCellphone());
                if (null != checkUser) {
                    if(null == checkUser.getMemberShip()){
                        memberService.addMemberShipToUser(level.getId(), checkUser.getId(), true);
                        this.refreshMemberList();
                        
                        return SUCCESS;
                    }else{
                        this.setMessage(MessageUtil.getMessage("member_already_exists", 
                                checkUser.getMemberShip().getLevel().getLevelName()));
                        return ERROR;          
                    }
                } else {
                    user.setUsername(user.getCellphone());
                    user.setPassword(user.getCellphone());
                    user.setUserStatus(true);
                    user.setCreateDate(new Date());
                    Set<Role> roles = new HashSet<Role>();
                    roles.add(roleService.loadRole(Constants.DEFAULT_ROLE));
                    Long newUserId = userService.saveUser(user);
                    // 刷新用户实体
                    user = userService.loadUser(newUserId);

                    memberService.addMemberShipToUser(level.getId(),
                            user.getId(), true);
                }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            this.setMessage(exception.getMessage());

            return ERROR;
        } finally {
            levels = memberService.getAllUserLevels();
            this.refreshPageData();
        }

        this.refreshMemberList();
        
        this.setMessage(MessageUtil.getMessage("member_add_success"));
        return SUCCESS;
    }

    /**
     * 响应编辑按钮的Action
     * */
    public String edit() {
        try {
            user = userService.loadUser(user.getId());
//            level = memberService.getUserLevelById(level.getId());
            level = user.getMemberShip().getLevel();
            levels = memberService.getAllUserLevels();
        } catch (Exception exception) {
            logger.error(exception.getMessage());

            setMessage(exception.getMessage());
            return ERROR;
        } finally {
            this.refreshPageData();
        }

        return SUCCESS;
    }

    /**
     * 更新用户会员
     * 
     * @return
     */
    public String update() {
        try{
            int newScore = user.getMemberShip().getCurrentScore();
            
            if(newScore < 0) {
                this.setMessage(MessageUtil.getMessage("member_bad_score"));
                
                return ERROR;
            }
            
            if(null != user) {             
                if(newScore != user.getMemberShip().getCurrentScore() 
                        || newScore != user.getMemberShip().getLevel().getLevelScore()) {
                    memberService.updateUserMemberShip(user.getId(), newScore);
                    
                    level = userService.findUserByCellphone(user.getCellphone()).getMemberShip().getLevel();
                }
            }
            
        }catch(Exception exception) {
            logger.error(exception.getMessage());
            
            setMessage(exception.getMessage());
            return ERROR;
        }finally{
            levels = memberService.getAllUserLevels();
            this.refreshMemberList();
            this.refreshPageData();
        }
        
        
        setMessage(MessageUtil.getMessage("member_update_success"));
        return SUCCESS;
    }

    public String remove() {
        try {
            user = userService.loadUser(user.getId());

            if (null == user) {
                throw new UserNotFoundException(MessageUtil.getMessage(
                        "user_id", user.getId() + ""));
            }

            level = user.getMemberShip().getLevel();
            memberService.deleteMemberShipOfUser(user.getId());
        } catch (Exception exception) {
            logger.error(exception.getMessage());

            setMessage(exception.getMessage());
            return ERROR;
        } finally {
            levels = memberService.getAllUserLevels();
            
            this.refreshMemberList();
            this.refreshPageData();
        }

        return SUCCESS;
    }

    public MemberShipServcie getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberShipServcie memberService) {
        this.memberService = memberService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserLevel> getLevels() {
        return levels;
    }

    public void setLevels(List<UserLevel> levels) {
        this.levels = levels;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    /**
     * 刷新用户会员分页
     */
    private void refreshMemberList() {
        if (level.getId() != null && level.getId() > 0L) {
            // 选择了一个会员等级
            try {
                count = memberService.getUsersByUserLevelCount(level.getId());
                pageTotal = memberService.getUsersByUserLevelPageCount(level.getId(),
                        Constants.PAGE_SIZE);

                if (pageInput != null) {
                    pageNow = pageInput;
                }

                if (pageNow < 1) {
                    pageNow = 1;
                } else if (pageNow > pageTotal) {
                    pageNow = pageTotal;
                }

                users = memberService.getUsersbyUserLevel(level.getId(), pageNow,
                        Constants.PAGE_SIZE);
            }catch(PageIndexOutOfBoundExcpeiton exception) {
//                if(StringUtil.isEmpty(user.getCellphone())){
                    this.setMessage(MessageUtil.getMessage("member_empty"));
//                }
            }
            catch (Exception exception) {
                logger.error(exception.getMessage());
                this.setMessage(exception.getMessage());
            }
        } else { 
                this.setMessage(MessageUtil.getMessage("member_select_level"));
        }
        
        this.refreshPageData();
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
