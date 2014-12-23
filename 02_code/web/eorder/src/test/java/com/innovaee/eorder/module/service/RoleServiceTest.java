package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class RoleServiceTest extends BaseSpringTestCase {

	@Autowired
	private RoleService roleService;

	private String roleName = "Test";
	private String roleDesc = "Test";
	private Boolean roleStatus = true;

	@Test
	public void getAllRoles() {
		List<Role> allRoles = roleService.findAllRoles();
		Assert.assertNotNull(allRoles);
		for (Role role : allRoles) {
			System.out.println(role);
		}
	}

	@Test
	public void loadRole() {
		Integer roleId = 2;
		Role role = roleService.loadRole(roleId);
		Assert.assertNotNull(role);
		Assert.assertEquals("Normal", role.getRoleName());
		System.out.println(role);
	}

	@Test
	public void findRolesByRoleName() {
		String roleName = "Admin";
		Role role = roleService.findRoleByRoleName(roleName);
		Assert.assertNotNull(role);
	}

	@Test
	public void saveRole() {
		Role role = new Role(roleName, roleDesc, roleStatus, createAt);
		Role roleNew = roleService.saveRole(role);

		// 检查
		Role roleDB = roleService.loadRole(roleNew.getRoleId());
		Assert.assertNotNull(roleDB);
		Assert.assertEquals("Test", roleDB.getRoleName());
	}

	@Test
	public void updateRole() {
		// 先新增一个对象
		Role role = new Role(roleName, roleDesc, roleStatus);
		Role roleNew = roleService.saveRole(role);

		// 得到新增后的ID
		Integer roleId = roleNew.getRoleId();

		// 更新属性
		String newRoleName = "Test2";
		String newRoleDesc = "Test2";
		roleNew.setRoleName(newRoleName);
		roleNew.setRoleDesc(newRoleDesc);
		roleService.updateRole(roleNew);

		// 检查
		Role roleDB = roleService.loadRole(roleId);
		Assert.assertNotNull(roleDB);
		Assert.assertEquals("Test2", roleDB.getRoleName());
	}

	@Test
	public void removeRole() {
		Role role = new Role(roleName, roleDesc, roleStatus);
		Role roleNew = roleService.saveRole(role);
		Integer roleId = roleNew.getRoleId();
		roleService.removeRole(roleId);
		// 检查
		Role roleDB = roleService.loadRole(roleId);
		Assert.assertNull(roleDB);
	}

	@Test
	public void removeRoles() {
		Role role1 = new Role(roleName, roleDesc, roleStatus);
		Role role2 = new Role(roleName, roleDesc, roleStatus);
		Role roleNew1 = roleService.saveRole(role1);
		Role roleNew2 = roleService.saveRole(role2);

		String[] roleIds = new String[] { roleNew1.getRoleId().toString(),
				roleNew2.getRoleId().toString() };
		roleService.removeRoles(roleIds);

		// 检查
		Role roleDB1 = roleService.loadRole(roleNew1.getRoleId());
		Assert.assertNull(roleDB1);
		Role roleDB2 = roleService.loadRole(roleNew2.getRoleId());
		Assert.assertNull(roleDB2);
	}

}