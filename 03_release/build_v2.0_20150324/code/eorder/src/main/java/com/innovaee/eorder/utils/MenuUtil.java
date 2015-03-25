/***********************************************
 * Filename        : MenuUtil.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.innovaee.eorder.vo.EOrderUserDetailVo;
import com.innovaee.eorder.vo.MenuLinkVo;
import com.innovaee.eorder.vo.UserFunctionVo;

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
    public static List<MenuLinkVo> getToolbarLinkVOList() {
        if (LOGGER.isDebugEnabled()) {
            // 找不到父节点
            LOGGER.debug("根据缓存中用户信息，获得该用户的权限列表（菜单）");
        }
        List<MenuLinkVo> toolbarlist = new ArrayList<MenuLinkVo>();

        // 从安全框架上下文得到用户详细信息
        EOrderUserDetailVo userDetail = (EOrderUserDetailVo) SecurityContextHolder
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
        toolbarlist.clear();
        for (final UserFunctionVo ufVo : userFunctions) {
            if (0 == ufVo.getFunction().getFunctionParent()) {
                // 检查是否有重复元素
                boolean duplicated = false;
                for (MenuLinkVo role : toolbarlist) {
                    if (ufVo.getFunction().getFunctionName()
                            .equals(role.getName())) {
                        duplicated = true;
                        break;
                    }
                }
                if (duplicated) {
                    continue;
                }

                MenuLinkVo menuLinkVo = new MenuLinkVo();
                menuLinkVo.setFlag("1");
                menuLinkVo.setLink(ufVo.getFunction().getFunctionPath());
                menuLinkVo.setId(ufVo.getFunction().getId());
                menuLinkVo.setName(ufVo.getFunction().getFunctionName());
                menuLinkVo
                        .setFunctionName(ufVo.getFunction().getFunctionName());
                menuLinkVo.setFunctionPicture(ufVo.getFunction()
                        .getFunctionPicture());

                menuLinkVo
                        .setFunctionDesc(ufVo.getFunction().getFunctionDesc());
                menuLinkVo.setOrder(ufVo.getFunction().getFunctionOrder());
                menuLinkVo.setList(new ArrayList<MenuLinkVo>());
                toolbarlist.add(menuLinkVo);
                continue;
            }

            // 子菜单
            MenuLinkVo parent = null;
            for (MenuLinkVo menuLinkVo : toolbarlist) {
                if (menuLinkVo.getId().equals(
                        ufVo.getFunction().getFunctionParent())) {
                    parent = menuLinkVo;
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
            for (MenuLinkVo menuLinkVo : parent.getList()) {
                if (ufVo.getFunction().getFunctionName()
                        .equals(menuLinkVo.getName())) {
                    duplicated = true;
                    break;
                }
            }

            if (duplicated) {
                continue;
            }

            MenuLinkVo rlVo = new MenuLinkVo();
            rlVo.setFlag("2");
            rlVo.setLink(ufVo.getFunction().getFunctionPath());
            rlVo.setId(ufVo.getFunction().getId());
            rlVo.setFunctionParent(ufVo.getFunction().getFunctionParent());
            rlVo.setName(ufVo.getFunction().getFunctionName());
            rlVo.setFunctionName(ufVo.getFunction().getFunctionName());
            rlVo.setFunctionDesc(ufVo.getFunction().getFunctionDesc());
            rlVo.setOrder(ufVo.getFunction().getFunctionOrder());
            parent.getList().add(rlVo);
        }

        return toolbarlist;
    }

    /**
     * 根据功能描述，获得对应的菜单列表
     * 
     * @param functionDesc
     *            功能描述
     * @return 菜单列表
     */
    public static List<MenuLinkVo> getMenuLinkVOList(String functionDesc) {
        List<MenuLinkVo> toolbarlist = getToolbarLinkVOList();

        // 1、先通过功能描述找到此功能的父功能ID
        Long parentFunctionId = 0L;
        for (MenuLinkVo menuLinkVo : toolbarlist) {
            List<MenuLinkVo> list = menuLinkVo.getList();
            for (MenuLinkVo menuLinkVo2 : list) {
                if (null != functionDesc
                        && functionDesc.equals(menuLinkVo2.getFunctionDesc())) {
                    parentFunctionId = menuLinkVo2.getFunctionParent();
                    if (0 != parentFunctionId) {
                        break;
                    }
                }
            }
            if (0 != parentFunctionId) {
                break;
            }

        }

        // 2、得到父功能
        for (MenuLinkVo menuLinkVo : toolbarlist) {
            if (parentFunctionId == menuLinkVo.getId()) {
                // 3、得到父功能下所有的子功能列表
                return menuLinkVo.getList();
            }
        }

        return null;
    }

    /**
     * 根据功能描述，获得对应的菜单列表
     * 
     * @param functionDesc
     *            功能描述
     * @return 菜单列表
     */
    public static String getParentFunctionDesc(String functionDesc) {
        List<MenuLinkVo> toolbarlist = getToolbarLinkVOList();

        // 1、先通过功能描述找到此功能的父功能ID
        Long parentFunctionId = 0L;
        for (MenuLinkVo menuLinkVo : toolbarlist) {
            List<MenuLinkVo> list = menuLinkVo.getList();
            for (MenuLinkVo menuLinkVo2 : list) {
                if (null != functionDesc
                        && functionDesc.equals(menuLinkVo2.getFunctionDesc())) {
                    parentFunctionId = menuLinkVo2.getFunctionParent();
                    if (0 != parentFunctionId) {
                        break;
                    }
                }
            }
            if (0 != parentFunctionId) {
                break;
            }

        }

        // 2、得到父功能
        for (MenuLinkVo menuLinkVo : toolbarlist) {
            if (parentFunctionId == menuLinkVo.getId()) {
                // 3、得到父功能下所有的子功能列表
                return menuLinkVo.getFunctionDesc();
            }
        }

        return null;
    }
}