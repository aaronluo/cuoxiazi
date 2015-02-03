package com.innovaee.eorder.resources;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @Title: AllTests
 * @Description: 单元测试包
 * @version V1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ CategoryResourceTest.class, DishResourceTest.class,
        OrderResourceTest.class, UserResourceTest.class })
public class AllTests {

}