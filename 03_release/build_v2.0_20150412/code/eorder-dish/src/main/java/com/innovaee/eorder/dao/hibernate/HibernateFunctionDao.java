/***********************************************
 * Filename       : HibernateFunctionDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import java.util.List;

import com.innovaee.eorder.dao.FunctionDao;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.utils.Constants;

/**
 * @Title: HibernateFunctionDao
 * @Description: 功能数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateFunctionDao extends HibernateBaseDao<Function> implements
        FunctionDao {

    /**
     * 根据功能名称查找功能
     * 
     * @param functionName
     *            功能名称
     * @return 功能
     */
    public Function findFunctionByFunctionName(final String functionName) {
        final String hql = "from Function as function where function.functionName = ?";
        Object[] paras = { functionName };
        List<Function> functions = getPage(0, Constants.MAX_RECORD, hql, paras);

        if (null != functions && functions.size() > 0) {
            return functions.get(0);
        }
        return null;
    }

    /**
     * 根节点功能列表
     * 
     * @return 根节点功能列表
     */
    public List<Function> findRootFunctionList() {
        final String hql = "from Function as function where function.functionParent = 0";
        return getPage(0, Constants.MAX_RECORD, hql);
    }

    /**
     * 根据父功能ID查找功能
     * 
     * @param parentFunctionId
     *            父功能ID
     * @return 功能列表
     */
    public List<Function> findFunctionsByParentFunctionId(
            final Long parentFunctionId) {
        final String hql = "from Function as function where function.functionParent = ?";
        Object[] paras = { parentFunctionId };
        return getPage(0, Constants.MAX_RECORD, hql, paras);
    }

}