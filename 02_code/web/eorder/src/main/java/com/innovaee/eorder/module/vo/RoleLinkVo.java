package com.innovaee.eorder.module.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleLinkVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String order; // menu item order number;
	private String name; // menu name
	private String displayName; // the display name of the menu
	private String link; // linkage address
	private String flag; // level1 menu is 1, level2 menu is 2
	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>(); // level2 menus list

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
}