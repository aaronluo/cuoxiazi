package com.innovaee.eorder.web.action.admin.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.FunctionVO;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.web.action.BaseAction;

public class FunctionOpAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	private String functionId;
	private String functionName;
	private String functionDesc;
	private String functionPath;
	private Integer functionParent;
	private String functionOrder;
	private String[] functionIds;
	private List<FunctionVO> functionvos;

	@Resource
	private FunctionService functionService;

	@Resource
	private RoleFunctionService roleFunctionService;

	private String contextPath;

	public void refreshData() {
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
		System.out.println("======validateSave======" + functionName == null);
		// 查看用户名是否已存在
		Function function = functionService
				.findFunctionByFunctionName(functionName);
		if (null != function) {
			addFieldError("functionName", "功能名称已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	public void validateUpdate() {
		System.out.println("======validateSave======" + functionName == null);
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
	}

	public String save() {
		Function function = new Function();
		if (null != functionName && !"".equals(functionName.trim())) {
			function.setFunctionName(functionName);
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		}

		if (null != functionParent && 0 != functionParent) {
			function.setFunctionParent(functionParent);
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		}
		function.setFunctionStatus(true);
		functionService.saveFunction(function);

		this.setMessage("新增成功！");
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
		}

		if (null != functionDesc && !"".equals(functionDesc.trim())) {
			function.setFunctionDesc(functionDesc);
		}

		if (null != functionPath && !"".equals(functionPath.trim())) {
			function.setFunctionPath(functionPath);
		}

		if (0 != functionParent) {
			function.setFunctionParent(functionParent);
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		}

		functionService.updateFunction(function);

		this.setMessage("修改成功！");

		this.setFunctionId("");
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