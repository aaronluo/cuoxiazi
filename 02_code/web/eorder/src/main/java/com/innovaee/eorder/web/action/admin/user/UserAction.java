package com.innovaee.eorder.web.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.UserService;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String userName;
	private String[] userNames;
	private User user = new User();
	private List<User> users = new ArrayList<User>();

	@Resource
	private UserService userService;

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doUser() {
		logger.debug("enter doUser() method");

		users = userService.findAllUsers();

		return SUCCESS;
	}

	public String doLoad() {
		user = userService.findUsersByUserName(userName);
		return SUCCESS;
	}

	public String doList() {
		// books = dao.getBooks();
		users = userService.findAllUsers();
		return SUCCESS;
	}

	public String doStore() {
		String userName = user.getUserName();
		String password = user.getUserPassword();
		String md5Password = Md5Util.getMD5Str(password + "{" + userName + "}");
		user.setUserPassword(md5Password);

		userService.saveUser(user);
		return SUCCESS;
	}

	public String doUpdate() {
		String userName = user.getUserName();
		String password = user.getUserPassword();
		String md5Password = Md5Util.getMD5Str(password + "{" + userName + "}");
		user.setUserPassword(md5Password);
		userService.updateUser(user);
		return SUCCESS;
	}

	public String doRemove() {
		if (null != userName) {
			userService.removeUser(userName);
		} else {
			userService.removeUsers(userNames);
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String[] getUserNames() {
		return userNames;
	}

	public void setUserNames(String[] userNames) {
		this.userNames = userNames;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}