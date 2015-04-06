/***********************************************
 * Filename        : UserService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.dao.RoleDao;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.utils.Md5Util;
import com.innovaee.eorder.vo.UserVO;

/**
 * @Title: UserService
 * @Description: 用户服务
 *
 * @version V1.0
 */
public class UserServiceImpl implements UserService {

    /** 用户数据访问对象 */
    @Resource
    private UserDao userDao;

    /** 用户等级数据访问对象 */
    @Resource
    private UserLevelDao userLevelDao;

    /** 角色数据访问对象 */
    @Resource
    private RoleDao roleDao;

    /**
     * 返回所有用户值对象列表
     * 
     * @return 用户值对象列表
     */
    public List<UserVO> findAllUserVOs() {
        List<UserVO> uservos = new ArrayList<UserVO>();

        UserLevel userLevel = null;
        Set<Role> roles = null;
        List<String> roleNames = null;
        Role role = null;

        // 查找所有用户
        List<User> users = userDao.loadAll();
        for (User user : users) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);

            // 设置用户等级名称
//            userLevel = userLevelDao.get(user.getLevelId());
            userLevel = user.getMemberShip().getLevel();
            if (null != userLevel) {
                userVO.setLevelName(userLevel.getLevelName());
                roles = user.getRoles();
                roleNames = new ArrayList<String>();
                for (Role tempRole : roles) {
                    role = roleDao.get(tempRole.getId());
                    roleNames.add(role.getRoleName());
                }
            }
            userVO.setRoleName(roleNames.toString());

            uservos.add(userVO);
        }

        return uservos;
    }

    /**
     * 根据用户ID查找用户信息
     * 
     * @param userId
     *            用户ID
     * @return 用户
     */
    public User loadUser(Long userId) {
        return (User) userDao.get(userId);
    }

    /**
     * 通过用户名和密码查找用户
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 用户
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        // 加密用户名密码
        String md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
        return userDao.findUserByUsernameAndPassword(username, md5Password);
    }

    /**
     * 通过用户名查找用户
     * 
     * @param username
     *            用户名
     * @return 用户
     */
    public User findUserByUserName(String username) {
        return (User) userDao.findUserByUserName(username);
    }

    /**
     * 通过手机号码查找用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User findUserByCellphone(String cellphone) {
        return (User) userDao.findUserByCellphone(cellphone);
    }

    /**
     * 保存用户
     * 
     * @param user
     *            用户信息
     * @return 被保存的用户
     */
    public Long saveUser(User user) {
        return userDao.save(user); 
    }

    /**
     * 更新用户
     * 
     * @param user
     *            待更新的用户信息
     */
    public void updateUser(User user) {
        userDao.update(user);
    }

    /**
     * 根据用户ID移除用户信息（先删除用户与角色的关联，再删除用户）
     * 
     * @param userId
     *            用户ID
     */
    public void deleteUser(Long userId) {
        User user = userDao.get(userId);
        userDao.delete(user);
    }

    /**
     * 通过角色ID查找剩余的角色
     * 
     * @param roleId
     *            角色ID
     * @return 角色列表
     */
    public List<Role> findLeftRolesByUserId(Long roleId) {
        List<Role> leftRoles = new ArrayList<Role>();
        List<Role> roles = new ArrayList<Role>();
        User user = userDao.get(roleId);
        if (null != user) {
            Set<Role> roleSet = user.getRoles();
            for (Role role : roleSet) {
                roles.add(role);
            }
        }

        List<Role> allRoles = roleDao.loadAll();
        for (Role roleDB : allRoles) {
            leftRoles.add(roleDB);
        }

        leftRoles.removeAll(roles);

        return leftRoles;
    }

    /**
     * 获得分页信息
     * 
     * @param startRow
     *            记录开始位置
     * @param pageSize
     *            分页大小
     * @return
     */
    public List<User> getUsersByPage(int startRow, int pageSize) {
        String hql = "FROM User as f ORDER BY f.id DESC";
        return userDao.getPage(startRow, pageSize, hql);
    }

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count() {
        return userDao.count();
    }
}