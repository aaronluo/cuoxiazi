package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.UserRole;

public class UserRoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return UserRole.class;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findAllUserRoles() {
		return (List<UserRole>) super.getHibernateTemplate().find("FROM UserRole");
	}

	public UserRole saveUserRole(UserRole userRole) {
		return (UserRole) save(userRole);
	}

	public void updateUserRole(UserRole userRole) {
		update(userRole);
	}

	public void removeUserRole(UserRole userRole) {
		super.getHibernateTemplate().delete(userRole);
	}

	@SuppressWarnings("unchecked")
	public UserRole findUserRoleByUserRole(String userRole) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate().find("FROM UserRole f WHERE f.userRole=?", userRole);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRolesByRoleName(String roleName) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate().find("FROM UserRole f WHERE f.roleName=?", roleName);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRolesByUsername(String username) {
		UserRole userRole = new UserRole();
		userRole.setUserName(username);
		return (List<UserRole>) super.getHibernateTemplate().findByExample(userRole);
	}

}
