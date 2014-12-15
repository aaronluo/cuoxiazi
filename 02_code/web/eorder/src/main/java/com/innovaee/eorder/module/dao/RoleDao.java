package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.Role;

public class RoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Role.class;
	}

	public Role loadRole(Integer roleId) {
		return (Role) get(roleId);
	}

	@SuppressWarnings("unchecked")
	public Role findRolesByRoleName(String roleName) {
		List<Role> list = (List<Role>) super.getHibernateTemplate().find(
				"FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Role findRoleByRoleName(String roleName) {
		List<Role> list = (List<Role>) super.getHibernateTemplate().find(
				"FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		return (List<Role>) super.getHibernateTemplate().find("FROM Role");
	}
	
	public Role saveRole(Role role) {
		return (Role) save(role);
	}

	public void updateRole(Role role) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		role.setUpdateAt(updateAt);
		update(role);
	}

	public void removeRole(Role role) {
		super.getHibernateTemplate().delete(role);
	}
}