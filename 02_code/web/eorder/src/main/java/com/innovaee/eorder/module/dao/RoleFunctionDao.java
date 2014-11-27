package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.RoleFunction;

public class RoleFunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return RoleFunction.class;
	}

	@SuppressWarnings("unchecked")
	public List<RoleFunction> findAllRoleFunctions() {
		return (List<RoleFunction>) super.getHibernateTemplate().find("FROM RoleFunction");
	}

	public RoleFunction saveRoleFunction(RoleFunction roleFunction) {
		return (RoleFunction)save(roleFunction);
	}

	public void updateRoleFunction(RoleFunction roleFunction) {
		update(roleFunction);
	}

	public void removeRoleFunction(RoleFunction roleFunction) {
		super.getHibernateTemplate().delete(roleFunction);
	}

	@SuppressWarnings("unchecked")
	public RoleFunction findRoleFunctionByRoleFunctionName(String roleFunctionName) {
		List<RoleFunction> list = (List<RoleFunction>) super.getHibernateTemplate().find("FROM RoleFunction f WHERE f.roleFunctionName=?", roleFunctionName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByRoleName(String roleName) {
		List<RoleFunction> list = (List<RoleFunction>) super.getHibernateTemplate().find("FROM RoleFunction f WHERE f.roleName=?", roleName);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByFunctionName(String functionName) {
		List<RoleFunction> list = (List<RoleFunction>) super.getHibernateTemplate().find("FROM RoleFunction f WHERE f.functionName=?", functionName);
		return list;
	}
}