package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class UserRoleServiceTest extends BaseSpringTestCase {

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Test
	public void getAllUserRoles() {
		List<UserRole> allUserRoles = userRoleService.findAllUserRoles();
		Assert.assertNotNull(allUserRoles);
		for (UserRole userRole : allUserRoles) {
			System.out.println(userRole);
		}
	}

	@Test
	public void findUserRoleByUserRole() {
		String userRole = "adminROLE_admin";
		UserRole userRoleDB = userRoleService.findUserRoleByUserRole(userRole);
		Assert.assertNotNull(userRoleDB);
		Assert.assertEquals("admin", userRoleDB.getUserName());
		Assert.assertEquals("ROLE_admin", userRoleDB.getRoleName());
	}

	@Test
	public void findUserRoleByRoleName() {
		String roleName = "ROLE_admin";
		List<UserRole> userRoles = userRoleService.findUserRolesByRoleName(roleName);
		Assert.assertNotNull(userRoles);
		for (UserRole userRole : userRoles) {
			System.out.println(userRole);
		}
	}

	@Test
	public void findUserRolesByUsername() {
		String userName = "admin";
		List<UserRole> userRoles = userRoleService.findUserRolesByUsername(userName);
		Assert.assertNotNull(userRoles);
		for (UserRole userRole : userRoles) {
			// Assert.assertEquals("admin", userRole.getRoleName());
			System.out.println(userRole);
		}
	}

	@Test
	public void saveUserRole() {
		String roleName = "ROLE_normal";

		Role role = new Role(roleName);

		String userName = "user01";
		User user = new User(userName);
		String password = "1234";
		String md5Password = Md5Util.getMD5Str(password + "{" + userName + "}");
		user.setUserPassword(md5Password);
		User userDB = userService.saveUser(user);
		Assert.assertNotNull(userDB);

		UserRole userRole = userRoleService.saveUserRole(role, user);
		Assert.assertNotNull(userRole);
		Assert.assertEquals(roleName + userName, userRole.getUserRoleName());
		Assert.assertEquals(userName, userRole.getUserName());
		Assert.assertEquals(roleName, userRole.getRoleName());
	}

	@Test
	public void removeUserRole() {
		String roleName = "ROLE_normal";
		String userName = "user01";
		Role role = new Role(roleName);
		User user = new User(userName);
		userRoleService.removeUserRole(role, user);
		String userRole = roleName + userName;
		UserRole userRoleDB = userRoleService.findUserRoleByUserRole(userRole);
		Assert.assertNull(userRoleDB);
		userService.removeUser(user);
		User userDB = userService.findUsersByUserName(userName);
		Assert.assertNull(userDB);
	}

}