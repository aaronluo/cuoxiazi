/***********************************************
 * Filename		: UserService.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserLevelDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserLevel;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.vo.UserVO;

/**
 * @Title: UserService
 * @Description: 用户服务
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class UserService extends BaseService {

	@Resource
	private UserDao userDao;

	@Resource
	private UserLevelDao userLevelDao;

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleDao roleDao;

	/**
	 * 根据用户ID查找用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户
	 */
	public User loadUser(Integer userId) {
		return (User) userDao.get(userId);
	}

	/**
	 * 返回所有用户值对象列表
	 * 
	 * @return 用户值对象列表
	 */
	public List<UserVO> findAllUserVOs() {
		List<UserVO> uservos = new ArrayList<UserVO>();

		UserLevel userLevel = null;
		List<UserRole> userRoles = null;
		List<String> roleNames = null;
		// UserRole userRole = null;
		Role role = null;

		List<User> users = userDao.findAllUsers();
		for (User user : users) {
			UserVO userVO = new UserVO();
			BeanUtils.copyProperties(user, userVO);

			// 设置用户等级名称
			userLevel = userLevelDao.loadUserLevel(user.getLevelId());
			if (null != userLevel) {
				userVO.setLevelName(userLevel.getLevelName());
				userRoles = userRoleDao.findUserRolesByUserId(user.getUserId());
				roleNames = new ArrayList<String>();
				for (UserRole userRole : userRoles) {
					role = roleDao.loadRole(userRole.getRoleId());
					roleNames.add(role.getRoleName());
				}
			}
			userVO.setRoleName(roleNames.toString());

			uservos.add(userVO);
		}

		return uservos;
	}

	/**
	 * 返回所有用户
	 * 
	 * @return 用户列表
	 */
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	/**
	 * 通过用户名密码查找用户
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 用户
	 */
	public User getUserByPassword(String loginId, String password) {
		return userDao.getUserByPassword(loginId, password);
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
	public User findUsersByCellphone(String cellphone) {
		return (User) userDao.findUserByCellphone(cellphone);
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 *            用户信息
	 * @return 被保存的用户
	 */
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            待更新的用户信息
	 */
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	/**
	 * 移除用户
	 * 
	 * @param user
	 *            待移除的用户信息
	 */
	public void removeUser(User user) {
		userDao.removeUser(user);
	}

	/**
	 * 根据用户ID移除用户信息（先删除角色，在删除用户）
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void removeUser(Integer userId) {
		// 1. 先删除角色
		List<UserRole> userRoles = userRoleDao.findUserRolesByUserId(userId);
		for (UserRole userRole : userRoles) {
			// role = roleDao.loadRole(userRole.getRoleId());
			// roleNames.add(role.getRoleName());
			userRoleDao.remove(userRole.getUserRoleId());
		}
		// 2. 再删除用户
		userDao.removeUser(new User(userId));
	}

}