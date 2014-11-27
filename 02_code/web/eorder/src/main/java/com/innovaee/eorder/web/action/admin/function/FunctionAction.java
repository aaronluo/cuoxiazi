package com.innovaee.eorder.web.action.admin.function;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.service.FunctionService;
import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class FunctionAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FunctionAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String functionName;
	private String[] functionNames;
	private Function function = new Function();
	private List<Function> functions = new ArrayList<Function>();

	@Resource
	private FunctionService functionService;

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doFunction() {
		logger.debug("enter doFunction() method");

		functions = functionService.findAllFunctions();

		return SUCCESS;
	}

	public String doLoad() {
		function = functionService.findFunctionsByFunctionName(functionName);
		return SUCCESS;
	}

	public String doList() {
		// books = dao.getBooks();
		functions = functionService.findAllFunctions();
		return SUCCESS;
	}

	public String doStore() {
		// dao.storeBook(function);
		// Function function2 = new Function("abcd", "abcd", "abcd", "abcd", "abcd", "abcd", false);
		functionService.saveFunction(function);
		// String result = functionService.storeFunction(function);
		// System.out.println("store result: " + result);
		return SUCCESS;
	}

	public String doUpdate() {
		// dao.storeBook(function);
		// Function function2 = new Function("abcd", "abcd", "abcd", "abcd", "abcd", "abcd", false);
		functionService.updateFunction(function);
		// String result = functionService.storeFunction(function);
		// System.out.println("store result: " + result);
		return SUCCESS;
	}

	public String doRemove() {
		if (null != functionName) {
			functionService.removeFunction(functionName);
		} else {
			functionService.removeFunctions(functionNames);
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

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String[] getFunctionNames() {
		return functionNames;
	}

	public void setFunctionNames(String[] functionNames) {
		this.functionNames = functionNames;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}


}