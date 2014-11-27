package com.innovaee.eorder.web.action.admin.role;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.service.RoleService;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String roleName;
	private String[] roleNames;
	private Role role = new Role();
	private List<Role> roles = new ArrayList<Role>();

	@Resource
	private RoleService roleService;

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doRole() {
		logger.debug("enter doRole() method");

		roles = roleService.findAllRoles();

		return SUCCESS;
	}

	public String doLoad() {
		role = roleService.findRolesByRoleName(roleName);
		return SUCCESS;
	}

	public String doList() {
		// books = dao.getBooks();
		roles = roleService.findAllRoles();
		return SUCCESS;
	}

	public String doStore() {
		// dao.storeBook(role);
		// Role role2 = new Role("abcd", "abcd", "abcd", "abcd", "abcd", "abcd", false);
		roleService.saveRole(role);
		// String result = roleService.storeRole(role);
		// System.out.println("store result: " + result);
		return SUCCESS;
	}

	public String doUpdate() {
		// dao.storeBook(role);
		// Role role2 = new Role("abcd", "abcd", "abcd", "abcd", "abcd", "abcd", false);
		roleService.updateRole(role);
		// String result = roleService.storeRole(role);
		// System.out.println("store result: " + result);
		return SUCCESS;
	}

	public String doRemove() {
		if (null != roleName) {
			roleService.removeRole(roleName);
		} else {
			roleService.removeRoles(roleNames);
		}
		return SUCCESS;
	}

	public String doRoleInfo() {
		logger.debug("enter doRoleInfo() method");
		return SUCCESS;
	}

	public String doRight() {
		logger.debug("enter doRight() method");
		return SUCCESS;
	}

	public String doBottom() {
		logger.debug("enter doBottom() method");
		return SUCCESS;
	}

	public String doLeft() {

		List<RoleLinkVo> subList = new ArrayList<RoleLinkVo>();
		RoleLinkVo linkVo = new RoleLinkVo();
		linkVo = new RoleLinkVo();
		linkVo.setName("Reset Password");
		linkVo.setFlag("0");
		linkVo.setLink("/base/config.action");
		subList.add(linkVo);

		linkVo = new RoleLinkVo();
		linkVo.setName("Security Question");
		linkVo.setFlag("0");
		linkVo.setLink("/mail/list.action");
		subList.add(linkVo);

		linkVo = new RoleLinkVo();
		linkVo.setName("Basic Setup");
		linkVo.setFlag("1");
		linkVo.setList(subList);
		list.add(linkVo);

		linkVo = new RoleLinkVo();
		linkVo.setName("Admin");
		linkVo.setFlag("1");
		list.add(linkVo);

		ServletActionContext.getRequest().setAttribute("permission", list);
		return SUCCESS;
	}

	public ResetPasswordVo getResetPasswordVo() {
		return resetPasswordVo;
	}

	public void setResetPasswordVo(ResetPasswordVo resetPasswordVo) {
		this.resetPasswordVo = resetPasswordVo;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public String[] getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}