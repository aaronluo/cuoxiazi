package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.entity.Role;

public class RoleService extends BaseService {

	@Resource
	private RoleDao roleDao;

	public List<Role> findAllRoles() {
		return (List<Role>) roleDao.findAllRoles();
	}

	public Role get(String roleId) {
		return (Role) roleDao.get(roleId);
	}

	public Role findRolesByRoleName(String roleName) {
		return (Role) roleDao.findRolesByRoleName(roleName);
	}

	public void saveRole(Role role) {
		roleDao.saveRole(role);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void removeRole(String roleName) {
		roleDao.removeRole(new Role(roleName));
	}

	public void removeRoles(String[] roleName) {
		int length = roleName.length;
		for (int i = 0; i < length; i++) {
			roleDao.removeRole(new Role(roleName[i]));
		}

	}
}