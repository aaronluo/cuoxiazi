package com.innovaee.eorder.module.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-security.xml",
		"classpath:applicationContext-total.xml", })
public class BaseSpringTestCase {

	/** 日志对象 */
	protected static final Logger LOGGER = Logger
			.getLogger(BaseSpringTestCase.class);

	@Test
	public void test2() {
		Assert.assertEquals(true, true);
	}
}