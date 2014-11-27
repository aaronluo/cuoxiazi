package com.innovaee.eorder.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:datasource.xml",
    "classpath:applicationContext.xml",
    "classpath:applicationContext-security.xml",
    "classpath:applicationContext-dao.xml",
    "classpath:applicationContext-service.xml",
    "classpath:applicationContext-utils.xml"    
})
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
//@Transactional(rollbackFor = { Throwable.class })
public class BaseSpringTestCase{
	@Test
	public void test2() {
		Assert.assertEquals(true, true);
	}
}