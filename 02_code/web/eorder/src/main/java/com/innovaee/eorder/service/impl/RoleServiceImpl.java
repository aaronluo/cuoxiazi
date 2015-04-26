/***********************************************
 * Filename        : RoleService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.dao.FunctionDao;
import com.innovaee.eorder.dao.RoleDao;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.service.RoleService;
import com.innovaee.eorder.vo.RoleVO;

/**
 * @Title: RoleService
 * @Description: 角色服务
 *
 * @version V1.0
 */
public class RoleServiceImpl implements RoleService {

    /** 角色数据访问对象 */
    @Resource
    private RoleDao roleDao;

    /** 功能数据访问对象 */
    @Resource
    private FunctionDao functionDao;

    /**
     * 返回所有用户值对象
     * 
     * @return 角色值对象列表
     */
    public List<RoleVO> findAllRoleVOs() {
        List<RoleVO> rolevos = new ArrayList<RoleVO>();

        Set<Function> functions = null;
        List<String> functionNames = null;
        Function function = null;

        List<Role> roles = roleDao.loadAll();
        for (Role role : roles) {
            RoleVO roleVO = new RoleVO();
            BeanUtils.copyProperties(role, roleVO);

            functions = role.getFunctions();
            functionNames = new ArrayList<String>();
            for (Function tempFunction : functions) {
                // 查找功能（权限）名称
                function = functionDao.get(tempFunction.getId());
                functionNames.add(function.getFunctionName());
            }
            roleVO.setFunctionName(functionNames.toString());

            rolevos.add(roleVO);
        }

        return rolevos;
    }

    /**
     * 通过角色ID查找角色
     * 
     * @param roleId
     *            角色ID
     * @return 角色
     */
    public Role loadRole(Long roleId) {
        return (Role) roleDao.get(roleId);
    }

    /**
     * 根据角色名称查找角色
     * 
     * @param roleName
     *            角色名称
     * @return 角色
     */
    public Role findRoleByRoleName(String roleName) {
        return (Role) roleDao.findRoleByRoleName(roleName);
    }

    /**
     * 保存角色
     * 
     * @param role
     *            待保存的角色
     * @return 被保存的角色
     */
    public Role addRole(Role role) throws DuplicateNameException {
        // 1. 检查是否有同名角色
        Role dbRole = roleDao.findRoleByRoleName(role.getRoleName());

        Long newId = 0L;
        if (null == dbRole) {
            Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
                    .getTime()));
            role.setCreateDate(createAt);
            newId = roleDao.save(role);
            if (0L != newId) {

                role.setId(newId);
            }
        } else {
            // 有重名菜品，抛出异常
            throw new DuplicateNameException(role.getRoleName());
        }

        return role;
    }

    /**
     * 更新角色
     * 
     * @param role
     *            待更新的角色
     */
    public void updateRole(Role role) {
        roleDao.update(role);
    }

    /**
     * 根据角色ID移除角色
     * 
     * @param roleId
     *            角色ID
     */
    public void deleteRole(Long roleId) {
        Role role = roleDao.get(roleId);
        roleDao.delete(role);
    }

    /**
     * 根据角色ID查找剩余的功能列表
     * 
     * @param roleId
     *            角色ID
     * @return 功能列表
     */
    public List<Function> findLeftFunctionsByRoleId(Long roleId) {
        List<Function> leftFunctions = new ArrayList<Function>();
        List<Function> functions = new ArrayList<Function>();
        Role role = roleDao.get(roleId);
        if (null != role) {
            Set<Function> functionSet = role.getFunctions();
            for (Function function : functionSet) {
                functions.add(function);
            }
        }

        List<Function> allFunctions = functionDao.loadAll();
        for (Function functionDB : allFunctions) {
            leftFunctions.add(functionDB);
        }

        leftFunctions.removeAll(functions);

        return leftFunctions;
    }

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count() {
        return roleDao.count();
    }

    /**
     * 获得分页信息
     * 
     * @param startRow
     *            记录开始位置
     * @param pageSize
     *            分页大小
     * @return
     */
    public List<Role> getRolesByPage(int startRow, int pageSize) {
        String hql = "FROM Role as r ORDER BY r.id DESC";
        return roleDao.getPage(startRow, pageSize, hql);
    }

}