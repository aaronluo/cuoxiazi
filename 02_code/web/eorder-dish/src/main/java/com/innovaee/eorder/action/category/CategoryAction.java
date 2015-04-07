/***********************************************
 * Filename       : CategoryAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.category;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.service.CategoryService;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.CategoryVO;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

/**
 * @Title: CategoryAction
 * @Description: 菜品分类Action
 *
 * @version V1.0
 */
public class CategoryAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的菜单分类描述常量 */
    public static final String FUNCTION_DESC = "Category";

    /** 菜单分类值对象 */
    private CategoryVO category;

    /** 菜单分类对象列表 */
    private List<Category> categories;

    /** 菜单分类服务类对象 */
    @Resource
    private CategoryService categoryService;

    /**
     * 进入菜单分类页面
     * 
     * @return
     */
    public String list() {
        getCategoryList();
        // 刷新系统菜单
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 响应添加会员等级按钮的Action
     * 
     * @return
     */
    public String add() {
        // 刷新系统菜单
        refreshPageData();

        category = new CategoryVO();

        if (null == category.getPicPath() || "".equals(category.getPicPath())) {
            category.setPicPath(Constants.DEFAULT_DISH_PIC);
        }

        return SUCCESS;
    }

    /**
     * 添加新会员等级Action
     * 
     * @return
     */
    public String save() {
        logger.debug(category);
        try {
            // 1. 检查传入的会员等级属性是否合法
            if (!checkCategoryVO()) {
                return ERROR;
            } else {
                // 2. 创建新的会员等级
                categoryService.addCategory(category);
                setMessage(MessageUtil.getMessage("category_save_success",
                        category.getName()));
                category = new CategoryVO();
            }
        } catch (DuplicateNameException e) {
            logger.error(e.getMessage());
            this.setMessage(e.getMessage());
            return ERROR;
        } finally {
            getCategoryList();
            refreshPageData();
        }

        return SUCCESS;
    }

    /**
     * 加载单个功能信息
     * 
     * @return
     */
    public String edit() {
        // 刷新系统菜单
        refreshPageData();
        category = new CategoryVO();
        try {
            if (null != id && !"".equals(id.trim())) {
                Category categoryDB = categoryService.getCategoryById(Long
                        .parseLong(id));
                category.setId(categoryDB.getId());
                category.setName(categoryDB.getName());
                category.setPicPath(categoryDB.getPicPath());
                category.setName(categoryDB.getName());
            } else {
                this.setMessage("ERROR");
                return ERROR;
            }
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 更新功能
     * 
     * @return
     */
    public String update() {
        try {
            // 1. 检查传入的会员等级属性是否合法
            if (!checkCategoryVO()) {
                return ERROR;
            } else {
                // 2. 更新新的会员等级
                categoryService.updateCategory(category);
                setMessage(MessageUtil.getMessage("category_update_success",
                        category.getName()));
                category = new CategoryVO();
            }
        } catch (DuplicateNameException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } catch (NumberFormatException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } finally {
            getCategoryList();
            refreshPageData();
        }
        return SUCCESS;
    }

    /**
     * 删除功能
     * 
     * @return
     */
    public String remove() {
        // 更新页面数据
        refreshPageData();

        if (null != id) {
            try {
                categoryService.deleteCategory(Long.parseLong(id));
            } catch (CategoryNotFoundException e) {
                this.setMessage(e.getMessage());
                return ERROR;
            }
        }

        this.setMessage(MessageUtil.getMessage("delete_success"));

        // 更新记录列表
        getCategoryList();

        return SUCCESS;
    }

    /**
     * 刷新页面数据
     */
    private void refreshPageData() {
        // 当前用户的工具栏
        List<MenuLinkVO> toolbarList = MenuUtil.getToolbarLinkVOList();

        List<MenuLinkVO> menuLink = null;
        if (null != toolbarList && 0 < toolbarList.size()) {
            // 第一个功能对应的菜单
            menuLink = MenuUtil.getMenuLinkVOList(FUNCTION_DESC);
        }

        this.setToolbarList(toolbarList);
        this.setMenuList(menuLink);
        this.setCurrentFunctionDesc(FUNCTION_DESC);
        this.setCurrentToolbar(MenuUtil.getParentFunctionDesc(FUNCTION_DESC));

        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());
    }

    /**
     * 获取菜品分类列表
     */
    private void getCategoryList() {
        try {
            Integer recordCount = categoryService.count();
            this.setCount(recordCount);
            int pageTotal = 1;
            if (0 == recordCount % Constants.PAGE_SIZE) {
                pageTotal = recordCount / Constants.PAGE_SIZE;
            } else {
                pageTotal = recordCount / Constants.PAGE_SIZE + 1;
            }
            this.setPageTotal(pageTotal);

            // 处理用户点击【上一页】和【下一页】边界情况
            if (pageNow > pageTotal) {
                pageNow = pageTotal;
                pageInput = pageNow;
            } else if (pageNow < 1) {
                pageNow = 1;
                pageInput = 1;
            }

            if (null != pageInput) {
                if (pageInput > pageTotal) {
                    pageInput = pageTotal;
                    pageNow = pageTotal;
                } else if (pageInput < 1) {
                    pageNow = 1;
                    pageInput = 1;
                }

                categories = categoryService.getCategoriesByPage(pageInput,
                        Constants.PAGE_SIZE);

                pageNow = pageInput;
            } else {
                categories = categoryService.getCategoriesByPage(pageNow,
                        Constants.PAGE_SIZE);
            }
        } catch (PageIndexOutOfBoundExcpeiton e) {
            this.setMessage(e.getMessage());
        } catch (InvalidPageSizeException e) {
            this.setMessage(e.getMessage());
        }
    }

    /**
     * 检查一个菜品的设定是否合法
     */
    private boolean checkCategoryVO() {
        boolean isValidVO = true;

        if (null == category) {
            isValidVO = false;
            addFieldError("category.categoryvo",
                    MessageUtil.getMessage("category_invalid_vo"));
            return isValidVO;
        }

        if (StringUtil.isEmpty(category.getName())) {
            isValidVO = false;
            addFieldError("category.name",
                    MessageUtil.getMessage("category_name_rule"));
        }

        if (StringUtil.isEmpty(category.getPicPath())) {
            isValidVO = false;
            addFieldError("category.pic.path",
                    MessageUtil.getMessage("pic_path_rule"));
        }

        return isValidVO;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryVO getCategory() {
        return category;
    }

    public void setCategory(CategoryVO category) {
        this.category = category;
    }

}