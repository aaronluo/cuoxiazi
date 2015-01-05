/***********************************************
 * Filename        : UserRoleService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: UserRoleService
 * @Description: 用户角色服务
 *
 * @version V1.0
 */
public class UserRoleService extends BaseService {

    /** 用户角色数据访问对象 */
    @Resource
    private UserRoleDao userRoleDao;

    /** 角色数据访问对象 */
    @Resource
    private RoleDao roleDao;

    /** 用户数据访问对象 */
    @Resource
    private UserDao userDao;

    /** 用户角色服务对象 */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 查找所有用户角色
     * 
     * @return 用户角色列表
     */
    public List<UserRole> findAllUserRoles() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("查找所有用户角色");
        }
        return (List<UserRole>) userRoleDao.findAllUserRoles();
    }

    /**
     * 通过用户ID和角色ID查找用户角色
     * 
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole findUserRoleByIds(Integer userId, Integer roleId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过用户ID和角色ID查找用户角色");
        }
        return userRoleDao.findUserRoleByIds(userId, roleId);
    }

    /**
     * 通过用户ID查找该用户拥有的用户角色
     * 
     * @param userId
     *            用户ID
     * @return 用户角色列表
     */
    public List<UserRole> findUserRolesByUserId(Integer userId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过用户ID查找该用户拥有的用户角色");
        }
        return (List<UserRole>) userRoleDao.findUserRolesByUserId(userId);
    }

    /**
     * 通过角色ID查找对应的用户角色
     * 
     * @param roleId
     *            角色ID
     * @return 用户角色列表
     */
    public List<UserRole> findUserRolesByRoleId(Integer roleId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过角色ID查找对应的用户角色");
        }
        return (List<UserRole>) userRoleDao.findUserRolesByRoleId(roleId);
    }

    /**
     * 通过该用户ID查找角色
     * 
     * @param userId
     *            用户ID
     * @return 角色列表
     */
    public List<Role> findRolesByUserId(Integer userId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过该用户ID查找角色");
        }
        List<Role> roles = new ArrayList<Role>();
        List<UserRole> userRoles = userRoleDao.findUserRolesByUserId(userId);
        Role role = null;
        for (UserRole userRole : userRoles) {
            role = roleDao.loadRole(userRole.getRoleId());
            roles.add(role);
        }

        return roles;
    }

    /**
     * 通过角色ID查找剩余的角色
     * 
     * @param roleId
     *            角色ID
     * @return 角色列表
     */
    public List<Role> findLeftRolesByUserId(Integer roleId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过角色ID查找剩余的角色");
        }
        List<Role> leftRoles = new ArrayList<Role>();
        List<Role> roles = new ArrayList<Role>();
        List<UserRole> userRoles = userRoleDao.findUserRolesByUserId(roleId);
        Role role = null;
        for (UserRole userRole : userRoles) {
            role = roleDao.loadRole(userRole.getRoleId());
            roles.add(role);
        }

        List<Role> allRoles = roleDao.findAllRoles();
        for (Role roleDB : allRoles) {
            leftRoles.add(roleDB);
        }

        leftRoles.removeAll(roles);

        return leftRoles;
    }

    /**
     * 保存用户角色
     * 
     * @param userRole
     *            待保存的用户角色
     */
    public void saveUserRole(UserRole userRole) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("保存用户角色");
        }
        userRoleDao.saveUserRole(userRole);
    }

    /**
     * 根据用户信息和角色信息保存用户角色
     * 
     * @param user
     *            用户信息
     * @param role
     *            角色信息
     * @return 用户角色
     */
    public UserRole saveUserRole(User user, Role role) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("保存用户角色");
        }
        UserRole rtnUserRole = null;
        User userDB = (User) userDao.loadUser(user.getUserId());
        Role roleDB = (Role) roleDao.loadRole(role.getRoleId());
        UserRole userRole = null;
        if (null != userDB && null != roleDB) {
            Integer userId = userDB.getUserId();
            Integer roleId = roleDB.getRoleId();
            userRole = new UserRole(userId, roleId);
            UserRole userRoleDB = (UserRole) userRoleDao.findUserRoleByIds(
                    userId, roleId);
            // 如果DB不存在，就添加
            if (null == userRoleDB) {
                rtnUserRole = userRoleDao.saveUserRole(userRole);
            }
        }

        return rtnUserRole;
    }

    /**
     * 根据用户信息和角色信息删除用户角色
     * 
     * @param user
     *            用户信息
     * @param role
     *            角色信息
     */
    private void removeUserRole(User user, Role role) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("删除用户角色");
        }
        User userDB = (User) userDao.loadUser(user.getUserId());
        Role roleDB = (Role) roleDao.loadRole(role.getRoleId());
        if (null != userDB && null != roleDB) {
            Integer userId = userDB.getUserId();
            Integer roleId = roleDB.getRoleId();
            UserRole userRoleDB = (UserRole) userRoleDao.findUserRoleByIds(
                    userId, roleId);
            // 如果DB存在，就删除
            if (null != userRoleDB) {
                userRoleDao.removeUserRole(userRoleDB);
            }
        }
    }

    /**
     * 根据用户ID和已存在的角色ID更新用户的角色信息。 先删除已有的，后增加最新的
     * 
     * @param userId
     *            角色ID
     * @param myRoleIds
     *            已有的角色ID列表字符串
     */
    public void updateUserRole(Integer userId, String myRoleIds) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("更新用户角色");
        }
        // 1. 根据roleId获取DB中的roleId列表，然后删除；
        List<UserRole> dbUserRoles = userRoleDao.findUserRolesByUserId(userId);
        for (UserRole userRole : dbUserRoles) {
            removeUserRole(new User(userId), new Role(userRole.getRoleId()));
        }

        // 2. 取得需要新增的roleId列表；
        List<Integer> myRoleIdList = StringUtil.stringToIntegerList(myRoleIds,
                Constants.REGEX);
        for (Integer roleId : myRoleIdList) {
            saveUserRole(new User(userId), new Role(roleId));
        }
    }

}