package com.innovaee.eorder.web.action.admin.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.RoleFunction;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.service.RoleFunctionService;
import com.innovaee.eorder.module.vo.FunctionVO;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class FunctionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FunctionAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

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

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doFunction() {
		logger.debug("enter doFunction() method");

		functionvos = functionService.findAllFunctionVOs();

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

		functionvos = functionService.findAllFunctionVOs();
		return SUCCESS;
	}

	public String doList() {
		functionvos = functionService.findAllFunctionVOs();
		return SUCCESS;
	}

	public String doStore() {
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

		if (0 != functionParent) {
			function.setFunctionParent(functionParent);
		}

		if (null != functionOrder && !"".equals(functionOrder.trim())) {
			function.setFunctionOrder(functionOrder);
		}
		function.setFunctionStatus(true);
		functionService.saveFunction(function);
		return SUCCESS;
	}

	public String doUpdate() {
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
		return SUCCESS;
	}

	public String doRemove() {
		if (null != functionId) {
			// 先判断角色功能关联关系，如果此功能已授权给某个角色，则不能删除
			List<RoleFunction> myRoleFunctions = roleFunctionService
					.findRoleFunctionsByFunctionId(Integer.parseInt(functionId));
			if (null == myRoleFunctions || 0 == myRoleFunctions.size()) {
				functionService.removeFunction(Integer.parseInt(functionId));
			}
		} else {
			functionService.removeFunctions(functionIds);
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