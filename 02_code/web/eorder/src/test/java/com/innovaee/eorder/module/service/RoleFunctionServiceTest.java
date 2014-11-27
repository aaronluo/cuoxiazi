package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class RoleFunctionServiceTest extends BaseSpringTestCase {

	@Autowired
	private RoleFunctionService roleFunctionService;

	@Test
	public void getAllRoleFunctions() {
		List<RoleFunction> allRoleFunctions = roleFunctionService.findAllRoleFunctions();
		Assert.assertNotNull(allRoleFunctions);
		for (RoleFunction roleFunction : allRoleFunctions) {
			System.out.println(roleFunction);
		}
	}

	@Test
	public void findRoleFunctionByRoleFunctionName() {
		// ROLE_admindoFunctionRole ROLE_admin doFunctionRole
		String roleFunctionName = "ROLE_admindoFunctionRole";
		RoleFunction roleFunction = roleFunctionService.findRoleFunctionByRoleFunctionName(roleFunctionName);
		Assert.assertNotNull(roleFunction);
		Assert.assertEquals("ROLE_admin", roleFunction.getRoleName());
		Assert.assertEquals("doFunctionRole", roleFunction.getFunctionName());
	}

	@Test
	public void findRoleFunctionByRoleName() {
		String roleName = "ROLE_admin";
		List<RoleFunction> roleFunctions = roleFunctionService.findRoleFunctionsByRoleName(roleName);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			System.out.println(roleFunction);
		}
	}

	@Test
	public void findRoleFunctionByFunctionName() {
		String functionName = "doFunctionRole";
		List<RoleFunction> roleFunctions = roleFunctionService.findRoleFunctionsByFunctionName(functionName);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			System.out.println(roleFunction);
		}
	}
	
	@Test
	public void findFunctionsByRoleName_01() {
		String roleName = "ROLE_admin";
		List<Function> functions = roleFunctionService.findFunctionsByRoleName(roleName);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			System.out.println(function);
		}
	}
	
	@Test
	public void findFunctionsByRoleName_02() {
		String roleName = "ROLE_normal";
		List<Function> functions = roleFunctionService.findFunctionsByRoleName(roleName);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			System.out.println(function);
		}
	}
	
	@Test
	public void findLeftFunctionsByRoleName_01() {
		String roleName = "ROLE_admin";
		List<Function> functions = roleFunctionService.findLeftFunctionsByRoleName(roleName);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			System.out.println(function);
		}
	}
	
	@Test
	public void findLeftFunctionsByRoleName_02() {
		String roleName = "ROLE_normal";
		List<Function> functions = roleFunctionService.findLeftFunctionsByRoleName(roleName);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			System.out.println(function);
		}
	}
	
	@Test
	public void updateRoleFunction_01() {
		String roleName = "ROLE_normal";
		String myFunctions = "doUser,doFileUpload,doReportFileUploadTransaction,doReportEntity";
		roleFunctionService.updateRoleFunction(roleName, myFunctions);
//		Assert.assertNotNull(functions);
//		for (Function function : functions) {
//			System.out.println(function);
//		}
	}
	
	@Test
	public void updateRoleFunction_02() {
		String roleName = "ROLE_normal";
		String myFunctions = "doUser,doFileUpload";
		roleFunctionService.updateRoleFunction(roleName, myFunctions);
//		Assert.assertNotNull(functions);
//		for (Function function : functions) {
//			System.out.println(function);
//		}
	}
	
	//public void updateRoleFunction(String roleName, String myFunctions) {
	
	@Test
	public void saveRoleFunction() {
		String roleName = "ROLE_normal";
		String functionName = "doUser";
		Role role = new Role(roleName);
		Function function = new Function(functionName);
		RoleFunction roleFunction = roleFunctionService.saveRoleFunction(role, function);
		Assert.assertNotNull(roleFunction);
		Assert.assertEquals(roleName + functionName, roleFunction.getRoleFunctionName());
	}

	@Test
	public void removeRoleFunction() {
		String roleName = "ROLE_normal";
		String functionName = "doUser";
		Role role = new Role(roleName);
		Function function = new Function(functionName);
		roleFunctionService.removeRoleFunction(role, function);
		String roleFunctionName = roleName + functionName;
		RoleFunction roleFunction = roleFunctionService.findRoleFunctionByRoleFunctionName(roleFunctionName);
		Assert.assertNull(roleFunction);
	}

}