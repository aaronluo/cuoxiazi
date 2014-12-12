package com.innovaee.eorder.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.dao.impl.UserDaoImpl;

public class UserDaoTest {

	private UserDao userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDao = new UserDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getUserByCellphone_01() {
		String cellphone = "13912345673";
		User user = userDao.getUserByCellphone(cellphone);
		Assert.assertNotNull(user);
		Assert.assertEquals("abc", user.getUserName());
	}
	
	@Test
	public void getUserByCellphone_02() {
		String cellphone = "13912340004";
		User user = userDao.getUserByCellphone(cellphone);
		Assert.assertNotNull(user);
		Assert.assertEquals("赵六", user.getUserName());
	}
}