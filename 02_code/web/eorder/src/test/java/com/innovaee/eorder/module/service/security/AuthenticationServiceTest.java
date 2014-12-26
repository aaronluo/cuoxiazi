package com.innovaee.eorder.module.service.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class AuthenticationServiceTest extends BaseSpringTestCase {

	@Autowired
	private AuthenticationService authenticationService;

	@Test
	public void getUserInfo() {
		String username = "admin";
		User user = authenticationService.getUserInfo(username);
		System.out.println(user.getCellphone());
	}

	@Test
	public void checkUserPassword() {
		String username = "admin";
		String src = "admin{admin}";
		String password = Md5Util.getMD5Str(src);

		boolean result = authenticationService.checkUserPassword(username,
				password);
		Assert.assertEquals(true, result);
	}

	@Test
	public void loadUserByUsername_01() {
		String username = "admin";

		UserDetailsVo userDetails = (UserDetailsVo) authenticationService
				.loadUserByUsername(username);
		Assert.assertNotNull(userDetails);

		List<UserFunctionVo> userFunctions = userDetails.getUserFunctions();
		User user = null;
		Role role = null;
		Function function = null;
		for (UserFunctionVo userFunctionVo : userFunctions) {
			user = userFunctionVo.getUser();
			System.out.println(user);
			role = userFunctionVo.getRole();
			System.out.println("### " + role.getRoleName());
			function = userFunctionVo.getFunction();
			System.out.println(function);
		}

		Collection<GrantedAuthority> c = userDetails.getAuthorities();
		Iterator<GrantedAuthority> iterator = c.iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = iterator.next();
			String rolename = grantedAuthority.getAuthority();
			System.out.println(rolename);
		}

	}

	@Test
	public void loadUserByUsername_02() {
		String username = "test";

		UserDetailsVo userDetails = (UserDetailsVo) authenticationService
				.loadUserByUsername(username);
		Assert.assertNotNull(userDetails);

		Collection<GrantedAuthority> c = userDetails.getAuthorities();
		Iterator<GrantedAuthority> iterator = c.iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = iterator.next();
			String rolename = grantedAuthority.getAuthority();
			System.out.println(rolename);
		}

	}
}