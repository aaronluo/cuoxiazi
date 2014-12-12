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

public class UserService extends BaseService {

	@Resource
	private UserDao userDao;

	@Resource
	private UserLevelDao userLevelDao;

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleDao roleDao;

	public User loadUser(Integer userId) {
		return (User) userDao.get(userId);
	}

	/**
	 * 返回所有用户
	 * 
	 * @return
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
	 * @return
	 */
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	public User getUserByPassword(String loginId, String password) {
		return userDao.getUserByPassword(loginId, password);
	}

	public User findUsersByUserName(String username) {
		return (User) userDao.findUsersByUserName(username);
	}

	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void removeUser(User user) {
		userDao.removeUser(user);
	}

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

	public void removeUsers(String[] userIds) {
		int length = userIds.length;
		for (int i = 0; i < length; i++) {
			removeUser(new User(Integer.parseInt(userIds[i])));
		}
	}

}