package com.innovaee.eorder.module.service;

import java.util.ArrayList;
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

	private Integer roleId = 2;
	private Integer functionId = 2;

	@Test
	public void getAllRoleFunctions() {
		List<RoleFunction> allRoleFunctions = roleFunctionService
				.findAllRoleFunctions();
		Assert.assertNotNull(allRoleFunctions);
		for (RoleFunction roleFunction : allRoleFunctions) {
			System.out.println(roleFunction);
		}
	}

	@Test
	public void findRoleFunctionByRoleId() {
		Integer roleId = 1;
		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByRoleId(roleId);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			System.out.println(roleFunction);
		}
	}

	@Test
	public void findRoleFunctionByIds() {
		Integer roleId = 1;
		Integer functionId = 2;
		RoleFunction roleFunction = roleFunctionService.findRoleFunctionByIds(
				roleId, functionId);
		Assert.assertNotNull(roleFunction);
		Assert.assertEquals(roleId, roleFunction.getRoleId());
		Assert.assertEquals(functionId, roleFunction.getFunctionId());
	}

	@Test
	public void saveRoleFunction_01() {
		Role role = new Role(roleId);
		Function function = new Function(functionId);
		RoleFunction newRoleFunction = roleFunctionService.saveRoleFunction(
				role, function);
		Assert.assertNotNull(newRoleFunction);
		Assert.assertEquals(roleId, newRoleFunction.getRoleId());
		Assert.assertEquals(functionId, newRoleFunction.getFunctionId());
	}

	@Test
	public void saveRoleFunction_02() {
		roleId = 2;
		functionId = 9;
		Role role = new Role(roleId);
		Function function = new Function(functionId);
		RoleFunction newRoleFunction = roleFunctionService.saveRoleFunction(
				role, function);
		Assert.assertNotNull(newRoleFunction);
		Assert.assertEquals(roleId, newRoleFunction.getRoleId());
		Assert.assertEquals(functionId, newRoleFunction.getFunctionId());
	}

	@Test
	public void removeRoleFunction() {
		Role role = new Role(roleId);
		Function function = new Function(functionId);
		roleFunctionService.removeRoleFunction(role, function);

		RoleFunction roleFunctionDB = roleFunctionService
				.findRoleFunctionByIds(roleId, functionId);
		Assert.assertNull(roleFunctionDB);
	}

	@Test
	public void saveAndRemoveRoleFunction() {
		Role role = new Role(roleId);
		Function function = new Function(functionId);
		RoleFunction newRoleFunction = roleFunctionService.saveRoleFunction(
				role, function);
		Assert.assertNotNull(newRoleFunction);
		Assert.assertEquals(roleId, newRoleFunction.getRoleId());
		Assert.assertEquals(functionId, newRoleFunction.getFunctionId());

		roleFunctionService.removeRoleFunction(role, function);

		RoleFunction roleFunctionDB = roleFunctionService
				.findRoleFunctionByIds(roleId, functionId);
		Assert.assertNull(roleFunctionDB);
	}

	@Test
	public void updateRoleFunction_01() {
		roleId = 2;
		String myFunctionIds = "2,3,8,11";
		roleFunctionService.updateRoleFunction(roleId, myFunctionIds);

		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByRoleId(roleId);
		Assert.assertNotNull(roleFunctions);
		Assert.assertEquals(7, roleFunctions.size());
	}

	@Test
	public void updateRoleFunction_02() {
		roleId = 2;
		String myFunctionIds = "9,11";
		roleFunctionService.updateRoleFunction(roleId, myFunctionIds);

		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByRoleId(roleId);
		Assert.assertNotNull(roleFunctions);
		Assert.assertEquals(4, roleFunctions.size());
	}

	@Test
	public void findLeftFunctionsByRoleId() {
		List<Function> functions = roleFunctionService
				.findLeftFunctionsByRoleId(roleId);
		Assert.assertNotNull(functions);
		Assert.assertEquals(3, functions.size());
	}

	@Test
	public void findLeftFunctionsByRoleId_02() {
		roleId = 3;
		List<Function> functions = roleFunctionService
				.findLeftFunctionsByRoleId(roleId);
		Assert.assertNotNull(functions);
		Assert.assertEquals(8, functions.size());
	}

	@Test
	public void findRoleFunctionsByFunctionIds() {
		List<Integer> parentFunctionId = new ArrayList<Integer>();
		parentFunctionId.add(3);
		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByFunctionIds(roleId, parentFunctionId);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			System.out.println(roleFunction);
		}
	}

}