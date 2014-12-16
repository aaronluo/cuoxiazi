package com.innovaee.eorder.web.action.admin.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.service.RoleService;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.RoleVO;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String roleId;
	private String roleName;
	private String roleDesc;
	private String[] roleIds;

	private List<RoleVO> rolevos = new ArrayList<RoleVO>();

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

	public void refreshData() {
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		rolevos = roleService.findAllRoleVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public String login() {
		refreshData();
		return SUCCESS;
	}

	public String doRole() {
		this.setMessage("");
		refreshData();
		return SUCCESS;
	}

	public String doLoad() {
		if (null != roleId) {
			Role role = roleService.loadRole(Integer.parseInt(roleId));
			roleName = role.getRoleName();
			roleDesc = role.getRoleDesc();

			// 加载用户角色信息
			myFunctions = roleFunctionService.findFunctionsByRoleId(Integer
					.parseInt(roleId));
//			if (null == myFunctions || 0 == myFunctions.size()) {
//				myFunctions.add(new Function(0, " "));
//			}
			leftFunctions = roleFunctionService
					.findLeftFunctionsByRoleId(Integer.parseInt(roleId));
//			if (null == leftFunctions || 0 == leftFunctions.size()) {
//				leftFunctions.add(new Function(0, " "));
//			}
		}
		refreshData();
		return SUCCESS;
	}

	public String doList() {
		refreshData();
		return SUCCESS;
	}

	public String doStore() {
		Role role = new Role();
		if (null != roleName && !"".equals(roleName.trim())) {
			role.setRoleName(roleName);
		}
		if (null != roleDesc && !"".equals(roleDesc.trim())) {
			role.setRoleDesc(roleDesc);
		}

		role.setRoleStatus(true);

		roleService.saveRole(role);

		refreshData();
		return SUCCESS;
	}

	public String doUpdate() {
		Role role = new Role();
		if (null != roleId) {
			role = roleService.loadRole(Integer.parseInt(roleId));
		}

		if (null != roleName && !"".equals(roleName.trim())) {
			role.setRoleName(roleName);
		}
		if (null != roleDesc && !"".equals(roleDesc.trim())) {
			role.setRoleDesc(roleDesc);
		}
		roleService.updateRole(role);

		// 更新角色信息
		roleFunctionService.updateRoleFunction(Integer.parseInt(roleId),
				myFunctionsArray);
		roleId = "";
		roleName = "";
		roleDesc = "";
		refreshData();
		return SUCCESS;
	}
	public void validateRemove() {
		if (null != roleId) {
			// 先判断用户角色关联关系，如果此角色已授权给某个用户，则不能删除
			List<UserRole> myUserRoles = userRoleService
					.findUserRolesByRoleId(Integer.parseInt(roleId));
			if (null != myUserRoles && 0 < myUserRoles.size()) {
				addFieldError("roleName", "该角色已被分配给某个用户，不能删除！");
				// 更新页面数据
				refreshData();
			}

		}
	}
	
	public String remove() {
		if (null != roleId) {
			roleService.removeRole(Integer.parseInt(roleId));
		}

		this.setMessage("删除成功！");
		
		refreshData();

		return SUCCESS;
	}
	public String doRoleInfo() {
		refreshData();
		return SUCCESS;
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