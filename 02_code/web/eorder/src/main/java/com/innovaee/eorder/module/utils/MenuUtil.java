/***********************************************
 * Filename        : MenuUtil.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.utils;

import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Title: MenuUtil
 * @Description: 菜单工具类
 *
 * @version V1.0
 */
public class MenuUtil {

	/** 日志对象 */
	private static final Logger LOGGER = Logger.getLogger(MenuUtil.class);

	/**
	 * 根据缓存中用户信息，获得该用户的权限列表（菜单）
	 * 
	 * @return 菜单列表
	 */
	public static List<RoleLinkVo> getRoleLinkVOList() {
		if (LOGGER.isDebugEnabled()) {
			// 找不到父节点
			LOGGER.debug("根据缓存中用户信息，获得该用户的权限列表（菜单）");
		}
		List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

		// 从安全框架上下文得到用户详细信息
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Assert.notNull(userDetail);

		// 从授权用户获取功能列表
		List<UserFunctionVo> userFunctions = userDetail.getUserFunctions();

		// 排序功能列表
		Collections.sort(userFunctions, new Comparator<UserFunctionVo>() {
			public int compare(UserFunctionVo o1, UserFunctionVo o2) {
				return o1.getFunction().getFunctionOrder()
						.compareTo(o2.getFunction().getFunctionOrder());
			}
		});

		// 将功能列表填充到角色链接值对列表中
		menulist.clear();
		for (final UserFunctionVo ufVo : userFunctions) {
			if (0 == ufVo.getFunction().getFunctionParent()) {
				// 检查是否有重复元素
				boolean duplicated = false;
				for (RoleLinkVo role : menulist) {
					if (ufVo.getFunction().getFunctionName()
							.equals(role.getName())) {
						duplicated = true;
						break;
					}
				}
				if (duplicated) {
					continue;
				}

				RoleLinkVo roleLinkVo = new RoleLinkVo();
				roleLinkVo.setFlag("1");
				roleLinkVo.setLink(ufVo.getFunction().getFunctionPath());
				roleLinkVo.setId(ufVo.getFunction().getFunctionId());
				roleLinkVo.setName(ufVo.getFunction().getFunctionName());
				roleLinkVo
						.setFunctionName(ufVo.getFunction().getFunctionName());
				roleLinkVo.setOrder(ufVo.getFunction().getFunctionOrder());
				roleLinkVo.setList(new ArrayList<RoleLinkVo>());
				menulist.add(roleLinkVo);
				continue;
			}

			// 子菜单
			RoleLinkVo parent = null;
			for (RoleLinkVo roleLinkVo : menulist) {
				if (roleLinkVo.getId().equals(
						ufVo.getFunction().getFunctionParent())) {
					parent = roleLinkVo;
					break;
				}
			}
			if (null == parent) {
				if (LOGGER.isDebugEnabled()) {
					// 找不到父节点
					LOGGER.debug(String.format(
							"cannot find function[%s]'s parent", ufVo
									.getFunction().getFunctionName()));
				}
				continue;
			}

			// 检查是否有重复
			boolean duplicated = false;
			for (RoleLinkVo roleLinkVo : parent.getList()) {
				if (ufVo.getFunction().getFunctionName()
						.equals(roleLinkVo.getName())) {
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