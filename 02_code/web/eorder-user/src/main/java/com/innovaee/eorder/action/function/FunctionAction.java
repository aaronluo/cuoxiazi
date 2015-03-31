/***********************************************
 * Filename        : FunctionAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.service.FunctionService;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.FunctionVO;
import com.innovaee.eorder.vo.MenuLinkVO;

/**
 * @Title: FunctionAction
 * @Description: 功能Action（查询和删除）
 *
 * @version V1.0
 */
public class FunctionAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Function";

    /** 功能名称 */
    private String functionName;

    /** 功能描述 */
    private String functionDesc;

    /** 功能路径 */
    private String functionPath;

    /** 父功能ID */
    private String functionParent;

    /** 父功能ID */
    private List<FunctionVO> functionParentList;

    /** 功能值对象列表 */
    private List<FunctionVO> functionvos = new ArrayList<FunctionVO>();

    /** 功能服务类对象 */
    @Resource
    private FunctionService functionService;

    /**
     * 进入功能页面
     * 
     * @return
     */
    public String function() {

        refreshDataList();

        // 更新页面数据
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 加载单个功能信息
     * 
     * @return
     */
    public String edit() {
        if (null != id && !"".equals(id.trim())) {
            Function function = functionService
                    .loadFunction(Long.parseLong(id));

            functionName = function.getFunctionName();
            functionDesc = function.getFunctionDesc();
            functionPath = function.getFunctionPath();
            functionParent = function.getFunctionParent().toString();
        }

        // 更新页面数据
        renewPage();

        return SUCCESS;
    }

    /**
     * 刷新页面数据
     */
    public void refreshPageData() {
        // 当前用户的工具栏
        List<MenuLinkVO> toolbarList = MenuUtil.getToolbarLinkVOList();

        List<MenuLinkVO> menuLink = null;
        if (null != toolbarList && 0 < toolbarList.size()) {
            // 第一个功能对应的菜单
            menuLink = MenuUtil.getMenuLinkVOList(FUNCTION_DESC);
        }

        this.setToolbarList(toolbarList);
        this.setMenuList(menuLink);
        this.setCurrentFunctionDesc(FUNCTION_DESC);
        this.setCurrentToolbar(MenuUtil.getParentFunctionDesc(FUNCTION_DESC));

        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());
    }

    /**
     * 刷新列表
     */
    public void refreshDataList() {
        Integer recordCount = functionService.count();
        this.setCount(recordCount);
        int pageTotal = 1;
        if (0 == recordCount % Constants.PAGE_SIZE) {
            pageTotal = recordCount / Constants.PAGE_SIZE;
        } else {
            pageTotal = recordCount / Constants.PAGE_SIZE + 1;
        }
        this.setPageTotal(pageTotal);

        // 处理用户点击【上一页】和【下一页】边界情况
        if (pageNow > pageTotal) {
            pageNow = pageTotal;
            pageInput = pageNow;
        } else if (pageNow < 1) {
            pageNow = 1;
            pageInput = 1;
        }

        List<Function> functions = null;
        if (null != pageInput) {
            if (pageInput > pageTotal) {
                pageInput = pageTotal;
                pageNow = pageTotal;
            } else if (pageInput < 1) {
                pageNow = 1;
                pageInput = 1;
            }
            functions = functionService.getFunctionsByPage((pageInput - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
            pageNow = pageInput;
        } else {
            functions = functionService.getFunctionsByPage((pageNow - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }

        FunctionVO functionvo = null;
        Function parentFunction = null;
        for (Function function : functions) {
            functionvo = new FunctionVO();
            BeanUtils.copyProperties(function, functionvo);
            parentFunction = functionService.loadFunction(function
                    .getFunctionParent());
            if (null != parentFunction) {
                functionvo.setFunctionParentName(parentFunction
                        .getFunctionName());
            }
            functionvos.add(functionvo);
        }
    }

    /**
     * 保存功能
     * 
     * @return
     */
    public String save() {
        // 更新页面数据
        renewPage();

        // 查看用户名是否已存在
        Function dbfunction = functionService
                .findFunctionByFunctionName(functionName);
        if (null != dbfunction) {
            this.setMessage(MessageUtil.getMessage("function_name_occupy"));
            renewPage();
            return INPUT;
        }

        Function function = new Function();
        if (null != functionName && !"".equals(functionName.trim())) {
            function.setFunctionName(functionName);
        } else {
            this.setMessage(MessageUtil.getMessage("function_name_empty"));
            return INPUT;
        }

        if (null != functionDesc && !"".equals(functionDesc.trim())) {
            function.setFunctionDesc(functionDesc);
        } else {
            this.setMessage(MessageUtil.getMessage("function_desc_empty"));
            return INPUT;
        }

        if (null != functionPath && !"".equals(functionPath.trim())) {
            function.setFunctionPath(functionPath);
        } else {
            this.setMessage(MessageUtil.getMessage("function_path_empty"));
            return SUCCESS;
        }

        // 检查父节点
        if (null != functionParent && !"".equals(functionParent.trim())) {
            function.setFunctionParent(Long.parseLong(functionParent));
        } else {
            function.setFunctionParent(Constants.ROOT_FUNCTION);
        }

        function.setFunctionStatus(true);

        // 新增成功
        Long newId = functionService.saveFunction(function);
        if (newId != 0) {
            this.setMessage(MessageUtil.getMessage("add_success"));
            this.setFunctionName("");
            this.setFunctionDesc("");
            this.setFunctionPath("");
            this.setFunctionParent("0");
            renewPage();
        } else {
            this.setMessage(MessageUtil.getMessage("add_failure"));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * 更新功能
     * 
     * @return
     */
    public String update() {
        // 更新页面数据
        renewPage();

        // 查看用户名是否已存在
        Function function1 = functionService.loadFunction(Long.parseLong(id));
        Function function2 = functionService
                .findFunctionByFunctionName(functionName);
        // 可以找到，而且和自己的名字不同，则说明已经被占用
        if (null != function2 && !function1.getId().equals(function2.getId())) {
            this.setMessage(MessageUtil.getMessage("function_name_occupy"));
            return INPUT;
        }

        Function function = null;
        if (null != id) {
            function = functionService.loadFunction(Long.parseLong(id));
        }

        if (null != functionName && !"".equals(functionName.trim())) {
            function.setFunctionName(functionName);
        } else {
            this.setMessage(MessageUtil.getMessage("function_name_empty"));
            return INPUT;
        }

        if (null != functionDesc && !"".equals(functionDesc.trim())) {
            function.setFunctionDesc(functionDesc);
        } else {
            this.setMessage(MessageUtil.getMessage("function_desc_empty"));
            return INPUT;
        }

        if (null != functionPath && !"".equals(functionPath.trim())) {
            function.setFunctionPath(functionPath);
        } else {
            this.setMessage(MessageUtil.getMessage("function_path_empty"));
            return INPUT;
        }

        // 检查父节点
        if (null != functionParent && !"".equals(functionParent.trim())) {
            function.setFunctionParent(Long.parseLong(functionParent));
        } else {
            function.setFunctionParent(Constants.ROOT_FUNCTION);
        }

        functionService.updateFunction(function);
        this.setMessage(MessageUtil.getMessage("update_success"));
        return SUCCESS;
    }

    /**
     * 新增页面
     * 
     * @return
     */
    public String add() {
        // 更新页面数据
        renewPage();
        return SUCCESS;
    }

    /**
     * 
     */
    public void renewPage() {
        List<FunctionVO> functionParentVOList = new ArrayList<FunctionVO>();
        // 更新权限列表
        FunctionVO functionVO = null;
        FunctionVO rootFunctionVO = new FunctionVO();
        rootFunctionVO.setStringId("0");
        rootFunctionVO.setFunctionName(MessageUtil.getMessage("function_root"));
        functionParentVOList.add(rootFunctionVO);
        List<Function> functionList = functionService.findRootFunctionList();
        for (Function function : functionList) {
            functionVO = new FunctionVO();
            BeanUtils.copyProperties(function, functionVO);
            functionVO.setStringId(function.getId().toString());
            functionParentVOList.add(functionVO);
        }

        this.setFunctionParentList(functionParentVOList);

        // 更新页面数据
        refreshPageData();
    }

    /**
     * 删除功能
     * 
     * @return
     */
    public String remove() {
        // 更新页面数据
        refreshPageData();

        if (null != id) {
            // 先判断角色功能关联关系，如果此功能已授权给某个角色，则不能删除
            Function function = functionService
                    .loadFunction(Long.parseLong(id));
            Set<Role> roles = function.getRoles();
            if (null != roles && 0 < roles.size()) {
                this.setMessage(MessageUtil.getMessage("function_using"));

                // 更新数据
                refreshPageData();
                // 更新数据列表
                refreshDataList();

                return INPUT;
            }
        }

        if (null != id) {
            functionService.deleteFunction(Long.parseLong(id));
        }

        this.setMessage(MessageUtil.getMessage("delete_success"));

        // 更新记录列表
        refreshDataList();

        return SUCCESS;
    }

    public List<FunctionVO> getFunctionvos() {
        return functionvos;
    }

    public void setFunctionvos(List<FunctionVO> functionvos) {
        this.functionvos = functionvos;
    }

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public String getFunctionParent() {
        return functionParent;
    }

    public void setFunctionParent(String functionParent) {
        this.functionParent = functionParent;
    }

    public List<FunctionVO> getFunctionParentList() {
        return functionParentList;
    }

    public void setFunctionParentList(List<FunctionVO> functionParentList) {
        this.functionParentList = functionParentList;
    }

}