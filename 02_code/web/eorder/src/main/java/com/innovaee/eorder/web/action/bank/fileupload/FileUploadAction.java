package com.innovaee.eorder.web.action.bank.fileupload;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.innovaee.eorder.module.vo.ResetPasswordVo;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.web.action.BaseAction;

public class FileUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(FileUploadAction.class);

	private ResetPasswordVo resetPasswordVo;

	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

	private String contextPath;

	public String login() {
		logger.debug("enter login() method");

		return SUCCESS;
	}

	public String doFileUpload() {
		logger.debug("enter doResetPassword() method");
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
}