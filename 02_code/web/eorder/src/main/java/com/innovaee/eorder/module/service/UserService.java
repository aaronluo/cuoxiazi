/***********************************************
 * Filename        : UserService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
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
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.vo.UserVO;

/**
 * @Title: UserService
 * @Description: 用户服务
 *
 * @version V1.0
 */
public class UserService extends BaseService {

	/** 用户数据访问对象 */
	@Resource
	private UserDao userDao;

	/** 用户等级数据访问对象 */
	@Resource
	private UserLevelDao userLevelDao;

	/** 用户角色数据访问对象 */
	@Resource
	private UserRoleDao userRoleDao;

	/** 角色数据访问对象 */
	@Resource
	private RoleDao roleDao;

	/**
	 * 返回所有用户值对象列表
	 * 
	 * @return 用户值对象列表
	 */
	public List<UserVO> findAllUserVOs() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("查找所有用户");
		}
		List<UserVO> uservos = new ArrayList<UserVO>();

		UserLevel userLevel = null;
		List<UserRole> userRoles = null;
		List<String> roleNames = null;
		Role role = null;

		// 查找所有用户
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
	 * 根据用户ID查找用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户
	 */
	public User loadUser(Integer userId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("新增用户");
		}
		return (User) userDao.loadUser(userId);
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
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("通过用户名和密码查找用户");
		}
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
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("通过用户名查找用户");
		}
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
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("通过手机号码查找用户");
		}
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
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("保存用户");
		}
		// 加密用户名密码
		String md5Password = Md5Util.getMD5Str(user.getPassword() + "{"
				+ user.getUsername() + "}");
		user.setPassword(md5Password);
		return userDao.saveUser(user);
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            待更新的用户信息
	 */
	public void updateUser(User user) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("更新用户");
		}
		userDao.updateUser(user);
	}

	/**
	 * 根据用户ID移除用户信息（先删除角色，在删除用户）
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void removeUser(Integer userId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("根据用户ID移除用户");
		}
		// 1. 先删除用户与角色的关联
		List<UserRole> userRoles = userRoleDao.findUserRolesByUserId(userId);
		for (UserRole userRole : userRoles) {
			userRoleDao.remove(userRole.getUserRoleId());
		}
		// 2. 再删除用户
		userDao.removeUser(new User(userId));
	}

}