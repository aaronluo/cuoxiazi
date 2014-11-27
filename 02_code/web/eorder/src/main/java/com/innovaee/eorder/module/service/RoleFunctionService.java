package com.innovaee.eorder.module.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.RoleFunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.StringUtil;

public class RoleFunctionService extends BaseService {

	@Resource
	private RoleFunctionDao roleFunctionDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private FunctionDao functionDao;

	@Resource
	private RoleFunctionService roleFunctionService;

	public List<RoleFunction> findAllRoleFunctions() {
		return (List<RoleFunction>) roleFunctionDao.findAllRoleFunctions();
	}

	public RoleFunction findRoleFunctionByRoleFunctionName(String roleFunctionName) {
		return roleFunctionDao.findRoleFunctionByRoleFunctionName(roleFunctionName);
	}

	public List<RoleFunction> findRoleFunctionsByRoleName(String roleName) {
		return (List<RoleFunction>) roleFunctionDao.findRoleFunctionsByRoleName(roleName);
	}

	public List<Function> findFunctionsByRoleName(String roleName) {
		List<Function> functions = new ArrayList<Function>();
		List<RoleFunction> roleFunctions = roleFunctionDao.findRoleFunctionsByRoleName(roleName);
		Function function = null;
		for (RoleFunction roleFunction : roleFunctions) {
			function = functionDao.findFunctionsByFunctionName(roleFunction.getFunctionName());
			functions.add(function);
		}

		return functions;
	}

	/**
	 * @param roleName
	 * @return
	 */
	public List<Function> findLeftFunctionsByRoleName(String roleName) {
		List<Function> functions = new ArrayList<Function>();
		List<RoleFunction> roleFunctions = roleFunctionDao.findRoleFunctionsByRoleName(roleName);
		Function function = null;
		for (RoleFunction roleFunction : roleFunctions) {
			function = functionDao.findFunctionsByFunctionName(roleFunction.getFunctionName());
			functions.add(function);
		}

		List<Function> allFunctions = functionDao.findAllFunctions();
		allFunctions.removeAll(functions);

		return allFunctions;
	}

	public List<RoleFunction> findRoleFunctionsByFunctionName(String functionName) {
		return (List<RoleFunction>) roleFunctionDao.findRoleFunctionsByFunctionName(functionName);
	}

	public void saveRoleFunction(RoleFunction roleFunction) {
		roleFunctionDao.saveRoleFunction(roleFunction);
	}

	public RoleFunction saveRoleFunction(Role role, Function function) {
		RoleFunction rtnRoleFunction = null;
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		Function functionDB = (Function) functionDao.get(function.getFunctionName());
		RoleFunction roleFunction = null;
		if (null != roleDB && null != functionDB) {
			String roleFunctionName = roleDB.getRoleName() + functionDB.getFunctionName();
			String roleName = roleDB.getRoleName();
			String functionName = functionDB.getFunctionName();
			roleFunction = new RoleFunction(roleFunctionName, roleName, functionName);
			RoleFunction roleFunctionDB = (RoleFunction) roleFunctionDao.get(roleFunctionName);
			// 如果DB不存在，就添加
			if (null == roleFunctionDB) {
				rtnRoleFunction = roleFunctionDao.saveRoleFunction(roleFunction);
			}
		}

		return rtnRoleFunction;

	}

	public void removeRoleFunction(Role role, Function function) {
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		Function functionDB = (Function) functionDao.get(function.getFunctionName());
		// RoleFunction roleFunction = null;
		if (null != roleDB && null != functionDB) {
			String roleFunction = roleDB.getRoleName() + functionDB.getFunctionName();
			// String roleName = roleDB.getRoleName();
			// String functionName = functionDB.getFunctionName();
			// roleFunction = new RoleFunction(roleFunction, roleName, functionName);
			RoleFunction roleFunctionDB = (RoleFunction) roleFunctionDao.get(roleFunction);
			// 如果DB不存在，就添加
			if (null != roleFunctionDB) {
				roleFunctionDao.removeRoleFunction(roleFunctionDB);
			}
		}

	}

	/**
	 * @param roleName
	 * @param myFunctions
	 */
	public void updateRoleFunction(String roleName, String myFunctions) {
		// roleFunctionDao.updateRoleFunction(roleFunction);

		List<String> myFunctionNames = StringUtil.stringToList(myFunctions, Constants.REGEX);

		// 1. 获取DB中的functionName列表；
		List<RoleFunction> dbRoleFunctions = roleFunctionDao.findRoleFunctionsByRoleName(roleName);
		List<String> dbFunctionNames = new ArrayList<String>();
		for (RoleFunction roleFunction : dbRoleFunctions) {
			dbFunctionNames.add(roleFunction.getFunctionName());
		}

		// 2. 取得需要新增的functionName列表；
		List<String> toAddFunctionNames = new ArrayList<String>();
		toAddFunctionNames.addAll(myFunctionNames);
		toAddFunctionNames.removeAll(dbFunctionNames);
		for (String functionName : toAddFunctionNames) {
			saveRoleFunction(new Role(roleName), new Function(functionName));
		}

		// 3. 取得需要删除的functionName列表；
		List<String> toDeleteFunctionNames = new ArrayList<String>();
		toDeleteFunctionNames.addAll(dbFunctionNames);
		toDeleteFunctionNames.removeAll(myFunctionNames);
		for (String functionName : toDeleteFunctionNames) {
			removeRoleFunction(new Role(roleName), new Function(functionName));
		}

	}

	public void updateRoleFunction(RoleFunction roleFunction) {
		roleFunctionDao.updateRoleFunction(roleFunction);
	}

	public void removeRoleFunction(String roleFunction) {
		roleFunctionDao.removeRoleFunction(new RoleFunction(roleFunction));
	}

	public void removeRoleFunctions(String[] roleFunctions) {
		int length = roleFunctions.length;
		for (int i = 0; i < length; i++) {
			roleFunctionDao.removeRoleFunction(new RoleFunction(roleFunctions[i]));
		}

	}

}