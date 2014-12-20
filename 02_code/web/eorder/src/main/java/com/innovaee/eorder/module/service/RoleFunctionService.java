/***********************************************
 * Filename		: RoleFunctionService.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

/**   
* @Title: RoleFunctionService 
* @Description: 角色功能服务 
* @author coderdream@gmail.com   
* @version V1.0   
*/
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

	public RoleFunction findRoleFunctionByIds(Integer roleId, Integer functionId) {
		return roleFunctionDao.findRoleFunctionByIds(roleId, functionId);
	}

	public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
		return (List<RoleFunction>) roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
	}

	public List<RoleFunction> findRoleFunctionsByFunctionId(Integer functionId) {
		return (List<RoleFunction>) roleFunctionDao
				.findRoleFunctionsByFunctionId(functionId);
	}

	public List<Function> findFunctionsByRoleId(Integer roleId) {
		List<Function> functions = new ArrayList<Function>();
		List<RoleFunction> roleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		Function function = null;
		for (RoleFunction roleFunction : roleFunctions) {
			function = functionDao.loadFunction(roleFunction.getFunctionId());
			// 过滤Root功能
			if (0 != function.getFunctionParent()) {
				functions.add(function);
			}
		}

		return functions;
	}

	/**
	 * @param roleId
	 * @return
	 */
	public List<Function> findLeftFunctionsByRoleId(Integer roleId) {
		List<Function> leftFunctions = new ArrayList<Function>();
		List<Function> functions = new ArrayList<Function>();
		List<RoleFunction> roleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		Function function = null;
		for (RoleFunction roleFunction : roleFunctions) {
			function = functionDao.loadFunction(roleFunction.getFunctionId());
			// 过滤Root类型的功能(functionParen为0)
			if (0 != function.getFunctionParent()) {
				functions.add(function);
			}
		}

		List<Function> allFunctions = functionDao.findAllFunctions();
		for (Function functionDB : allFunctions) {
			// 过滤Root类型的功能(functionParen为0)
			if (0 != functionDB.getFunctionParent()) {
				leftFunctions.add(functionDB);
			}
		}

		leftFunctions.removeAll(functions);

		return leftFunctions;
	}

	public void saveRoleFunction(RoleFunction roleFunction) {
		roleFunctionDao.saveRoleFunction(roleFunction);
	}

	public RoleFunction saveRoleFunction(Role role, Function function) {
		RoleFunction rtnRoleFunction = null;
		Role roleDB = (Role) roleDao.get(role.getRoleId());
		Function functionDB = (Function) functionDao.get(function
				.getFunctionId());
		RoleFunction roleFunction = null;
		if (null != roleDB && null != functionDB) {
			Integer roleId = roleDB.getRoleId();
			Integer functionId = functionDB.getFunctionId();
			Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
					.getTime()));
			roleFunction = new RoleFunction(roleId, functionId, createAt);
			RoleFunction roleFunctionDB = (RoleFunction) roleFunctionDao
					.findRoleFunctionByIds(roleId, functionId);
			// 如果DB不存在，就添加
			if (null == roleFunctionDB) {
				// 先检查这个功能的父功能是否给该roleId
				Integer parentFunctionId = functionDB.getFunctionParent();
				RoleFunction parentRoleFunctionDB = (RoleFunction) roleFunctionDao
						.findRoleFunctionByIds(roleId, parentFunctionId);
				// 如果不存在，就添加该父功能
				if (null == parentRoleFunctionDB) {
					parentRoleFunctionDB = new RoleFunction(roleId,
							parentFunctionId, createAt);
					rtnRoleFunction = roleFunctionDao
							.saveRoleFunction(parentRoleFunctionDB);
				}

				rtnRoleFunction = roleFunctionDao
						.saveRoleFunction(roleFunction);
			}
		}

		return rtnRoleFunction;
	}

	public void removeRoleFunction(Role role, Function function) {
		Role roleDB = (Role) roleDao.get(role.getRoleId());
		Function functionDB = (Function) functionDao.get(function
				.getFunctionId());
		// RoleFunction roleFunction = null;
		if (null != roleDB && null != functionDB) {
			Integer roleId = roleDB.getRoleId();
			Integer functionId = functionDB.getFunctionId();
			RoleFunction roleFunctionDB = (RoleFunction) roleFunctionDao
					.findRoleFunctionByIds(roleId, functionId);
			// 如果DB不存在，就添加
			if (null != roleFunctionDB) {
				roleFunctionDao.removeRoleFunction(roleFunctionDB);
			}

			// 如果某功能组的所有功能都删除了，如果只剩下父功能对应的一条记录，则该父功能记录也要移除
			// 先得到这个功能的父功能及其子功能在角色功能表中的记录列表
			// 1、先通过parentFunctionId找到所有该功能的子功能
			Integer parentFunctionId = functionDB.getFunctionParent();
			List<Function> subFunctionList = functionDao
					.findFunctionsByParentFunctionId(parentFunctionId);
			List<Integer> subFunctionIdList = new ArrayList<Integer>();
			for (Function subFunction : subFunctionList) {
				System.out.println(subFunction);
				subFunctionIdList.add(subFunction.getFunctionId());
			}

			List<RoleFunction> subRoleFunctionListDB = roleFunctionDao
					.findParentRoleFunctionByIds(roleId, parentFunctionId);
			// 如果不存在子功能，就删除该父功能
			if (0 == subRoleFunctionListDB.size()) {
				RoleFunction parentRoleFunction = new RoleFunction(roleId,
						parentFunctionId);
				roleFunctionDao.removeRoleFunction(parentRoleFunction);
			}
		}
	}

	public List<RoleFunction> findRoleFunctionsByFunctionIds(Integer roleId,
			final List<Integer> parentFunctionId) {
		return roleFunctionDao.findRoleFunctionsByFunctionIds(roleId,
				parentFunctionId);
	}

	/**
	 * @param roleId
	 * @param myFunctions
	 */
	public void updateRoleFunctionBackup(Integer roleId, String myFunctionIds) {
		List<Integer> myFunctionIdList = StringUtil.stringToIntegerList(
				myFunctionIds, Constants.REGEX);

		// 1. 根据roleId获取DB中的functionId列表；
		List<RoleFunction> dbRoleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		List<Integer> dbFunctionIds = new ArrayList<Integer>();
		for (RoleFunction roleFunction : dbRoleFunctions) {
			dbFunctionIds.add(roleFunction.getFunctionId());
		}

		// 2. 取得需要新增的functionId列表；
		List<Integer> toAddFunctionIds = new ArrayList<Integer>();
		toAddFunctionIds.addAll(myFunctionIdList);
		toAddFunctionIds.removeAll(dbFunctionIds);
		for (Integer functionId : toAddFunctionIds) {
			saveRoleFunction(new Role(roleId), new Function(functionId));
		}

		// 3. 取得需要删除的functionId列表；
		List<Integer> toDeleteFunctionIds = new ArrayList<Integer>();
		toDeleteFunctionIds.addAll(dbFunctionIds);
		toDeleteFunctionIds.removeAll(myFunctionIdList);
		for (Integer functionId : toDeleteFunctionIds) {
			removeRoleFunction(new Role(roleId), new Function(functionId));
		}
	}

	/**
	 * 先删除已有的，后增加最新的
	 * 
	 * @param roleId
	 * @param myFunctions
	 */
	public void updateRoleFunction(Integer roleId, String myFunctionIds) {
		// 1. 根据roleId获取DB中的functionId列表，然后删除；
		List<RoleFunction> dbRoleFunctions = roleFunctionDao
				.findRoleFunctionsByRoleId(roleId);
		for (RoleFunction roleFunction : dbRoleFunctions) {
			// dbFunctionIds.add(roleFunction.getFunctionId());
			removeRoleFunction(new Role(roleId),
					new Function(roleFunction.getFunctionId()));
		}

		// 2. 取得需要新增的functionId列表；
		List<Integer> myFunctionIdList = StringUtil.stringToIntegerList(
				myFunctionIds, Constants.REGEX);
		for (Integer functionId : myFunctionIdList) {
			saveRoleFunction(new Role(roleId), new Function(functionId));
		}
	}

	public void removeRoleFunction(Integer roleFunctionId) {
		roleFunctionDao.removeRoleFunction(new RoleFunction(roleFunctionId));
	}

	public void removeRoleFunctions(Integer[] roleFunctionIds) {
		int length = roleFunctionIds.length;
		for (int i = 0; i < length; i++) {
			roleFunctionDao.removeRoleFunction(new RoleFunction(
					roleFunctionIds[i]));
		}
	}
}