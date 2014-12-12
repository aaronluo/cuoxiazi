package com.innovaee.eorder.web.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.service.UserService;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserVO;
import com.innovaee.eorder.web.action.BaseAction;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String userId;
	private String username;
	private String password;
	private String cellphone;
	private String[] userIds;

	private List<UserVO> uservos = new ArrayList<UserVO>();

	@Resource
	private UserService userService;

	@Resource
	private UserRoleService userRoleService;

	private List<Role> myRoles = new ArrayList<Role>();

	private List<Role> leftRoles = new ArrayList<Role>();

	private String myRolesArray;

	private String leftRolesArray;

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doUser() {
		logger.debug("enter doUser() method");
		uservos = userService.findAllUserVOs();
		return SUCCESS;
	}

	public String doLoad() {
		if (null != userId) {
			User user = userService.loadUser(Integer.parseInt(userId));
			username = user.getUsername();
			password = user.getPassword();
			cellphone = user.getCellphone();

			// 加载用户角色信息
			myRoles = userRoleService.findRolesByUserId(Integer
					.parseInt(userId));
			if (null == myRoles || 0 == myRoles.size()) {
				myRoles.add(new Role(0, " "));
			}
			leftRoles = userRoleService.findLeftRolesByUserId(Integer
					.parseInt(userId));
			if (null == leftRoles || 0 == leftRoles.size()) {
				leftRoles.add(new Role(0, " "));
			}
		}
		uservos = userService.findAllUserVOs();
		return SUCCESS;
	}

	public String doList() {
		uservos = userService.findAllUserVOs();
		return SUCCESS;
	}

	public String doStore() {
		String md5Password = "";
		User user = new User();
		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		}
		if (null != password && !"".equals(password.trim())) {
			md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
		}
		if (null != cellphone && !"".equals(cellphone.trim())) {
			user.setCellphone(cellphone);
		}

		user.setPassword(md5Password);
		user.setLevelId(Constants.DEFAULT_LEVEL);
		user.setUserStatus(true);

		userService.saveUser(user);
		// 默认给用户添加普通用户的角色
		userRoleService.saveUserRole(user, new Role(Constants.DEFAULT_ROLE));

		uservos = userService.findAllUserVOs();
		return SUCCESS;
	}

	public String doUpdate() {
		User user = null;
		if (null != userId) {
			user = userService.loadUser(Integer.parseInt(userId));
		}

		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		}
		if (null != password && !"".equals(password.trim())) {
			user.setPassword(password);
		}
		if (null != cellphone && !"".equals(cellphone.trim())) {
			user.setCellphone(cellphone);
		}
		userService.updateUser(user);

		// 更新角色信息
		userRoleService.updateUserRole(Integer.parseInt(userId), myRolesArray);
		userId = "";
		username = "";
		password = "";
		cellphone = "";
		uservos = userService.findAllUserVOs();
		return SUCCESS;
	}

	public String doRemove() {
		if (null != userId) {
			userService.removeUser(Integer.parseInt(userId));
		} else {
			userService.removeUsers(userIds);
		}
		return SUCCESS;
	}

	public String doUserInfo() {
		logger.debug("enter doUserInfo() method");
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

	public List<UserVO> getUservos() {
		return uservos;
	}

	public void setUservos(List<UserVO> uservos) {
		this.uservos = uservos;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<Role> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(List<Role> myRoles) {
		this.myRoles = myRoles;
	}

	public List<Role> getLeftRoles() {
		return leftRoles;
	}

	public void setLeftRoles(List<Role> leftRoles) {
		this.leftRoles = leftRoles;
	}

	public String getMyRolesArray() {
		return myRolesArray;
	}

	public void setMyRolesArray(String myRolesArray) {
		this.myRolesArray = myRolesArray;
	}

	public String getLeftRolesArray() {
		return leftRolesArray;
	}

	public void setLeftRolesArray(String leftRolesArray) {
		this.leftRolesArray = leftRolesArray;
	}

}