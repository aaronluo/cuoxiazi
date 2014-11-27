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

	@Test
	public void getAllRoles() {
		List<Role> allRoles = roleService.findAllRoles();
		Assert.assertNotNull(allRoles);
		for (Role role : allRoles) {
			System.out.println(role);
		}
	}

	@Test
	public void findRolesByRoleName() {
		String roleName = "doSystemSettings";
		Role role = roleService.findRolesByRoleName(roleName);
		Assert.assertNotNull(role);
	}

	@Test
	public void saveRole() {
		Role role = new Role("abcd", "abcd", false);
		roleService.saveRole(role);
	}

	@Test
	public void updateRole() {
		Role role = new Role("abcd", "abcd", false);
		roleService.updateRole(role);
	}

}