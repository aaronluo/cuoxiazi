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

import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.innovaee.eorder.vo.UserFunctionVO;

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
    public static List<MenuLinkVO> getToolbarLinkVOList() {
        System.out.println("getToolbarLinkVOList");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("根据缓存中用户信息，获得该用户的权限列表（菜单）");
        }
        List<MenuLinkVO> toolbarlist = new ArrayList<MenuLinkVO>();

        // 从安全框架上下文得到用户详细信息
        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Assert.notNull(userDetail);

        // 从授权用户获取功能列表
        List<UserFunctionVO> userFunctions = userDetail.getUserFunctions();

        // 排序功能列表
        Collections.sort(userFunctions, new Comparator<UserFunctionVO>() {
            public int compare(UserFunctionVO o1, UserFunctionVO o2) {
                return o1.getFunction().getFunctionOrder()
                        .compareTo(o2.getFunction().getFunctionOrder());
            }
        });

        // 将功能列表填充到角色链接值对列表中
        toolbarlist.clear();
        // 子菜单
        MenuLinkVO parent = null;
        for (final UserFunctionVO ufVo : userFunctions) {
            if (0 == ufVo.getFunction().getFunctionParent()) {
                // 检查是否有重复元素
                boolean duplicated = false;
                for (MenuLinkVO role : toolbarlist) {
                    if (ufVo.getFunction().getFunctionName()
                            .equals(role.getName())) {
                        duplicated = true;
                        break;
                    }
                }
                if (duplicated) {
                    continue;
                }

                MenuLinkVO menuLinkVo = new MenuLinkVO();
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
                menuLinkVo.setList(new ArrayList<MenuLinkVO>());
                toolbarlist.add(menuLinkVo);
                continue;
            }

            for (MenuLinkVO menuLinkVo : toolbarlist) {
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
            for (MenuLinkVO menuLinkVo : parent.getList()) {
                if (ufVo.getFunction().getFunctionName()
                        .equals(menuLinkVo.getName())) {
                    duplicated = true;
                    break;
                }
            }

            if (duplicated) {
                continue;
            }

            MenuLinkVO rlVo = new MenuLinkVO();
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
    public static List<MenuLinkVO> getMenuLinkVOList(String functionDesc) {
        System.out.println("getMenuLinkVOList");
        List<MenuLinkVO> toolbarlist = getToolbarLinkVOList();

        // 1、先通过功能描述找到此功能的父功能ID
        Long parentFunctionId = 0L;
        for (MenuLinkVO menuLinkVo : toolbarlist) {
            List<MenuLinkVO> list = menuLinkVo.getList();
            for (MenuLinkVO menuLinkVo2 : list) {
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
        for (MenuLinkVO menuLinkVo : toolbarlist) {
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
        System.out.println("getParentFunctionDesc");
        List<MenuLinkVO> toolbarlist = getToolbarLinkVOList();

        // 1、先通过功能描述找到此功能的父功能ID
        Long parentFunctionId = 0L;
        for (MenuLinkVO menuLinkVo : toolbarlist) {
            List<MenuLinkVO> list = menuLinkVo.getList();
            for (MenuLinkVO menuLinkVo2 : list) {
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
        for (MenuLinkVO menuLinkVo : toolbarlist) {
            if (parentFunctionId == menuLinkVo.getId()) {
                // 3、得到父功能下所有的子功能列表
                return menuLinkVo.getFunctionDesc();
            }
        }

        return null;
    }
}