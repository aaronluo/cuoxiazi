package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class UserRoleServiceTest extends BaseSpringTestCase {

	@Autowired
	private UserRoleService userRoleService;

	private Integer userId = 2;
	private Integer roleId = 1;

	@Test
	public void getAllUserRoles() {
		List<UserRole> allUserRoles = userRoleService.findAllUserRoles();
		Assert.assertNotNull(allUserRoles);
		for (UserRole userRole : allUserRoles) {
			System.out.println(userRole);
		}
	}

	@Test
	public void findUserRoleByUserId_01() {
		Integer userId = 1;
		List<UserRole> userRoles = userRoleService
				.findUserRolesByUserId(userId);
		Assert.assertNotNull(userRoles);
		for (UserRole userRole : userRoles) {
			System.out.println(userRole);
		}
	}
	
	@Test
	public void findUserRoleByUserId_02() {
		Integer userId = 2;
		List<UserRole> userRoles = userRoleService
				.findUserRolesByUserId(userId);
		Assert.assertNotNull(userRoles);
		for (UserRole userRole : userRoles) {
			System.out.println(userRole);
		}
	}


	@Test
	public void findUserRoleByIds() {
		Integer userId = 1;
		Integer roleId = 1;
		UserRole userRole = userRoleService.findUserRoleByIds(userId, roleId);
		Assert.assertNotNull(userRole);
		Assert.assertEquals(roleId, userRole.getRoleId());
		Assert.assertEquals(userId, userRole.getUserId());
	}

	@Test
	public void saveUserRole() {
		User user = new User(userId);
		Role role = new Role(roleId);
		UserRole newUserRole = userRoleService.saveUserRole(user, role);
		Assert.assertNotNull(newUserRole);
		Assert.assertEquals(roleId, newUserRole.getRoleId());
		Assert.assertEquals(userId, newUserRole.getUserId());
	}

	@Test
	public void removeUserRole() {
		Role role = new Role(roleId);
		User user = new User(userId);
		userRoleService.removeUserRole(user, role);

		UserRole userRoleDB = userRoleService.findUserRoleByIds(roleId, userId);
		Assert.assertNull(userRoleDB);
	}

	@Test
	public void saveAndRemoveUserRole() {
		User user = new User(userId);
		Role role = new Role(roleId);
		UserRole newUserRole = userRoleService.saveUserRole(user, role);
		Assert.assertNotNull(newUserRole);
		Assert.assertEquals(roleId, newUserRole.getRoleId());
		Assert.assertEquals(userId, newUserRole.getUserId());

		userRoleService.removeUserRole(user, role);
		UserRole userRoleDB = userRoleService.findUserRoleByIds(roleId, userId);
		Assert.assertNull(userRoleDB);
	}

	@Test
	public void updateUserRole() {
		String myRoleIds = "1,2";
		userRoleService.updateUserRole(userId, myRoleIds);

		List<UserRole> userRoles = userRoleService
				.findUserRolesByUserId(userId);
		Assert.assertNotNull(userRoles);
		Assert.assertEquals(2, userRoles.size());
	}

	@Test
	public void findLeftRolesByUserId() {
		List<Role> roles = userRoleService.findLeftRolesByUserId(userId);
		Assert.assertNotNull(roles);
		Assert.assertEquals(1, roles.size());
	}

}