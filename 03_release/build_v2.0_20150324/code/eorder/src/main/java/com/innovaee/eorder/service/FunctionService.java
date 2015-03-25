/***********************************************
 * Filename        : FunctionService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.service;

import java.util.List;

import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.vo.FunctionVO;

/**
 * @Title: FunctionService
 * @Description: 功能服务接口
 *
 * @version V1.0
 */
public interface FunctionService {

    /**
     * 返回所有值对象列表
     * 
     * @return 功能值对象列表
     */
    public List<FunctionVO> findAllFunctionVOs();

    /**
     * 根据父功能节点ID查找功能
     * 
     * @param parentFunctionId
     *            父功能节点ID
     * @return 功能列表
     */
    public List<Function> findFunctionsByParentFunctionId(Long parentFunctionId);

    /**
     * 根据功能ID查找功能
     * 
     * @param functionId
     *            功能ID
     * @return
     */
    public Function loadFunction(Long functionId);

    /**
     * 保存功能
     * 
     * @param function
     *            待保存的功能
     * @return 被保存的功能
     */
    public long saveFunction(Function function);

    /**
     * 更新功能
     * 
     * @param function
     *            待更新的功能
     */
    public void updateFunction(Function function);

    /**
     * 根据功能ID移除功能
     * 
     * @param functionId
     *            功能ID
     */
    public void deleteFunction(Long functionId);

    /**
     * 通过功能名称查找功能
     * 
     * @param functionName
     *            功能名称
     * @return 功能
     */
    public Function findFunctionByFunctionName(String functionName);

    /**
     * 获得分页信息
     * 
     * @param startRow
     *            记录开始位置
     * @param pageSize
     *            分页大小
     * @return
     */
    public List<Function> getFunctionsByPage(int startRow, int pageSize);

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count();

    /**
     * 根节点功能列表
     * 
     * @return 根节点功能列表
     */
    public List<Function> findRootFunctionList();
}