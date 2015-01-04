package com.innovaee.eorder.module.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.utils.StringUtil;

/**
 * @Title: RoleFunctionServiceTest
 * @Description: 角色功能服务测试类
 * @version V1.0
 */
public class RoleFunctionServiceTest extends BaseSpringTestCase {

	/** 角色功能服务类对象 */
	@Autowired
	private RoleFunctionService roleFunctionService;

	// /** 角色ID */
	// private Integer roleId = 2;
	// /** 功能ID */
	// private Integer functionId = 2;

	/** 功能服务类对象 */
	@Autowired
	private FunctionService functionService;

	/** 功能名称 */
	private String functionName = "FunctionName1";

	/** 功能描述 */
	private String functionDesc = "FunctionDesc1";

	/** 功能路径 */
	private String functionPath = "/test/doTest.action";

	/** 父功能ID */
	private Integer functionParent = 1;

	/** 功能排序 */
	private String functionOrder = "010800";

	/** 功能状态 */
	private Boolean functionStatus = true;

	/** 功能名称 */
	private String rootFunctionName = "RootFunctionName";

	/** 功能描述 */
	private String rootFunctionDesc = "RootFunctionDesc";

	/** 功能路径 */
	private String rootFunctionPath = "/test/doRootTest.action";

	/** 父功能ID */
	private Integer rootFunctionParent = 0;

	/** 功能排序 */
	private String rootFunctionOrder = "050000";

	/** 功能状态 */
	private Boolean rootFunctionStatus = true;

	/** 角色服务类对象 */
	@Autowired
	private RoleService roleService;

	/** 角色名称 */
	private String roleName = "RoleName1";

	/** 角色描述 */
	private String roleDesc = "RoleDesc1";

	/** 角色状态 */
	private Boolean roleStatus = true;

	@Test
	public void findLeftFunctionsByRoleId_01() {
		List<Function> functions = roleFunctionService
				.findLeftFunctionsByRoleId(1);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			LOGGER.debug(function);
		}

	}

	@Test
	public void findLeftFunctionsByRoleId_02() {
		List<Function> functions = roleFunctionService
				.findLeftFunctionsByRoleId(2);
		Assert.assertNotNull(functions);
		for (Function function : functions) {
			LOGGER.debug(function);
		}

	}

	@Test
	public void findRoleFunctionsByFunctionId_01() {
		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByFunctionId(1);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			LOGGER.debug(roleFunction);
		}
	}

	@Test
	public void findRoleFunctionsByFunctionId_02() {
		List<RoleFunction> roleFunctions = roleFunctionService
				.findRoleFunctionsByFunctionId(2);
		Assert.assertNotNull(roleFunctions);
		for (RoleFunction roleFunction : roleFunctions) {
			LOGGER.debug(roleFunction);
		}
	}

	/**
	 * 先增加，再查找，再删除，再查找
	 */
	@Test
	public void operateFunction_01() {
		// 先新增一个角色对象
		Role role = new Role(roleName, roleDesc, roleStatus);
		// 1. 保存
		Role roleNew = roleService.saveRole(role);
		// 得到新增后的ID
		Integer newRoleId = roleNew.getRoleId();

		// 先新增一个功能对象
		Function function = new Function(functionName, functionDesc,
				functionPath, functionParent, functionOrder, functionStatus);
		// 1. 保存
		Function functionNew = functionService.saveFunction(function);
		// 得到新增后的ID
		Integer newFunctionId = functionNew.getFunctionId();
		List<Integer> functionIdList = new ArrayList<Integer>();
		functionIdList.add(newFunctionId);
		String myFunctionIds = StringUtil.integerListToString(functionIdList);

		// 2. 更新（新增）角色功能关系
		roleFunctionService.updateRoleFunction(newRoleId, myFunctionIds);

		functionIdList.remove(newFunctionId);
		myFunctionIds = StringUtil.integerListToString(functionIdList);
		// 3. 更新（删除）角色功能关系
		roleFunctionService.updateRoleFunction(newRoleId, myFunctionIds);

		// 4. 移除角色
		roleService.removeRole(roleNew.getRoleId());

		// 4.2 通过角色ID查找角色
		Role roleDB = roleService.loadRole(newRoleId);
		Assert.assertNull(roleDB);

		// 5. 移除功能
		functionService.removeFunction(functionNew.getFunctionId());

		// 5.2 通过角色ID查找角色
		Function functionDB = functionService.loadFunction(newFunctionId);
		Assert.assertNull(functionDB);
	}

	/**
	 * 先增加，再查找，再删除，再查找
	 */
	@Test
	public void operateFunction_02() {
		// 先新增一个角色对象
		Role role = new Role(roleName, roleDesc, roleStatus);
		// 1. 保存
		Role roleNew = roleService.saveRole(role);
		// 得到新增后的ID
		Integer newRoleId = roleNew.getRoleId();

		// 1.1先新增一个父功能对象
		Function rootFunction = new Function(rootFunctionName,
				rootFunctionDesc, rootFunctionPath, rootFunctionParent,
				rootFunctionOrder, rootFunctionStatus);

		// 1. 保存
		Function rootFunctionNew = functionService.saveFunction(rootFunction);
		// 得到新增后的ID
		Integer newRootFunctionId = rootFunctionNew.getFunctionId();

		// 1.2 再新增一个子功能对象
		Function subFunction = new Function(rootFunctionName, rootFunctionDesc,
				rootFunctionPath, newRootFunctionId, rootFunctionOrder,
				rootFunctionStatus);
		Function subFunctionNew = functionService.saveFunction(subFunction);

		Integer newSubFunctionId = subFunctionNew.getFunctionId();
		List<Integer> functionIdList = new ArrayList<Integer>();
		functionIdList.add(newSubFunctionId);
		String myFunctionIds = StringUtil.integerListToString(functionIdList);

		// 2. 更新（新增）角色功能关系
		roleFunctionService.updateRoleFunction(newRoleId, myFunctionIds);

		functionIdList.remove(newSubFunctionId);
		myFunctionIds = StringUtil.integerListToString(functionIdList);
		// 3. 更新（删除）角色功能关系
		roleFunctionService.updateRoleFunction(newRoleId, myFunctionIds);

		// 4. 移除角色
		roleService.removeRole(roleNew.getRoleId());

		// 4.2 通过角色ID查找角色
		Role roleDB = roleService.loadRole(newRoleId);
		Assert.assertNull(roleDB);

		// 5. 移除功能
		functionService.removeFunction(newSubFunctionId);
		functionService.removeFunction(newRootFunctionId);

		// 5.2 通过角色ID查找角色
		Function functionDB = functionService.loadFunction(newSubFunctionId);
		Assert.assertNull(functionDB);
		functionDB = functionService.loadFunction(newRootFunctionId);
		Assert.assertNull(functionDB);
	}

}