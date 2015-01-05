/***********************************************
 * Filename        : FunctionService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.vo.FunctionVO;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: FunctionService
 * @Description: 功能服务
 *
 * @version V1.0
 */
public class FunctionService extends BaseService {

    /** 功能数据访问对象 */
    @Resource
    private FunctionDao functionDao;

    /**
     * 返回所有值对象列表
     * 
     * @return 功能值对象列表
     */
    public List<FunctionVO> findAllFunctionVOs() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("返回所有值对象列表");
        }
        List<FunctionVO> functionvos = new ArrayList<FunctionVO>();
        FunctionVO functionVO = null;
        Function parentFunction = null;
        List<Function> functions = functionDao.findAllFunctions();
        for (Function function : functions) {
            functionVO = new FunctionVO();
            BeanUtils.copyProperties(function, functionVO);

            // 设置上级权限名称
            parentFunction = functionDao.loadFunction(function
                    .getFunctionParent());
            if (null != parentFunction) {
                functionVO.setFunctionParentName(parentFunction
                        .getFunctionName());
            }

            functionvos.add(functionVO);
        }

        return functionvos;
    }

    /**
     * 根据父功能节点ID查找功能
     * 
     * @param parentFunctionId
     *            父功能节点ID
     * @return 功能列表
     */
    public List<Function> findFunctionsByParentFunctionId(
            Integer parentFunctionId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据父功能节点ID查找功能");
        }
        return functionDao.findFunctionsByParentFunctionId(parentFunctionId);
    }

    /**
     * 根据功能ID查找功能
     * 
     * @param functionId
     *            功能ID
     * @return
     */
    public Function loadFunction(Integer functionId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据功能ID查找功能");
        }
        return (Function) functionDao.loadFunction(functionId);
    }

    /**
     * 保存功能
     * 
     * @param function
     *            待保存的功能
     * @return 被保存的功能
     */
    public Function saveFunction(Function function) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("保存功能");
        }
        return functionDao.saveFunction(function);
    }

    /**
     * 更新功能
     * 
     * @param function
     *            待更新的功能
     */
    public void updateFunction(Function function) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("更新功能");
        }
        functionDao.updateFunction(function);
    }

    /**
     * 根据功能ID移除功能
     * 
     * @param functionId
     *            功能ID
     */
    public void removeFunction(Integer functionId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据功能ID移除功能");
        }
        functionDao.removeFunction(new Function(functionId));
    }

    /**
     * 通过功能名称查找功能
     * 
     * @param functionName
     *            功能名称
     * @return 功能
     */
    public Function findFunctionByFunctionName(String functionName) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("通过功能名称查找功能");
        }
        return functionDao.findFunctionByFunctionName(functionName);
    }

}