/***********************************************
 * Filename        : RoleFunctionService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.RoleFunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: RoleFunctionService
 * @Description: 角色功能服务
 *
 * @version V1.0
 */
public class RoleFunctionService extends BaseService {

    /** 角色功能数据访问对象 */
    @Resource
    private RoleFunctionDao roleFunctionDao;

    /** 角色数据访问对象 */
    @Resource
    private RoleDao roleDao;

    /** 功能数据访问对象 */
    @Resource
    private FunctionDao functionDao;

    /**
     * 根据功能ID查找角色功能
     * 
     * @param functionId
     *            功能ID
     * @return 角色功能
     */
    public List<RoleFunction> findRoleFunctionsByFunctionId(Integer functionId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据功能ID查找角色功能");
        }
        return (List<RoleFunction>) roleFunctionDao
                .findRoleFunctionsByFunctionId(functionId);
    }

    /**
     * 根据角色ID查找功能列表
     * 
     * @param roleId
     *            角色ID
     * @return 功能列表
     */
    public List<Function> findFunctionsByRoleId(Integer roleId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据角色ID查找功能列表");
        }
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
     * 根据角色ID查找剩余的功能列表
     * 
     * @param roleId
     *            角色ID
     * @return 功能列表
     */
    public List<Function> findLeftFunctionsByRoleId(Integer roleId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据角色ID查找剩余的功能列表");
        }
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

    /**
     * 根据角色对象和功能对象保存角色功能信息
     * 
     * @param role
     *            待保存的角色信息
     * @param function
     *            待保存的功能信息
     * @return 角色功能
     */
    private RoleFunction saveRoleFunction(Role role, Function function) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据角色对象和功能对象保存角色功能信息");
        }
        RoleFunction rtnRoleFunction = null;
        Role roleDB = (Role) roleDao.loadRole(role.getRoleId());
        Function functionDB = (Function) functionDao.loadFunction(function
                .getFunctionId());
        RoleFunction roleFunction = null;
        if (null != roleDB && null != functionDB) {
            Integer roleId = roleDB.getRoleId();
            Integer functionId = functionDB.getFunctionId();
            roleFunction = new RoleFunction(roleId, functionId);
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
                            parentFunctionId);
                    rtnRoleFunction = roleFunctionDao
                            .saveRoleFunction(parentRoleFunctionDB);
                }

                rtnRoleFunction = roleFunctionDao
                        .saveRoleFunction(roleFunction);
            }
        }

        return rtnRoleFunction;
    }

    /**
     * 移除角色功能信息
     * 
     * @param role
     *            待移除的角色信息
     * @param function
     *            带移除的功能信息
     */
    private void removeRoleFunction(Role role, Function function) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("移除功能角色");
        }

        // 1. 根据角色ID获取角色对象
        Role roleDB = (Role) roleDao.loadRole(role.getRoleId());
        // 2. 根据权限ID获取功能对象
        Function functionDB = (Function) functionDao.loadFunction(function
                .getFunctionId());

        if (null != roleDB && null != functionDB) {
            Integer roleId = roleDB.getRoleId();
            Integer functionId = functionDB.getFunctionId();
            // 1、根据角色ID和功能ID查找角色功能
            RoleFunction roleFunctionDB = (RoleFunction) roleFunctionDao
                    .findRoleFunctionByIds(roleId, functionId);
            // 2、如果数据库中存在此角色功能信息，就删除
            if (null != roleFunctionDB) {
                roleFunctionDao.removeRoleFunction(roleFunctionDB);
            }

            // 如果某功能组的所有功能都删除了，如果只剩下父功能对应的一条记录，则该父功能记录也要移除
            // 先得到这个功能的父功能及其子功能在角色功能表中的记录列表
            // 3、先通过parentFunctionId找到所有该功能的子功能
            Integer parentFunctionId = functionDB.getFunctionParent();
            List<Function> subFunctionList = functionDao
                    .findFunctionsByParentFunctionId(parentFunctionId);
            List<Integer> subFunctionIdList = new ArrayList<Integer>();
            for (Function subFunction : subFunctionList) {
                subFunctionIdList.add(subFunction.getFunctionId());
            }

            // 4、通过父功能ID列表查找角色功能
            List<RoleFunction> subRoleFunctionList = roleFunctionDao
                    .findRoleFunctionsByFunctionIds(subFunctionIdList);

            // 5、如果不存在子功能，就删除该父功能
            if (null == subRoleFunctionList || 0 == subRoleFunctionList.size()) {
                // 5.1 通过角色ID和父功能ID查找角色功能列表
                List<RoleFunction> parentRoleFunctionList = roleFunctionDao
                        .findParentRoleFunctionByIds(roleId, parentFunctionId);
                if (null != parentRoleFunctionList
                        && 0 < parentRoleFunctionList.size()) {
                    roleFunctionDao.removeRoleFunction(parentRoleFunctionList
                            .get(0));
                }

            }
        }
    }

    /**
     * 先删除已有的，后增加最新的
     * 
     * @param roleId
     *            角色ID
     * @param myFunctions
     *            功能数组字符串
     */
    public void updateRoleFunction(Integer roleId, String myFunctionIds) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("更新功能角色");
        }
        // 1. 根据roleId获取DB中的functionId列表，然后删除；
        List<RoleFunction> dbRoleFunctions = roleFunctionDao
                .findRoleFunctionsByRoleId(roleId);
        for (RoleFunction roleFunction : dbRoleFunctions) {
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

}