/***********************************************
 * Filename        : FunctionAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.web.action.admin.function;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
 *
 * @version V1.0
 */
public class FunctionAction extends BaseAction {

	/** 功能ID */
	private String functionId;

	/** 功能名称 */
	private String functionName;

	/** 功能描述 */
	private String functionDesc;

	/** 功能路径 */
	private String functionPath;

	/** 父功能ID */
	private Integer functionParent;

	/** 功能排序 */
	private String functionOrder;

	/** 功能值对象列表 */
	private List<FunctionVO> functionvos;

	/** 功能服务类对象 */
	@Resource
	private FunctionService functionService;

	/** 角色功能服务类对象 */
	@Resource
	private RoleFunctionService roleFunctionService;

	/**
	 * 刷新页面数据
	 */
	@SuppressWarnings("unchecked")
	public void refreshData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("menulist", MenuUtil.getRoleLinkVOList());
		List<RoleLinkVo> sessionMenulist = (List<RoleLinkVo>) session
				.getAttribute("menulist");
		this.setMenulist(sessionMenulist);
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		functionvos = functionService.findAllFunctionVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	/**
	 * 进入功能页面
	 * 
	 * @return
	 */
	public String doFunction() {
		this.setMessage("");
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	/**
	 * 加载单个功能信息
	 * 
	 * @return
	 */
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

	/**
	 * 删除功能前的校验
	 */
	public void validateRemove() {
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

	/**
	 * 删除功能
	 * 
	 * @return
	 */
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

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
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