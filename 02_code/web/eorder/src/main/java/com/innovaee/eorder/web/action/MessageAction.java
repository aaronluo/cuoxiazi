package com.innovaee.eorder.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// add action messages
		addActionError("Action Error Message");
		addActionMessage("");

		addFieldError("field", "field error");

		return SUCCESS;
	}

}