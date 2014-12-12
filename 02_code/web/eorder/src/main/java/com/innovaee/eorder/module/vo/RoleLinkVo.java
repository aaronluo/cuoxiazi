package com.innovaee.eorder.module.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleLinkVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String order; // menu item order number;
	private Integer id; // menu name
	private String name; // menu name
	private String functionName; // the display name of the menu
	private String link; // linkage address
	private String flag; // level1 menu is 1, level2 menu is 2
	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>(); // level2 menus
																	// list

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<RoleLinkVo> getList() {
		return list;
	}

	public void setList(List<RoleLinkVo> list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}