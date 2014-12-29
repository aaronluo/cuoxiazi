/***********************************************
 * Filename		: MenuUtil.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;

/**
 * @Title: MenuUtil
 * @Description: 菜单工具类
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class MenuUtil {

	private static final Logger logger = Logger.getLogger(MenuUtil.class);

	/**
	 * 根据缓存中用户信息，获得该用户的权限列表（菜单）
	 * 
	 * @return 菜单列表
	 */
	public static List<RoleLinkVo> getRoleLinkVOList() {
		List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

		// 从安全框架上下文得到用户详细信息
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Assert.notNull(userDetail);

		// 从授权用户获取功能列表
		List<UserFunctionVo> userFunctions = userDetail.getUserFunctions();

		// 排序功能列表
		Collections.sort(userFunctions, new Comparator<UserFunctionVo>() {
			@Override
			public int compare(UserFunctionVo o1, UserFunctionVo o2) {
				return o1.getFunction().getFunctionOrder()
						.compareTo(o2.getFunction().getFunctionOrder());
			}
		});

		// 将功能列表填充到角色链接值对列表中
		menulist.clear();
		for (UserFunctionVo ufVo : userFunctions) {
			if (0 == ufVo.getFunction().getFunctionParent()) {
				// 检查是否有重复元素
				boolean duplicated = false;
				for (RoleLinkVo r : menulist) {
					if (ufVo.getFunction().getFunctionName()
							.equals(r.getName())) {
						duplicated = true;
						break;
					}
				}
				if (duplicated) {
					continue;
				}

				RoleLinkVo rlVo = new RoleLinkVo();
				rlVo.setFlag("1");
				rlVo.setLink(ufVo.getFunction().getFunctionPath());
				rlVo.setId(ufVo.getFunction().getFunctionId());
				rlVo.setName(ufVo.getFunction().getFunctionName());
				rlVo.setFunctionName(ufVo.getFunction().getFunctionName());
				rlVo.setOrder(ufVo.getFunction().getFunctionOrder());
				rlVo.setList(new ArrayList<RoleLinkVo>());
				menulist.add(rlVo);
				continue;
			}

			// 子菜单
			RoleLinkVo parent = null;
			for (RoleLinkVo p : menulist) {
				if (p.getId() == ufVo.getFunction().getFunctionParent()) {
					parent = p;
					break;
				}
			}
			if (null == parent) {
				// 找不到父节点
				logger.warn(String.format("cannot find function[%s]'s parent",
						ufVo.getFunction().getFunctionName()));
				continue;
			}

			// 检查是否有重复
			boolean duplicated = false;
			for (RoleLinkVo r : parent.getList()) {
				if (ufVo.getFunction().getFunctionName().equals(r.getName())) {
					duplicated = true;
					break;
				}
			}
			if (duplicated) {
				continue;
			}
			RoleLinkVo rlVo = new RoleLinkVo();
			rlVo.setFlag("2");
			rlVo.setLink(ufVo.getFunction().getFunctionPath());
			rlVo.setId(ufVo.getFunction().getFunctionId());
			rlVo.setName(ufVo.getFunction().getFunctionName());
			rlVo.setFunctionName(ufVo.getFunction().getFunctionName());
			rlVo.setOrder(ufVo.getFunction().getFunctionOrder());
			parent.getList().add(rlVo);
		}

		return menulist;
	}
}