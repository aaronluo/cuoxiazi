/***********************************************
 * Filename        : RoleOpAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.web.action.admin.role;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.service.RoleService;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.RoleVO;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: RoleOpAction
 * @Description: 角色操作Action（增加和修改）
 *
 * @version V1.0
 */
public class RoleOpAction extends BaseAction {

	/** 角色ID */
	private String roleId;

	/** 角色名称 */
	private String roleName;

	/** 角色描述 */
	private String roleDesc;

	/** 角色值对象列表 */
	private List<RoleVO> rolevos = new ArrayList<RoleVO>();

	/** 角色服务类对象 */
	@Resource
	private RoleService roleService;

	/** 用户角色服务类 */
	@Resource
	private UserRoleService userRoleService;

	/** 角色功能服务类 */
	@Resource
	private RoleFunctionService roleFunctionService;

	/** 已有的功能列表 */
	private List<Function> myFunctions = new ArrayList<Function>();

	/** 剩余的功能列表 */
	private List<Function> leftFunctions = new ArrayList<Function>();

	/** 已有的功能数组 */
	private String myFunctionsArray;

	/** 剩余的功能数组 */
	private String leftFunctionsArray;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		refreshData();
	}

	/**
	 * 保存前的校验
	 */
	public void validateSave() {
		// 查看用户名是否已存在
		Role role = roleService.findRoleByRoleName(roleName);
		if (null != role) {
			addFieldError("roleName", "角色名称已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	/**
	 * 更新前的校验
	 */
	public void validateUpdate() {
		// 查看用户名是否已存在
		Role role1 = roleService.loadRole(Integer.parseInt(roleId));
		Role role2 = roleService.findRoleByRoleName(roleName);
		// 可以找到，而且和自己的名字不同，则说明已经被占用
		if (null != role2 && role1.getRoleId().equals(role2.getRoleId())) {
			addFieldError("roleName", "角色名称已被占用！");
			// 更新页面数据
			refreshData();
		}

	}

	/**
	 * 清空输入框数据
	 */
	private void clean() {
		this.setRoleId("");
		this.setRoleName("");
		this.setRoleDesc("");
	}

	/**
	 * 保存角色
	 * 
	 * @return
	 */
	public String save() {
		Role role = new Role();
		if (null != roleName && !"".equals(roleName.trim())) {
			role.setRoleName(roleName);
		} else {
			addFieldError("roleName", "角色名称不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}
		if (null != roleDesc && !"".equals(roleDesc.trim())) {
			role.setRoleDesc(roleDesc);
		} else {
			addFieldError("roleDesc", "角色描述不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		role.setRoleStatus(true);

		roleService.saveRole(role);

		this.setMessage("新增成功！");

		// 清空输入框数据
		clean();

		refreshData();
		return SUCCESS;
	}

	/**
	 * 更新角色
	 * 
	 * @return
	 */
	public String update() {
		Role role = new Role();
		if (null != roleId) {
			role = roleService.loadRole(Integer.parseInt(roleId));
		}

		if (null != roleName && !"".equals(roleName.trim())) {
			role.setRoleName(roleName);
		} else {
			addFieldError("roleName", "角色名称不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}
		if (null != roleDesc && !"".equals(roleDesc.trim())) {
			role.setRoleDesc(roleDesc);
		} else {
			addFieldError("roleDesc", "角色描述不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}
		roleService.updateRole(role);

		// 更新角色信息
		roleFunctionService.updateRoleFunction(Integer.parseInt(roleId),
				myFunctionsArray);
		this.setMessage("修改成功");
		// 清空输入框数据
		clean();
		refreshData();
		return SUCCESS;
	}

	/**
	 * 刷新页面数据
	 */
	public void refreshData() {
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		rolevos = roleService.findAllRoleVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
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

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

}