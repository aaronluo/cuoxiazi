/***********************************************
 * Filename        : FunctionDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.entity.Function;

/**
 * @Title: FunctionDao
 * @Description: 权限数据访问对象接口
 *
 * @version V1.0
 */
public interface FunctionDao extends BaseDao<Function> {
    /**
     * 根据功能名称查找功能
     * 
     * @param functionName
     *            功能名称
     * @return 功能
     */
    public Function findFunctionByFunctionName(final String functionName);

    /**
     * 根据父功能ID查找功能
     * 
     * @param parentFunctionId
     *            父功能ID
     * @return 功能列表
     */
    public List<Function> findFunctionsByParentFunctionId(
            final Long parentFunctionId);

    /**
     * 根节点功能列表
     * 
     * @return 根节点功能列表
     */
    public List<Function> findRootFunctionList();

}