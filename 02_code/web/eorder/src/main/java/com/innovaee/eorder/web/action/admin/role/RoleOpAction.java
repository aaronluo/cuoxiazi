/***********************************************
 * Filename		: RoleOpAction.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.web.action.admin.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

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

/**   
* @Title: RoleOpAction 
* @Description: 角色操作Action（增加和修改）
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class RoleOpAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String roleId;
	private String roleName;
	private String roleDesc;
	private String[] roleIds;

	private List<RoleVO> rolevos;

	@Resource
	private RoleService roleService;

	@Resource
	private UserRoleService userRoleService;

	@Resource
	private RoleFunctionService roleFunctionService;

	private List<Function> myFunctions = new ArrayList<Function>();

	private List<Function> leftFunctions = new ArrayList<Function>();

	private String myFunctionsArray;

	private String leftFunctionsArray;

	private String contextPath;

	public void validate() {
		refreshData();
	}

	public void validateSave() {
		System.out.println("======validateSave======" + roleName == null);
		// 查看用户名是否已存在
		Role role = roleService.findRoleByRoleName(roleName);
		if (null != role) {
			addFieldError("roleName", "角色名称已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	public void validateUpdate() {
		System.out.println("======validateSave======" + roleName == null);
		// 查看用户名是否已存在
		Role role1 = roleService.loadRole(Integer.parseInt(roleId));
		Role role2 = roleService.findRoleByRoleName(roleName);
		// 可以找到，而且和自己的名字不同，则说明已经被占用
		if (null != role2 && role1.getRoleId() != role2.getRoleId()) {
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

	public void refreshData() {
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		rolevos = roleService.findAllRoleVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public List<RoleLinkVo> getList() {
		return list;
	}

	public void setList(List<RoleLinkVo> list) {
		this.list = list;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
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

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
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