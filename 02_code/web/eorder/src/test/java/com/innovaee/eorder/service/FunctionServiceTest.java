package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.vo.FunctionVO;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Title: FunctionServiceTest
 * @Description: 功能服务测试类
 * @version V1.0
 */
public class FunctionServiceTest extends BaseSpringTestCase {

    /** 功能服务类对象 */
    @Autowired
    private FunctionService functionService;

    /** 功能名称 */
    private String functionName = "FunctionName1";

    /** 功能描述 */
    private String functionDesc = "FunctionDesc1";

    /** 功能图片 */
    private String functionPicture = "icon13.png";

    /** 功能路径 */
    private String functionPath = "/test/test.action";

    /** 父功能ID */
    private Long functionParent = 1L;

    /** 功能排序 */
    private String functionOrder = "010500";

    /** 功能状态 */
    private Boolean functionStatus = true;

    /**
     * 返回所有值对象列表
     */
    @Test
    public void getAllFunctions() {
        List<FunctionVO> allFunctionVOs = functionService.findAllFunctionVOs();
        Assert.assertNotNull(allFunctionVOs);
        for (FunctionVO functionVO : allFunctionVOs) {
            LOGGER.debug(functionVO);
        }
    }

    /**
     * 先增加，再查找，再删除，再查找
     */
    @Test
    public void operateFunction() {
        // 先新增一个对象
        Function function = new Function(functionName, functionDesc,
                functionPicture, functionPath, functionParent, functionOrder,
                functionStatus);

        // 1. 保存
        Long functionId = functionService.saveFunction(function);
        Function functionNew = functionService.loadFunction(functionId);

        // 更新属性
        String newFunctionName = "FunctionName2";
        String newFunctionDesc = "FunctionDesc2";
        functionNew.setFunctionName(newFunctionName);
        functionNew.setFunctionDesc(newFunctionDesc);
        // 2. 更新
        functionService.updateFunction(functionNew);

        // 3. 查找
        // 3.1 根据角色名称查找角色
        Function functionDB = functionService
                .findFunctionByFunctionName(newFunctionName);
        Assert.assertNotNull(functionDB);
        Assert.assertEquals(newFunctionName, functionDB.getFunctionName());
        Assert.assertEquals(newFunctionDesc, functionDB.getFunctionDesc());

        // 4. 移除
        functionService.deleteFunction(functionNew.getId());

        // 3.2 通过角色ID查找角色
        // 得到新增后的ID
        Long newFunctionId = functionNew.getId();
        functionDB = functionService.loadFunction(newFunctionId);
        Assert.assertNull(functionDB);
    }

    /**
     * 查找（通过功能名称查找功能）
     */
    @Test
    public void findFunctionByFunctionName() {
        Function functionDB = functionService
                .findFunctionByFunctionName(functionName);
        Assert.assertNull(functionDB);
    }

    /**
     * 查找（通过功能名称查找功能）
     */
    @Test
    public void findFunctionsByParentFunctionId() {
        List<Function> functionListDB = functionService
                .findFunctionsByParentFunctionId(functionParent);
        Assert.assertNotNull(functionListDB);
    }

}