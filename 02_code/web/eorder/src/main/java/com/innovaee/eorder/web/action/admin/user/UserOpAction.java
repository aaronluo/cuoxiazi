package com.innovaee.eorder.web.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.service.UserService;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserVO;
import com.innovaee.eorder.web.action.BaseAction;

public class UserOpAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String userId;
	private String username;
	private String password;
	private String cellphone;
	private String[] userIds;

	private List<UserVO> uservos = new ArrayList<UserVO>();

	private String message = "";

	@Resource
	private UserService userService;

	@Resource
	private UserRoleService userRoleService;

	private List<Role> myRoles = new ArrayList<Role>();

	private List<Role> leftRoles = new ArrayList<Role>();

	private String myRolesArray;

	private String leftRolesArray;

	private String contextPath;

	private String operation;

	public void validate() {

		refreshData();
	}

	public void validateSave() {
		System.out.println("======validateSave======" + username == null);
		// 查看用户名是否已存在
		User user = userService.findUserByUserName(username);
		if (null != user) {
			addFieldError("username", "用户名已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 查看手机号码是否已存在
		user = userService.findUsersByCellphone(cellphone);
		if (null != user) {
			addFieldError("cellphone", "手机号码已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	public void validateUpdate() {
		System.out.println("======validateSave======" + username == null);
		// 查看用户名是否已存在
		User user1 = userService.loadUser(Integer.parseInt(userId));
		User user2 = userService.findUserByUserName(username);
		// 可以找到，而且和自己的名字不同，则说明已经被占用
		if (null != user2 && user1.getUserId() != user2.getUserId()) {
			addFieldError("username", "用户名已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 查看手机号码是否已存在
		// 可以找到，而且和自己的手机不同，则说明已经被占用
		User user3 = userService.findUsersByCellphone(cellphone);
		if (null != user3 && !cellphone.equals(user1.getCellphone())) {
			addFieldError("cellphone", "手机号码已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	// 增加一个save方法，对应一个处理逻辑
	public String save() {
		String md5Password = "";
		User user = new User();
		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		} else {
			setSessionMessage("message", "用户名不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}

		if (null != password && !"".equals(password.trim())) {
			md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
		} else {
			setSessionMessage("message", "密码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}
		if (null != cellphone && !"".equals(cellphone.trim())) {
			user.setCellphone(cellphone);
		} else {
			setSessionMessage("message", "手机号码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}

		user.setPassword(md5Password);
		user.setLevelId(Constants.DEFAULT_LEVEL);
		user.setUserStatus(true);

		userService.saveUser(user);
		// 默认给用户添加普通用户的角色
		userRoleService.saveUserRole(user, new Role(Constants.DEFAULT_ROLE));

		setSessionMessage("message", "用户新增成功！");
		
		this.setMessage("新增成功！");
		
		this.setUserId("");
		
		// 更新页面数据
		refreshData();

		return SUCCESS;
	}

	public String update() {
		setSessionMessage("username", username);
		setSessionMessage("password", password);
		setSessionMessage("cellphone", cellphone);

		User user = null;
		if (null != userId) {
			user = userService.loadUser(Integer.parseInt(userId));
		}

		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		}
		if (null != password && !"".equals(password.trim())) {
			// 由于数据库存储的是MD5加密后的密码，所以这里要处理一下
			// 首先判断是否修改过密码，及比对一下前台传过来的密码是否和数据库中的一致，
			// 如果相同，则直接赋值后更新；
			if (password.equals(user.getPassword())) {
				user.setPassword(password);
			}
			// 如果不相同，则说明修改了密码，则需要加密后再存储
			else {
				String md5Password = Md5Util.getMD5Str(password + "{"
						+ username + "}");
				user.setPassword(md5Password);
			}
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

		setSessionMessage("message", "用户信息修改成功！");
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public void setSessionMessage(String key, String message) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(key, message);
		this.setMessage(message);
	}

	public void refreshData() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpSession session = request.getSession();
		// String message = (String) session.getAttribute("message");
		// String username = (String) session.getAttribute("username");
		// String password = (String) session.getAttribute("password");
		// String cellphone = (String) session.getAttribute("cellphone");
		// this.setMessage(message);
		// this.setUsername(username);
		// this.setPassword(password);
		// this.setCellphone(cellphone);

		this.setMenulist(MenuUtil.getRoleLinkVOList());
		uservos = userService.findAllUserVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}