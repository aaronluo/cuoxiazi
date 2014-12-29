/***********************************************
 * Filename		: FunctionOpAction.java																									: DishService.java
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

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.FunctionVO;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

/**   
* @Title: FunctionOpAction 
* @Description: 功能操作Action（增加和修改）
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class FunctionOpAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String functionId;
	private String functionName;
	private String functionDesc;
	private String functionPath;
	private String functionParent;
	private String functionOrder;
	private String[] functionIds;
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
		List<RoleLinkVo> sessionMenulist = (List<RoleLinkVo>) session
				.getAttribute("menulist");
		this.setMenulist(sessionMenulist);
		this.setMenulist(MenuUtil.getRoleLinkVOList());
		functionvos = functionService.findAllFunctionVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public void validate() {
		refreshData();
	}

	public void validateSave() {
		// 查看用户名是否已存在
		Function function = functionService
				.findFunctionByFunctionName(functionName);
		if (null != function) {
			addFieldError("functionName", "功能名称已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 检查父节点ID是否有效
		checkFunctionParentId();
	}

	public void validateUpdate() {
		// 查看用户名是否已存在
		Function function1 = functionService.loadFunction(Integer
				.parseInt(functionId));
		Function function2 = functionService
				.findFunctionByFunctionName(functionName);
		// 可以找到，而且和自己的名字不同，则说明已经被占用
		if (null != function2
				&& function1.getFunctionId() != function2.getFunctionId()) {
			addFieldError("functionName", "功能名称已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 检查父节点ID是否有效
		checkFunctionParentId();
	}

	private String checkFunctionParentId() {
		if (null != functionParent && !"".equals(functionParent.trim())) {
			int parentFunctionId = -1;
			try {
				parentFunctionId = Integer.parseInt(functionParent);
			} catch (NumberFormatException e) {
				addFieldError("functionParent",
						"父功能ID应该为“存在的且其父ID为0”的记录对应的ID数字！");
				// 更新页面数据
				refreshData();

				return INPUT;
			}
			Function function = functionService.loadFunction(parentFunctionId);
			if (null == function || 0 != function.getFunctionParent()) {
				addFieldError("functionParent",
						"父功能ID应该为“存在的且其父ID为0”的记录对应的ID数字！");
				// 更新页面数据
				refreshData();

				return INPUT;
			}
		}

		return INPUT;
	}

	/**
	 * 清空输入框数据
	 */
	private void clean() {
		this.setFunctionId("");
		this.setFunctionName("");
		this.setFunctionDesc("");
		this.setFunctionPath("");
		this.setFunctionParent("");
		this.setFunctionOrder("");

	}

	public String save() {
		Function function = new Function();
		if (null != functionName && !"".equals(functionName.trim())) {
			function.setFunctionName(functionName);
		} else {
			addFieldError("functionName", "权限名称不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		} else {
			addFieldError("functionDesc", "权限描述不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		} else {
			addFieldError("functionPath", "权限路径不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionParent && !"".equals(functionParent.trim())) {
			try {
				function.setFunctionParent(Integer.parseInt(functionParent));
			} catch (NumberFormatException e) {
				addFieldError("functionParent",
						"父功能ID应该为“存在的且其父ID为0”的记录对应的ID数字！");
				// 更新页面数据
				refreshData();

				return INPUT;
			}
			function.setFunctionParent(Integer.parseInt(functionParent));
		} else {
			addFieldError("functionPath", "父权限ID不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		} else {
			addFieldError("functionOrder", "权限排序不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}
		function.setFunctionStatus(true);
		functionService.saveFunction(function);

		this.setMessage("新增成功！");
		// 清空输入框数据
		clean();
		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	public String update() {
		Function function = null;
		if (null != functionId) {
			function = functionService.loadFunction(Integer
					.parseInt(functionId));
		}

		if (null != functionName && !"".equals(functionName.trim())) {
			function.setFunctionName(functionName);
		} else {
			addFieldError("functionName", "权限名称不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		} else {
			addFieldError("functionDesc", "权限描述不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		} else {
			addFieldError("functionPath", "权限路径不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionParent) {
			try {
				function.setFunctionParent(Integer.parseInt(functionParent));
			} catch (NumberFormatException e) {
				addFieldError("functionParent",
						"父功能ID应该为“存在的且其父ID为0”的记录对应的ID数字！");
				// 更新页面数据
				refreshData();

				return INPUT;
			}
		} else {
			addFieldError("functionPath", "父权限ID不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		} else {
			addFieldError("functionOrder", "权限排序不能为空！");
			// 更新页面数据
			refreshData();

			return INPUT;
		}

		functionService.updateFunction(function);

		this.setMessage("修改成功！");

		// 清空输入框数据
		clean();
		// 更新页面数据
		refreshData();
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

	public String[] getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String[] functionIds) {
		this.functionIds = functionIds;
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

	public String getFunctionParent() {
		return functionParent;
	}

	public void setFunctionParent(String functionParent) {
		this.functionParent = functionParent;
	}

	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}

}