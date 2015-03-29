/***********************************************
 * Filename        : FunctionService.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.innovaee.eorder.dao.FunctionDao;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.service.FunctionService;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.DateUtil;
import com.innovaee.eorder.vo.FunctionVO;

/**
 * @Title: FunctionService
 * @Description: 功能服务
 *
 * @version V1.0
 */
public class FunctionServiceImpl implements FunctionService {

    /** 功能数据访问对象 */
    @Resource
    private FunctionDao functionDao;

    /**
     * 返回所有值对象列表
     * 
     * @return 功能值对象列表
     */
    public List<FunctionVO> findAllFunctionVOs() {
        List<FunctionVO> functionvos = new ArrayList<FunctionVO>();
        FunctionVO functionVO = null;
        Function parentFunction = null;
        List<Function> functions = functionDao.loadAll();
        for (Function function : functions) {
            functionVO = new FunctionVO();
            BeanUtils.copyProperties(function, functionVO);

            // 设置上级权限名称
            parentFunction = functionDao.get(function.getFunctionParent());
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
    public List<Function> findFunctionsByParentFunctionId(Long parentFunctionId) {
        return functionDao.findFunctionsByParentFunctionId(parentFunctionId);
    }

    /**
     * 根据功能ID查找功能
     * 
     * @param functionId
     *            功能ID
     * @return
     */
    public Function loadFunction(Long functionId) {
        return (Function) functionDao.get(functionId);
    }

    /**
     * 保存功能
     * 
     * @param function
     *            待保存的功能
     * @return 被保存的功能
     */
    public long saveFunction(Function function) {
        Date currentDay = Calendar.getInstance().getTime();
        String functionOrder = DateUtil.formatDate(
                Constants.DATE_FORMAT_YYYYMMDDHHMMSS, currentDay);
        //
        function.setFunctionOrder(functionOrder);

        return functionDao.save(function);
    }

    /**
     * 更新功能
     * 
     * @param function
     *            待更新的功能
     */
    public void updateFunction(Function function) {
        functionDao.update(function);
    }

    /**
     * 根据功能ID移除功能
     * 
     * @param functionId
     *            功能ID
     */
    public void deleteFunction(Long functionId) {
        Function function = functionDao.get(functionId);
        functionDao.delete(function);
    }

    /**
     * 通过功能名称查找功能
     * 
     * @param functionName
     *            功能名称
     * @return 功能
     */
    public Function findFunctionByFunctionName(String functionName) {
        return functionDao.findFunctionByFunctionName(functionName);
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
    public List<Function> getFunctionsByPage(int startRow, int pageSize) {
        String hql = "FROM Function as f ORDER BY f.id DESC";
        return functionDao.getPage(startRow, pageSize, hql);
    }

    /**
     * 获得总记录条数
     * 
     * @return 总记录条数
     */
    public Integer count() {
        return functionDao.count();
    }

    /**
     * 根节点功能列表
     * 
     * @return 根节点功能列表
     */
    public List<Function> findRootFunctionList() {
        return functionDao.findRootFunctionList();
    }
}