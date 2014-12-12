package com.innovaee.eorder.module.service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-security.xml",
		"classpath:applicationContext-total.xml", })
// @TransactionConfiguration(transactionManager = "txManager", defaultRollback =
// true)
// @Transactional(rollbackFor = { Throwable.class })
public class BaseSpringTestCase {
	public static Timestamp createAt = Timestamp
			.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
					.format(Calendar.getInstance().getTime()));

	@Test
	public void test2() {
		Assert.assertEquals(true, true);
	}
}