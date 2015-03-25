/***********************************************
 * Filename        : RoleService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.vo.RoleVO;

/**
 * @Title: RoleService
 * @Description: 角色服务接口
 *
 * @version V1.0
 */
public interface RoleService {

    /**
     * 返回所有用户值对象
     * 
     * @return 角色值对象列表
     */
    public List<RoleVO> findAllRoleVOs();

    /**
     * 通过角色ID查找角色
     * 
     * @param roleId
     *            角色ID
     * @return 角色
     */
    public Role loadRole(Long roleId);

    /**
     * 根据角色名称查找角色
     * 
     * @param roleName
     *            角色名称
     * @return 角色
     */
    public Role findRoleByRoleName(String roleName);

    /**
     * 保存角色
     * 
     * @param role
     *            待保存的角色
     * @return 被保存的角色
     */
    public Long saveRole(Role role);

    /**
     * 更新角色
     * 
     * @param role
     *            待更新的角色
     */
    public void updateRole(Role role);

    /**
     * 根据角色ID移除角色
     * 
     * @param roleId
     *            角色ID
     */
    public void deleteRole(Long roleId);

    /**
     * 根据角色ID查找剩余的功能列表
     * 
     * @param roleId
     *            角色ID
     * @return 功能列表
     */
    public List<Function> findLeftFunctionsByRoleId(Long roleId);

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count();

    /**
     * 获得分页信息
     * 
     * @param startRow
     *            记录开始位置
     * @param pageSize
     *            分页大小
     * @return
     */
    public List<Role> getRolesByPage(int startRow, int pageSize);
}