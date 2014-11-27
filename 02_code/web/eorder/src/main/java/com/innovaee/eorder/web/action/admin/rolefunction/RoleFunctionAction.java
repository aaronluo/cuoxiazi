package com.innovaee.eorder.web.action.admin.rolefunction;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.service.RoleService;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

public class RoleFunctionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(RoleFunctionAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String roleName;
	
	private List<Role> roles = new ArrayList<Role>();

	private List<Function> myFunctions = new ArrayList<Function>();

	private List<Function> leftFunctions = new ArrayList<Function>();
	
	private String myFunctionsArray;

	private String leftFunctionsArray;

	@Resource
	private RoleFunctionService roleFunctionService;

	@Resource
	private RoleService roleService;
	
	@Resource
	private FunctionService functionService;

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doRoleFunction() {
		logger.debug("enter doRoleFunction() method");

		roles = roleService.findAllRoles();
		myFunctions = functionService.findAllFunctions();
		return SUCCESS;
	}

	public String doLoad() {
		roles = roleService.findAllRoles();
		myFunctions = roleFunctionService.findFunctionsByRoleName(roleName);
		leftFunctions = roleFunctionService.findLeftFunctionsByRoleName(roleName);
		return SUCCESS;
	}

	public String doList() {
		// books = dao.getBooks();
		//roleFunctions = roleFunctionService.findAllRoleFunctions();

		return SUCCESS;
	}

	public String doStore() {
		//roleFunctionService.saveRoleFunction(roleFunction);
		return SUCCESS;
	}
	
	public String doUpdate() {
//		ActionContext context = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
//
//		String[] myFunctionsName = request.getParameterValues("myFunctions");
//		for (int i = 0; i < myFunctionsName.length; i++) {
//			System.out.println("拥有功能： " + myFunctionsName[i] + "/t");
//		}
//		String[] leftFunctionsName = request.getParameterValues("leftFunctions");
//		for (int i = 0; i < leftFunctionsName.length; i++) {
//			System.out.println("未获功能：" + leftFunctionsName[i] + "/t");
//		}
		
		roleFunctionService.updateRoleFunction(roleName, myFunctionsArray);
		roles = roleService.findAllRoles();
		myFunctions = roleFunctionService.findFunctionsByRoleName(roleName);
		leftFunctions = roleFunctionService.findLeftFunctionsByRoleName(roleName);
		return SUCCESS;
	}

	public String doRemove() {
//		if (null != roleFunction) {
//			roleFunctionService.removeRoleFunction(roleFunctionName);
//		} else {
//			roleFunctionService.removeRoleFunctions(roleFunctionNames);
//		}
		
	
		
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

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

}