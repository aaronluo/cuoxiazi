package com.innovaee.eorder.module.utils.log;

import java.util.Date;

public class InvokeInfo {
	private String invokerName;// invokerName
	private Date date; // start invoke time
	private int level; // nested level
	private boolean inTransaction;

	public String getInvokerName() {
		return invokerName;
	}

	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isInTransaction() {
		return inTransaction;
	}

	public void setInTransaction(boolean inTransaction) {
		this.inTransaction = inTransaction;
	}
}