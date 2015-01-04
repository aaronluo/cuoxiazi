/***********************************************
 * Filename        : RoleFunctionDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.innovaee.eorder.module.entity.RoleFunction;

/**
 * @Title: RoleFunctionDao
 * @Description: 角色功能数据访问对象
 *
 * @version V1.0
 */
public class RoleFunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return RoleFunction.class;
	}

	/**
	 * 保存角色功能
	 * 
	 * @param roleFunction
	 *            待保存的角色功能
	 * @return 被保存的角色功能
	 */
	public RoleFunction saveRoleFunction(RoleFunction roleFunction) {
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		roleFunction.setCreateAt(createAt);
		return (RoleFunction) save(roleFunction);
	}

	/**
	 * 移除角色功能
	 * 
	 * @param roleFunction
	 *            待移除的角色功能
	 */
	public void removeRoleFunction(RoleFunction roleFunction) {
		remove(roleFunction.getPK());
	}

	/**
	 * 通过角色ID查找角色功能
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate().find(
						"FROM RoleFunction rf WHERE rf.roleId=?", roleId);
		return list;
	}

	/**
	 * 通过功能ID查找角色功能
	 * 
	 * @param functionId
	 *            功能ID
	 * @return 角色功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByFunctionId(Integer functionId) {
		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate().find(
						"FROM RoleFunction rf WHERE rf.functionId=?",
						functionId);
		return list;
	}

	/**
	 * 通过角色ID和功能ID查找角色功能
	 * 
	 * @param roleId
	 *            角色ID
	 * @param functionId
	 *            功能ID
	 * @return 角色功能
	 */
	@SuppressWarnings("unchecked")
	public RoleFunction findRoleFunctionByIds(Integer roleId, Integer functionId) {
		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate()
				.find("FROM RoleFunction rf WHERE rf.roleId=? and rf.functionId=?",
						roleId, functionId);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过角色ID和父功能ID查找角色功能
	 * 
	 * @param roleId
	 *            角色ID
	 * @param parentFunctionId
	 *            父功能ID
	 * @return 角色功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<RoleFunction> findParentRoleFunctionByIds(Integer roleId,
			Integer parentFunctionId) {

		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate()
				.find("FROM RoleFunction rf WHERE rf.roleId=? and rf.functionId=?",
						roleId, parentFunctionId);
		return list;
	}

	/**
	 * 通过父功能ID列表查找角色功能
	 * 
	 * @param parentFunctionIdList
	 *            父功能ID列表
	 * @return 角色功能列表
	 */
	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByFunctionIds(
			final List<Integer> parentFunctionIdList) {

		return getHibernateTemplate().execute(
				new HibernateCallback<List<RoleFunction>>() {
					public List<RoleFunction> doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from RoleFunction rf where rf.functionId in(:typeids)";
						Query query = session.createQuery(hql);
						query.setParameterList("typeids", parentFunctionIdList);
						List<RoleFunction> list = (List<RoleFunction>) query
								.list();

						return list;
					}
				});
	}
}