/***********************************************
 * Filename        : UserService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.vo.UserVO;

/**
 * @Title: UserService
 * @Description: 用户服务接口
 *
 * @version V1.0
 */
public interface UserService {

    /**
     * 返回所有用户值对象列表
     * 
     * @return 用户值对象列表
     */
    public List<UserVO> findAllUserVOs();

    /**
     * 根据用户ID查找用户信息
     * 
     * @param userId
     *            用户ID
     * @return 用户
     */
    public User loadUser(Long userId);

    /**
     * 通过用户名和密码查找用户
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 用户
     */
    public User findUserByUsernameAndPassword(String username, String password);

    /**
     * 通过用户名查找用户
     * 
     * @param username
     *            用户名
     * @return 用户
     */
    public User findUserByUserName(String username);

    /**
     * 通过手机号码查找用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User findUserByCellphone(String cellphone);

    /**
     * 保存用户
     * 
     * @param user
     *            用户信息
     * @return 被保存的用户
     */
    public Long saveUser(User user);

    /**
     * 更新用户
     * 
     * @param user
     *            待更新的用户信息
     */
    public void updateUser(User user);

    /**
     * 根据用户ID移除用户信息（先删除用户与角色的关联，再删除用户）
     * 
     * @param userId
     *            用户ID
     */
    public void deleteUser(Long userId);

    /**
     * 通过角色ID查找剩余的角色
     * 
     * @param roleId
     *            角色ID
     * @return 角色列表
     */
    public List<Role> findLeftRolesByUserId(Long userId);

    /**
     * 获得分页信息
     * 
     * @param startRow
     *            记录开始位置
     * @param pageSize
     *            分页大小
     * @return
     */
    public List<User> getUsersByPage(int startRow, int pageSize);
    
    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count();
}