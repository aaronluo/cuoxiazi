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
		return (List<UserRole>) super.getHibernateTemplate().find(
				"FROM UserRole");
	}

	public UserRole loadUserRole(Integer userRoleId) {
		return (UserRole) get(userRoleId);
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
	public List<UserRole> findUserRolesByRoleId(Integer roleId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole f WHERE f.roleId=?", roleId);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRolesByUserId(Integer userId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole ur WHERE ur.userId=?", userId);
		return list;
	}

	@SuppressWarnings("unchecked")
	public UserRole findUserRoleByIds(Integer userId, Integer roleId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole ur WHERE ur.userId=? and ur.roleId=?",
						userId, roleId);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

}