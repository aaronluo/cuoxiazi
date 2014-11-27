package com.innovaee.eorder.module.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class UserServiceTest extends BaseSpringTestCase {
	@Autowired
	private UserService userService;

	@Test
	public void saveUser() {
		String userName = "aaa";
		String password = "aaa";
		User user = new User();
		user.setUserName(userName);
		user.setUserEmail("aaa@qq.com");
		// String src = "test{test}";
		String md5Password = Md5Util.getMD5Str(password + "{" + userName + "}");
		user.setUserPassword(md5Password);
		userService.saveUser(user);
	}

	@Test
	public void getUserByPassword() {
		String userName = "abcd";
		String password = "1234";
		// String src = "test{test}";
		String md5Password = Md5Util.getMD5Str(password + "{" + userName + "}");
		User user = userService.getUserByPassword(userName, md5Password);
		Assert.assertNotNull(user);
		Assert.assertEquals("aaa@qq.com", user.getUserEmail());
	}

}