package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;

public class UserRoleService extends BaseService {

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private UserDao userDao;

	public List<UserRole> findAllUserRoles() {
		return (List<UserRole>) userRoleDao.findAllUserRoles();
	}

	public UserRole findUserRoleByUserRole(String userRole) {
		return (UserRole) userRoleDao.findUserRoleByUserRole(userRole);
	}

	public List<UserRole> findUserRolesByRoleName(String roleName) {
		return userRoleDao.findUserRolesByRoleName(roleName);
	}

	public List<UserRole> findUserRolesByUsername(String userName) {
		return userRoleDao.findUserRolesByUsername(userName);
	}

	public void saveUserRole(UserRole userRole) {
		userRoleDao.saveUserRole(userRole);
	}

	public void updateUserRole(UserRole userRole) {
		userRoleDao.updateUserRole(userRole);
	}

	public void removeUserRole(String userRoleName) {
		userRoleDao.removeUserRole(new UserRole(userRoleName));
	}

	public void removeUserRoles(String[] userRoleName) {
		int length = userRoleName.length;
		for (int i = 0; i < length; i++) {
			userRoleDao.removeUserRole(new UserRole(userRoleName[i]));
		}

	}

	public UserRole saveUserRole(Role role, User user) {
		UserRole rtnUserRole = null;
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		User userDB = (User) userDao.get(user.getUserName());
		UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			String roleUser = roleDB.getRoleName() + userDB.getUserName();
			String roleName = roleDB.getRoleName();
			String userName = userDB.getUserName();
			userRole = new UserRole(roleUser, userName, roleName);
			UserRole userRoleDB = (UserRole) userRoleDao.get(roleUser);
			// 如果DB不存在，就添加
			if (null == userRoleDB) {
				rtnUserRole = userRoleDao.saveUserRole(userRole);
			}
		}

		return rtnUserRole;

	}

	public void removeUserRole(Role role, User user) {
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		User userDB = (User) userDao.get(user.getUserName());
		// UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			String roleUser = roleDB.getRoleName() + userDB.getUserName();
			// String roleName = roleDB.getRoleName();
			// String userName = userDB.getUserName();
			// userRole = new UserRole(roleUser, roleName, userName);
			UserRole userRoleDB = (UserRole) userRoleDao.get(roleUser);
			// 如果DB不存在，就添加
			if (null != userRoleDB) {
				userRoleDao.removeUserRole(userRoleDB);
			}
		}

	}
}