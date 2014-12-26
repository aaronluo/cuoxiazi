/***********************************************
 * Filename		: FunctionAction.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.web.action.admin.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.FunctionVO;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

/**   
* @Title: FunctionAction 
* @Description: 功能Action（查询和删除）
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class FunctionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FunctionAction.class);

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String functionId;
	private String functionName;
	private String functionDesc;
	private String functionPath;
	private Integer functionParent;
	private String functionOrder;
	private List<FunctionVO> functionvos;

	@Resource
	private FunctionService functionService;

	@Resource
	private RoleFunctionService roleFunctionService;

	private String contextPath;

	@SuppressWarnings("unchecked")
	public void refreshData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("menulist", MenuUtil.getRoleLinkVOList());
		List<RoleLinkVo> sessionMenulist= (List<RoleLinkVo>)session.getAttribute("menulist");
		this.setMenulist(sessionMenulist);
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		functionvos = functionService.findAllFunctionVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public String doFunction() {
		logger.debug("enter doFunction() method");

		this.setMessage("");
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doLoad() {
		if (null != functionId && !"".equals(functionId.trim())) {
			Function function = functionService.loadFunction(Integer
					.parseInt(functionId));

			functionName = function.getFunctionName();
			functionDesc = function.getFunctionDesc();
			functionPath = function.getFunctionPath();
			functionParent = function.getFunctionParent();
			functionOrder = function.getFunctionOrder();
		}

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String doList() {
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public void validateRemove() {
		System.out.println("======validateSave======" + functionName == null);
		if (null != functionId) {
			// 先判断角色功能关联关系，如果此功能已授权给某个角色，则不能删除
			List<RoleFunction> myRoleFunctions = roleFunctionService
					.findRoleFunctionsByFunctionId(Integer.parseInt(functionId));
			if (null != myRoleFunctions && 0 < myRoleFunctions.size()) {
				addFieldError("functionName", "该功能已被分配给某个角色，不能删除！");

				// 清空删除时传入的Id，防止返回后页面取到
				this.setFunctionId("");
				
				// 更新页面数据
				refreshData();
			}
		}
	}

	public String remove() {
		if (null != functionId) {
			functionService.removeFunction(Integer.parseInt(functionId));
		}

		this.setMessage("删除成功！");

		// 清空删除时传入的Id，防止返回后页面取到
		this.setFunctionId("");
		
		// 更新页面数据
		refreshData();
		
		return SUCCESS;
	}

	public String doUserInfo() {
		logger.debug("enter doUserInfo() method");

		// 更新页面数据
		refreshData();
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

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
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

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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

	public Integer getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(Integer functionParent) {
		this.functionParent = functionParent;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

}