/***********************************************
 * Filename        : RoleDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.Role;

/**
 * @Title: RoleDao
 * @Description: 角色数据访问对象接口
 *
 * @version V1.0
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 根据角色名称查找角色
     * 
     * @param roleName
     *            角色名称
     * @return 角色
     */
    public Role findRoleByRoleName(final String roleName) ;
}