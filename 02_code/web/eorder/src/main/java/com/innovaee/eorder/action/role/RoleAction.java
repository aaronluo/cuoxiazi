/***********************************************
 * Filename        : RoleAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Function;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.service.FunctionService;
import com.innovaee.eorder.service.RoleService;
import com.innovaee.eorder.support.MessageUtil;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVo;
import com.innovaee.eorder.vo.MenuLinkVo;
import com.innovaee.eorder.vo.RoleVO;

/**
 * @Title: RoleAction
 * @Description: 角色Action（查询和删除）
 *
 * @version V1.0
 */
public class RoleAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -1486847780419755300L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Role";

    /** 角色名称 */
    private String roleName;

    /** 角色描述 */
    private String roleDesc;

    /** 角色值对象列表 */
    private List<RoleVO> rolevos = new ArrayList<RoleVO>();

    /** 角色服务类对象 */
    @Resource
    private RoleService roleService;

    /** 角色服务类对象 */
    @Resource
    private FunctionService functionService;

    /** 已有的功能列表 */
    private List<Function> myFunctions = new ArrayList<Function>();

    /** 剩余的功能列表 */
    private List<Function> leftFunctions = new ArrayList<Function>();

    /** 已有的功能数组 */
    private String myFunctionsArray;

    /** 剩余的功能数组 */
    private String leftFunctionsArray;

    /**
     * 校验失败或者新增、修改成功后，刷新列表框
     */
    public void refreshList() {
        List<Long> myFunctionIdList = StringUtil.stringToLongList(
                myFunctionsArray, Constants.REGEX);
        List<Function> myFunctionList = new ArrayList<Function>();
        Function function = null;
        for (Long longValue : myFunctionIdList) {
            function = functionService.loadFunction(longValue);
            myFunctionList.add(function);
        }
        this.setMyFunctions(myFunctionList);
        List<Long> leftFunctionIdList = StringUtil.stringToLongList(
                leftFunctionsArray, Constants.REGEX);
        List<Function> leftFunctionList = new ArrayList<Function>();
        for (Long longValue : leftFunctionIdList) {
            function = functionService.loadFunction(longValue);
            leftFunctionList.add(function);
        }
        this.setLeftFunctions(leftFunctionList);
    }

    /**
     * 刷新页面数据
     */
    public void refreshPageData() {
        // 当前用户的工具栏
        List<MenuLinkVo> toolbarList = MenuUtil.getToolbarLinkVOList();

        List<MenuLinkVo> menuLink = null;
        if (null != toolbarList && 0 < toolbarList.size()) {
            // 第一个功能对应的菜单
            menuLink = MenuUtil.getMenuLinkVOList(FUNCTION_DESC);
        }

        this.setToolbarList(toolbarList);
        this.setMenuList(menuLink);
        this.setCurrentFunctionDesc(FUNCTION_DESC);
        this.setCurrentToolbar(MenuUtil.getParentFunctionDesc(FUNCTION_DESC));

        EOrderUserDetailVo userDetail = (EOrderUserDetailVo) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());
    }

    /**
     * 更新数据列表
     */
    public void refreshDataList() {
        Integer recordCount = roleService.count();
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

        List<Role> roles = null;
        if (null != pageInput) {
            if (pageInput > pageTotal) {
                pageInput = pageTotal;
                pageNow = pageTotal;
            } else if (pageInput < 1) {
                pageNow = 1;
                pageInput = 1;
            }
            roles = roleService.getRolesByPage((pageInput - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
            pageNow = pageInput;
        } else {
            roles = roleService.getRolesByPage((pageNow - 1)
                    * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }

        RoleVO rolevo = null;
        for (Role role : roles) {
            rolevo = new RoleVO();
            BeanUtils.copyProperties(role, rolevo);

            Set<Function> functions = role.getFunctions();
            List<String> functionNameList = new ArrayList<String>();
            for (Function function : functions) {
                functionNameList.add(function.getFunctionName());
            }
            rolevo.setFunctionName(functionNameList.toString());

            rolevos.add(rolevo);
        }

    }

    /**
     * 进入角色页面
     * 
     * @return
     */
    public String role() {
        // 更新列表
        refreshList();
        // 更新数据
        refreshPageData();
        // 更新数据列表
        refreshDataList();

        return SUCCESS;
    }

    /**
     * 保存角色
     * 
     * @return
     */
    public String save() {
        // 更新列表
        refreshList();
        // 更新数据
        refreshPageData();

        Role role = new Role();
        // 判断角色名称是否为空
        if (null == roleName || "".equals(roleName.trim())) {
            this.setMessage(MessageUtil.getMessage("role_name_empty"));
            return INPUT;
        } else {
            Role dbrole = roleService.findRoleByRoleName(roleName.trim());
            // 可以找到，说明已经被占用
            if (null != dbrole) {
                this.setMessage(MessageUtil.getMessage("role_name_occupy"));
                return INPUT;
            } else {
                role.setRoleName(roleName);
            }
        }

        if (null != roleDesc && !"".equals(roleDesc.trim())) {
            role.setRoleDesc(roleDesc);
        } else {
            this.setMessage(MessageUtil.getMessage("role_desc_empty"));
            return INPUT;
        }
        

        // 校验功能列表不能为空
        if (null == myFunctionsArray || "".equals(myFunctionsArray)) {
            this.setMessage(MessageUtil.getMessage("function_list_empty"));
            return INPUT;
        }

        role.setRoleStatus(true);

        // 设置角色列表
        Set<Function> myFunctionSet = new HashSet<Function>();
        myFunctionSet.addAll(this.getMyFunctions());
        role.setFunctions(myFunctionSet);

        Long newId = roleService.saveRole(role);
        if (newId != 0) {
            this.setMessage(MessageUtil.getMessage("add_success"));
            this.setRoleName("");
            this.setRoleDesc("");
            renewPage();
        } else {
            this.setMessage(MessageUtil.getMessage("add_failure"));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * 新增页面
     * 
     * @return
     */
    public String add() {
        // 更新页面数据
        refreshPageData();

        List<Function> leftFunctionList = roleService
                .findLeftFunctionsByRoleId(Constants.MAX_ID);
        leftFunctions.addAll(leftFunctionList);
        return SUCCESS;
    }
    

    
    /**
     * 
     */
    public void renewPage() {
        // 更新权限列表
        refreshList();
        // 更新页面数据
        refreshPageData();

        this.setMyFunctions(new ArrayList<Function>());
        List<Function> leftFunctionList = roleService
                .findLeftFunctionsByRoleId(Constants.MAX_ID);
        this.setLeftFunctions(leftFunctionList);
    }

    /**
     * 更新角色
     * 
     * @return
     */
    public String update() {
        // 更新列表
        refreshList();
        // 更新页面数据
        refreshPageData();

        // 查看用户名是否已存在
        Role role1 = roleService.loadRole(Long.parseLong(id));
        Role role2 = roleService.findRoleByRoleName(roleName);
        // 可以找到，而且和自己的名字不同，则说明已经被占用
        if (null != role2 && !role1.getId().equals(role2.getId())) {
            this.setMessage(MessageUtil.getMessage("role_name_occupy"));
            return INPUT;
        }

        // 校验功能列表不能为空
        if (null == myFunctionsArray || "".equals(myFunctionsArray)) {
            this.setMessage(MessageUtil.getMessage("function_list_empty"));
            return INPUT;
        }

        Role role = new Role();
        if (null != id) {
            role = roleService.loadRole(Long.parseLong(id));
        }

        if (null != roleName && !"".equals(roleName.trim())) {
            role.setRoleName(roleName);
        } else {
            this.setMessage(MessageUtil.getMessage("role_name_empty"));
            // 更新页面数据
            refreshPageData();

            return INPUT;
        }
        if (null != roleDesc && !"".equals(roleDesc.trim())) {
            role.setRoleDesc(roleDesc);
        } else {
            this.setMessage(MessageUtil.getMessage("role_desc_empty"));
            // 更新页面数据
            refreshPageData();

            return INPUT;
        }

        // 更新角色信息
        Set<Function> myFunctionSet = new HashSet<Function>();
        myFunctionSet.addAll(this.getMyFunctions());
        role.setFunctions(myFunctionSet);

        roleService.updateRole(role);
        this.setMessage(MessageUtil.getMessage("update_success"));
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 加载单个角色对象
     * 
     * @return
     */
    public String edit() {
        if (null != id) {
            Role role = roleService.loadRole(Long.parseLong(id));
            roleName = role.getRoleName();
            roleDesc = role.getRoleDesc();

            // 加载用户角色信息
            Set<Function> functionSet = role.getFunctions();
            myFunctions.addAll(functionSet);

            List<Function> leftFunctionList = roleService
                    .findLeftFunctionsByRoleId(Long.parseLong(id));
            leftFunctions.addAll(leftFunctionList);
        }
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 删除角色
     * 
     * @return
     */
    public String remove() {
        refreshPageData();

        if (null != id) {
            // 先判断用户角色关联关系，如果此角色已授权给某个用户，则不能删除
            Role role = roleService.loadRole(Long.parseLong(id));
            Set<User> users = role.getUsers();
            if (null != users && 0 < users.size()) {
                this.setMessage(MessageUtil.getMessage("role_using"));
                // 更新页面数据
                refreshPageData();
                // 更新数据列表
                refreshDataList();
                return INPUT;
            }
        }

        if (null != id) {
            roleService.deleteRole(Long.parseLong(id));
        }
        this.setMessage(MessageUtil.getMessage("delete_success"));

        // 更新数据列表
        refreshDataList();

        return SUCCESS;
    }

    public List<RoleVO> getRolevos() {
        return rolevos;
    }

    public void setRolevos(List<RoleVO> rolevos) {
        this.rolevos = rolevos;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Function> getMyFunctions() {
        return myFunctions;
    }

    public void setMyFunctions(List<Function> myFunctions) {
        this.myFunctions = myFunctions;
    }

    public List<Function> getLeftFunctions() {
        return leftFunctions;
    }

    public void setLeftFunctions(List<Function> leftFunctions) {
        this.leftFunctions = leftFunctions;
    }

    public String getMyFunctionsArray() {
        return myFunctionsArray;
    }

    public void setMyFunctionsArray(String myFunctionsArray) {
        this.myFunctionsArray = myFunctionsArray;
    }

    public String getLeftFunctionsArray() {
        return leftFunctionsArray;
    }

    public void setLeftFunctionsArray(String leftFunctionsArray) {
        this.leftFunctionsArray = leftFunctionsArray;
    }

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

}