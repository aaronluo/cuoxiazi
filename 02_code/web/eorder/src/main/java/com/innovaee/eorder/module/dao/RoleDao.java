/***********************************************
 * Filename		: RoleDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.Role;

/**
 * @Title: RoleDao
 * @Description: 角色数据访问对象
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class RoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Role.class;
	}

	/**
	 * 根据角色ID查找角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色
	 */
	public Role loadRole(Integer roleId) {
		return (Role) get(roleId);
	}

	/**
	 * 根据角色名称查找角色
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色
	 */
	@SuppressWarnings("unchecked")
	public Role findRolesByRoleName(String roleName) {
		List<Role> list = (List<Role>) super.getHibernateTemplate().find(
				"FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据角色名称查找角色
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色
	 */
	@SuppressWarnings("unchecked")
	public Role findRoleByRoleName(String roleName) {
		List<Role> list = (List<Role>) super.getHibernateTemplate().find(
				"FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查找所有角色
	 * 
	 * @return 角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findAllRoles() {
		return (List<Role>) super.getHibernateTemplate().find("FROM Role");
	}

	/**
	 * @param role
	 * @return
	 */
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