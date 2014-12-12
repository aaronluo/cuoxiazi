package com.innovaee.eorder.module.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.StringUtil;

public class UserRoleService extends BaseService {

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private UserDao userDao;

	@Resource
	private UserRoleService userRoleService;

	public List<UserRole> findAllUserRoles() {
		return (List<UserRole>) userRoleDao.findAllUserRoles();
	}

	public UserRole findUserRoleByIds(Integer userId, Integer roleId) {
		return userRoleDao.findUserRoleByIds(userId, roleId);
	}

	public List<UserRole> findUserRolesByUserId(Integer userId) {
		return (List<UserRole>) userRoleDao.findUserRolesByUserId(userId);
	}
	

	public List<UserRole> findUserRolesByRoleId(Integer roleId) {
		return (List<UserRole>) userRoleDao.findUserRolesByRoleId(roleId);
	}

	public List<Role> findRolesByUserId(Integer userId) {
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
	 * @param roleId
	 * @return
	 */
	public List<Role> findLeftRolesByUserId(Integer roleId) {
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

	public void saveUserRole(UserRole userRole) {
		userRoleDao.saveUserRole(userRole);
	}

	public UserRole saveUserRole(User user, Role role) {
		UserRole rtnUserRole = null;
		User userDB = (User) userDao.get(user.getUserId());
		Role roleDB = (Role) roleDao.get(role.getRoleId());
		UserRole userRole = null;
		if (null != userDB && null != roleDB) {
			Integer userId = userDB.getUserId();
			Integer roleId = roleDB.getRoleId();
			Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
					.getTime()));
			userRole = new UserRole(userId, roleId, createAt);
			UserRole userRoleDB = (UserRole) userRoleDao.findUserRoleByIds(
					userId, roleId);
			// 如果DB不存在，就添加
			if (null == userRoleDB) {
				rtnUserRole = userRoleDao.saveUserRole(userRole);
			}
		}

		return rtnUserRole;
	}

	public void removeUserRole(User user, Role role) {
		User userDB = (User) userDao.get(user.getUserId());
		Role roleDB = (Role) roleDao.get(role.getRoleId());
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
	 * 先删除已有的，后增加最新的
	 * 
	 * @param roleId
	 * @param myRoles
	 */
	public void updateUserRole(Integer userId, String myRoleIds) {
		// 1. 根据roleId获取DB中的roleId列表，然后删除；
		List<UserRole> dbUserRoles = userRoleDao.findUserRolesByUserId(userId);
		for (UserRole userRole : dbUserRoles) {
			// dbRoleIds.add(userRole.getRoleId());
			removeUserRole(new User(userId), new Role(userRole.getRoleId()));
		}

		// 2. 取得需要新增的roleId列表；
		List<Integer> myRoleIdList = StringUtil.stringToIntegerList(myRoleIds,
				Constants.REGEX);
		for (Integer roleId : myRoleIdList) {
			saveUserRole(new User(userId), new Role(roleId));
		}
	}

	public void removeUserRole(Integer userRoleId) {
		userRoleDao.removeUserRole(new UserRole(userRoleId));
	}

	public void removeUserRoles(Integer[] userRoleIds) {
		int length = userRoleIds.length;
		for (int i = 0; i < length; i++) {
			userRoleDao.removeUserRole(new UserRole(userRoleIds[i]));
		}
	}
}