/***********************************************
 * Filename       : HibernateRoleDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import java.util.List;

import com.innovaee.eorder.dao.RoleDao;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.utils.Constants;

/**
 * @Title: HibernateRoleDao
 * @Description: 角色数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateRoleDao extends HibernateBaseDao<Role> implements RoleDao {

    /**
     * 根据角色名称查找角色
     * 
     * @param roleName
     *            角色名称
     * @return 角色
     */
    public Role findRoleByRoleName(final String roleName) {
        final String hql = "from Role as role where role.roleName = ?";
        Object[] paras = { roleName };
        List<Role> roles = getPage(0, Constants.MAX_RECORD, hql, paras);

        if (null != roles && roles.size() > 0) {
            return roles.get(0);
        }
        return null;
    }
}