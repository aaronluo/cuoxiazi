package com.innovaee.eorder.module.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FunctionServiceTest.class, RoleFunctionServiceTest.class,
		RoleServiceTest.class, UserRoleServiceTest.class, UserServiceTest.class })
public class AllTests {

}