/***********************************************
 * Filename        : RoleLinkVo.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: RoleLinkVo
 * @Description: 角色链接值对象（用于显示菜单）
 *
 * @version V1.0
 */

public class RoleLinkVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	/** 菜单项排序号 */
	private String order;

	/** 菜单ID */
	private Integer id;

	/** 菜单名 */
	private String name;

	/** 功能名称 */
	private String functionName;

	/** 链接地址 */
	private String link;
	
	/** 菜单等级，1为父节点，2为子节点 */
	private String flag;

	/** 子节点菜单列表 */
	private List<RoleLinkVo> list = new ArrayList<RoleLinkVo>();

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