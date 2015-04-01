/***********************************************
 * Filename        : UserAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.RoleService;
import com.innovaee.eorder.service.UserLevelService;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.Md5Util;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.innovaee.eorder.vo.UserVO;

/**
 * @Title: UserAction
 * @Description: 用户Action（查找和删除）
 *
 * @version V1.0
 */
public class UserAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = 714474963089126216L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "User";

    /** 用户名称 */
    private String username;

    /** 密码 */
    private String passwordOne;

    /** 确认密码 */
    private String passwordTwo;

    /** 电话号码 */
    private String cellphone;

    /** 用户值对象列表 */
    private List<UserVO> uservos = new ArrayList<UserVO>();

    /** 用户服务类对象 */
    @Resource
    private UserService userService;

    /** 用户等级服务类对象 */
    @Resource
    private UserLevelService userLevelService;

    /** 角色服务类对象 */
    @Resource
    private RoleService roleService;

    /** 已有的角色列表 */
    private List<Role> myRoles = new ArrayList<Role>();

    /** 剩余的角色列表 */
    private List<Role> leftRoles = new ArrayList<Role>();

    /** 已有的角色数组 */
    private String myRolesArray;

    /** 剩余的角色数组 */
    private String leftRolesArray;

    /**
     * 进入用户管理页面
     * 
     * @return
     */
    public String user() {
        // 更新页面数据
        refreshPageData();

        // 更新数据列表
        refreshDataList();

        return SUCCESS;
    }

    /**
     * 加载单个用户信息
     * 
     * @return
     */
    public String edit() {
        // 更新页面数据
        refreshPageData();

        if (null != id) {
            User user = userService.loadUser(Long.parseLong(id));
            username = user.getUsername();
            cellphone = user.getCellphone();

            // 加载用户角色信息
            myRoles.addAll(user.getRoles());
            leftRoles = userService.findLeftRolesByUserId(Long.parseLong(id));
        }

        return SUCCESS;
    }

    /**
     * 更新
     * 
     * @return
     */
    public String update() {
        // 更新权限列表
        refreshFunctionList();
        // 更新页面数据
        refreshPageData();

        // 查看用户名是否已存在
        User user1 = userService.loadUser(Long.parseLong(id));
        User user2 = userService.findUserByUserName(username);
        // 可以找到，而且和自己的名字不同，则说明已经被占用
        if (null != user2
                && (user1.getId().intValue() != user2.getId().intValue())) {
            this.setMessage(MessageUtil.getMessage("username_occupy"));
            return INPUT;
        }

        // 查看手机号码是否已存在
        // 可以找到，而且和自己的手机不同，则说明已经被占用
        User user3 = userService.findUserByCellphone(cellphone);
        if (null != user3 && !cellphone.equals(user1.getCellphone())) {
            this.setMessage(MessageUtil.getMessage("cellphone_occupy"));
            return INPUT;
        }

        // 校验用户角色不能为空
        if (null == myRolesArray || "".equals(myRolesArray)) {
            this.setMessage(MessageUtil.getMessage("role_list_empty"));
            return INPUT;
        }

        User user = null;
        if (null != id) {
            user = userService.loadUser(Long.parseLong(id));
        }

        if (null != username && !"".equals(username.trim())) {
            user.setUsername(username);
        } else {
            this.setMessage(MessageUtil.getMessage("username_empty"));
            return INPUT;
        }

        // 如果相同，则检查两个新密码是否相同
        // 如果两次输入的密码都不为空，且相同
        if (null == passwordOne || "".equals(passwordOne.trim())) {
            // 两个密码都没有输入，也不同
            this.setMessage(MessageUtil.getMessage("password_empty"));
            return INPUT;
        }

        if (null == passwordTwo || "".equals(passwordTwo.trim())) {
            // 两个密码都没有输入，也不同
            this.setMessage(MessageUtil.getMessage("passwordTwo_empty"));
            return INPUT;
        }

        if (!passwordOne.equals(passwordTwo)) {
            this.setMessage(MessageUtil.getMessage("password_different"));
            return INPUT;
        } else {
            // 如果不相同，则说明修改了密码，则需要加密后再存储
            String md5Password = Md5Util.getMD5Str(passwordOne + "{" + username
                    + "}");
            user.setPassword(md5Password);
        }

        if (null != cellphone && !"".equals(cellphone.trim())) {
            if (StringUtil.isMobileNO(cellphone)) {
                user.setCellphone(cellphone);
            } else {
                this.setMessage(MessageUtil.getMessage("cellphone_invalid"));
                return INPUT;
            }
        } else {
            this.setMessage(MessageUtil.getMessage("cellphone_empty"));
            return INPUT;
        }

        // 更新角色信息
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.addAll(this.getMyRoles());
        user.setRoles(roleSet);
        userService.updateUser(user);
        this.setMessage(MessageUtil.getMessage("update_success"));

        return SUCCESS;
    }

    /**
     * 增加一个save方法，对应一个处理逻辑
     * 
     * @return
     */
    public String save() {
        // 更新权限列表
        refreshFunctionList();
        // 更新页面数据
        refreshPageData();

        // 查看用户名是否已存在
        User dbuser = userService.findUserByUserName(username);
        if (null != dbuser) {
            this.setMessage(MessageUtil.getMessage("username_occupy"));
            return INPUT;
        }

        User user = new User();
        if (null != username && !"".equals(username.trim())) {
            user.setUsername(username);
        } else {
            this.setMessage(MessageUtil.getMessage("username_empty"));
            return INPUT;
        }

        // 如果相同，则检查两个新密码是否相同
        // 如果两次输入的密码都不为空，且相同
        if (null == passwordOne || "".equals(passwordOne.trim())) {
            // 两个密码都没有输入，也不同
            this.setMessage(MessageUtil.getMessage("password_empty"));
            return INPUT;
        }

        if (null == passwordTwo || "".equals(passwordTwo.trim())) {
            // 两个密码都没有输入，也不同
            this.setMessage(MessageUtil.getMessage("passwordTwo_empty"));
            return INPUT;
        }

        // 判断两次密码是否一致
        if (!passwordOne.equals(passwordTwo)) {
            this.setMessage(MessageUtil.getMessage("password_different"));
            return INPUT;
        } else {
            String md5Password = Md5Util.getMD5Str(passwordOne + "{" + username
                    + "}");
            user.setPassword(md5Password);
        }

        // 查看手机号码是否已存在
        dbuser = userService.findUserByCellphone(cellphone);
        if (null != dbuser) {
            this.setMessage(MessageUtil.getMessage("cellphone_occupy"));
            return INPUT;
        }

        if (null != cellphone && !"".equals(cellphone.trim())) {
            if (StringUtil.isMobileNO(cellphone)) {
                user.setCellphone(cellphone);
            } else {
                this.setMessage(MessageUtil.getMessage("cellphone_invalid"));
                return INPUT;
            }
        } else {
            this.setMessage(MessageUtil.getMessage("cellphone_empty"));
            return INPUT;
        }

        // 校验用户角色不能为空
        if (null == myRolesArray || "".equals(myRolesArray)) {
            this.setMessage(MessageUtil.getMessage("role_list_empty"));
            return INPUT;
        }

//        user.setLevelId(Constants.DEFAULT_LEVEL);
        user.setUserStatus(true);

        Set<Role> myRoleSet = new HashSet<Role>();
        myRoleSet.addAll(this.getMyRoles());
        user.setRoles(myRoleSet);

        // 新增成功
        Long newId = userService.saveUser(user);
        if (newId != 0) {
            this.setMessage(MessageUtil.getMessage("add_success"));
            this.setUsername("");
            this.setPasswordOne("");
            this.setPasswordTwo("");
            this.setCellphone("");
            renewPage();
        } else {
            this.setMessage(MessageUtil.getMessage("add_failure"));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * 新增页面
     * 
     * @return
     */
    public String add() {
        renewPage();
        return SUCCESS;
    }

    /**
     * 
     */
    public void renewPage() {
        // 更新权限列表
        refreshFunctionList();
        // 更新页面数据
        refreshPageData();

        this.setMyRoles(new ArrayList<Role>());
        List<Role> leftRoleList = userService
                .findLeftRolesByUserId(Constants.MAX_ID);
        this.setLeftRoles(leftRoleList);
    }

    /**
     * 校验失败或者新增、修改成功后，刷新列表框
     */
    public void refreshFunctionList() {
        List<Long> myRoleIdList = StringUtil.stringToLongList(myRolesArray,
                Constants.REGEX);
        List<Role> myRoleList = new ArrayList<Role>();
        Role role = null;
        for (Long longValue : myRoleIdList) {
            role = roleService.loadRole(longValue);
            myRoleList.add(role);
        }
        this.setMyRoles(myRoleList);
        List<Long> leftRoleIdList = StringUtil.stringToLongList(leftRolesArray,
                Constants.REGEX);
        List<Role> leftRoleList = new ArrayList<Role>();
        for (Long longValue : leftRoleIdList) {
            role = roleService.loadRole(longValue);
            leftRoleList.add(role);
        }
        this.setLeftRoles(leftRoleList);
    }

    /**
     * 刷新页面数据
     */
    public void refreshPageData() {
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

    /**
     * 更新数据列表
     */
    public void refreshDataList() {
        Integer recordCount = userService.count();
        this.setCount(recordCount);
        int pageTotal = 1;
        if (0 == recordCount % Constants.PAGE_SIZE) {
            pageTotal = recordCount / Constants.PAGE_SIZE;
        } else {
            pageTotal = recordCount / Constants.PAGE_SIZE + 1;
        }
        this.setPageTotal(pageTotal);

        // 处理用户点击【上一页】和【下一页】边界情况
        if (pageNow > pageTotal) {
            pageNow = pageTotal;
            pageInput = pageNow;
        } else if (pageNow < 1) {
            pageNow = 1;
            pageInput = 1;
        }

        List<User> users = null;
        if (null != pageInput) {
            if (pageInput > pageTotal) {
                pageInput = pageTotal;
                pageNow = pageTotal;
            } else if (pageInput < 1) {
                pageNow = 1;
                pageInput = 1;
            }
            users = userService.getUsersByPage((pageInput - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
            pageNow = pageInput;
        } else {
            users = userService.getUsersByPage((pageNow - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }

        UserVO uservo = null;
        UserLevel userLevel = null;
        for (User user : users) {
            uservo = new UserVO();
            BeanUtils.copyProperties(user, uservo);
//            userLevel = userLevelService.loadUserLevel(user.getLevelId());
            userLevel = user.getMemberShip().getLevel();

            Set<Role> roles = user.getRoles();
            List<String> roleNameList = new ArrayList<String>();
            for (Role role : roles) {
                roleNameList.add(role.getRoleName());
            }
            uservo.setRoleName(roleNameList.toString());
            uservo.setLevelName(userLevel.getLevelName());
            uservos.add(uservo);
        }
    }

    /**
     * 删除用户
     * 
     * @return
     */
    public String remove() {
        // 更新页面数据
        refreshPageData();

        if (null != id) {
            userService.deleteUser(Long.parseLong(id));
        }

        this.setMessage("删除成功！");

        // 更新数据列表
        refreshDataList();
        return SUCCESS;
    }

    public List<UserVO> getUservos() {
        return uservos;
    }

    public void setUservos(List<UserVO> uservos) {
        this.uservos = uservos;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public List<Role> getMyRoles() {
        return myRoles;
    }

    public void setMyRoles(List<Role> myRoles) {
        this.myRoles = myRoles;
    }

    public List<Role> getLeftRoles() {
        return leftRoles;
    }

    public void setLeftRoles(List<Role> leftRoles) {
        this.leftRoles = leftRoles;
    }

    public String getMyRolesArray() {
        return myRolesArray;
    }

    public void setMyRolesArray(String myRolesArray) {
        this.myRolesArray = myRolesArray;
    }

    public String getLeftRolesArray() {
        return leftRolesArray;
    }

    public void setLeftRolesArray(String leftRolesArray) {
        this.leftRolesArray = leftRolesArray;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public String getPasswordOne() {
        return passwordOne;
    }

    public void setPasswordOne(String passwordOne) {
        this.passwordOne = passwordOne;
    }

    public String getPasswordTwo() {
        return passwordTwo;
    }

    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
    }

}