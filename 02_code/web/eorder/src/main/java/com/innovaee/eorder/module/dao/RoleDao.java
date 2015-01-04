/***********************************************
 * Filename        : RoleDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.dao;

import com.innovaee.eorder.module.entity.Role;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Title: RoleDao
 * @Description: 角色数据访问对象
 *
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
	public Role loadRole(final Integer roleId) {
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
	public Role findRoleByRoleName(final String roleName) {
		final List<Role> list = (List<Role>) super.getHibernateTemplate().find(
				"FROM Role f WHERE f.roleName=?", roleName);
		if (null != list && list.size() > 0) {
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
	 * 保存角色
	 * 
	 * @param role
	 *            待保存的角色
	 * @return 已保存的角色
	 */
	public Role saveRole(final Role role) {
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		role.setCreateAt(createAt);
		return (Role) save(role);
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            待更新的角色
	 */
	public void updateRole(Role role) {
		final Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		role.setUpdateAt(updateAt);
		update(role);
	}

	/**
	 * 移除角色
	 * 
	 * @param role
	 *            待移除的角色
	 */
	public void removeRole(final Role role) {
		super.getHibernateTemplate().delete(role);
	}
}