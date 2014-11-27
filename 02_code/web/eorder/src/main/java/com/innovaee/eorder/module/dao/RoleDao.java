package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.Role;

public class RoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Role.class;
	}

	@SuppressWarnings("unchecked")
	public Role findRolesByRoleName(String roleName) {
		List<Role> list = (List<Role>) super.getHibernateTemplate().find("FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		return (List<Role>) super.getHibernateTemplate().find("FROM Role");
	}

	public void saveRole(Role role) {
		save(role);
	}

	public void updateRole(Role role) {
		update(role);
	}

	public void removeRole(Role role) {
		super.getHibernateTemplate().delete(role);
	}
}