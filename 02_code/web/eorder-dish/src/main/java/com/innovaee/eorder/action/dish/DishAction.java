/***********************************************
 * Filename        : DishAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Category;
import com.innovaee.eorder.entity.Dish;
import com.innovaee.eorder.exception.CategoryNotFoundException;
import com.innovaee.eorder.exception.DishNotFoundException;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.service.CategoryService;
import com.innovaee.eorder.service.DishService;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.vo.CategoryVO;
import com.innovaee.eorder.vo.DishVO;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;

/**
 * @Title: DishAction
 * @Description: 功能Action（查询和删除）
 *
 * @version V1.0
 */
public class DishAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Dish";

    /** 菜品名称 */
    private String name;

    /** 菜品价格 */
    private Float price;

    /** 菜品图片 */
    private String picPath;

    /** 在售状态 */
    private boolean onSell;

    /** 分类id */
    private Long categoryId;

    /** 更多信息 */
    private String misc;

    /** 菜品分类列表 */
    private List<Category> categoryList;

    /** 菜品分类列表 */
    private List<CategoryVO> categoryVOList;

    /** 功能值对象列表 */
    private List<DishVO> dishvos = new ArrayList<DishVO>();

    /** 功能服务类对象 */
    @Resource
    private DishService dishService;

    /** 功能服务类对象 */
    @Resource
    private CategoryService categoryService;

    /**
     * 进入功能页面
     * 
     * @return
     */
    public String dish() {
        renewPage();
        // 更新页面数据
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 进入功能页面
     * 
     * @return
     */
    public String list() {
        refreshDataList();
        renewPage();
        // 更新页面数据
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 新增页面
     * 
     * @return
     */
    public String add() {
        // 更新页面数据
        renewPage();
        return SUCCESS;
    }

    // public void validateSave() {
    // System.out.println("validateSave");
    // if (null != price &&0 != price) {
    // //dishVO.setPrice(price);
    //
    // addFieldError("functionName", "功能名称已被占用！");
    // this.setMessage(MessageUtil.getMessage("dish_price_empty"));
    // // 更新页面数据
    // refreshPageData();
    // }
    // }

    /**
     * 保存功能
     * 
     * @return
     */
    public String save() {
        // 更新页面数据
        renewPage();
        try {
            DishVO dishVO = new DishVO();

            if (null != name && !"".equals(name.trim())) {
                dishVO.setName(name);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_name_empty"));
                // 更新页面数据
                refreshPageData();
                return INPUT;
            }

            if (null != price && 0 != price) {
                dishVO.setPrice(price);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_price_empty"));
                // 更新页面数据
                refreshPageData();
                return INPUT;
            }

            if (null != picPath && !"".equals(picPath.trim())) {
                dishVO.setPicPath(picPath);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_picture_empty"));
                // 更新页面数据
                refreshPageData();
                return INPUT;
            }

            dishVO.setCategoryId(categoryId);
            // 新增成功
            Dish newDish = dishService.addDish(dishVO);

            if (null != newDish) {
                this.setMessage(MessageUtil.getMessage("add_success"));
                this.setName("");
                this.setPrice(null);
                this.setPicPath("");
                renewPage();
            } else {
                this.setMessage(MessageUtil.getMessage("add_failure"));
                return INPUT;
            }
        } catch (DuplicateNameException e) {
            this.setMessage(e.getMessage());
            // 更新页面数据
            refreshPageData();
            return INPUT;
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
            // 更新页面数据
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
        try {
            if (null != id && !"".equals(id.trim())) {
                Dish dish = dishService.getDishById(Long.parseLong(id));
                name = dish.getName();
                price = dish.getPrice();
                picPath = dish.getPicPath();
                onSell = dish.isOnSell();
                categoryId = dish.getCategory().getId();
            }
        } catch (DishNotFoundException e) {
            this.setMessage(e.getMessage());
            return INPUT;
        }
        // 更新页面数据
        renewPage();

        return SUCCESS;
    }

    /**
     * 更新功能
     * 
     * @return
     */
    public String update() {
        // 更新页面数据
        renewPage();
        try {
            Dish dish = null;
            DishVO dishVO = null;
            if (null != id) {
                dish = dishService.getDishById(Long.parseLong(id));
            }

            if (null != dish) {
                dishVO = new DishVO();
                dishVO.setId(dish.getId());
                dishVO.setCategoryId(categoryId);
            }

            if (null != name && !"".equals(name.trim())) {
                dishVO.setName(name);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_name_empty"));
                return INPUT;
            }

            if (0 != price) {
                dishVO.setPrice(price);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_price_empty"));
                return INPUT;
            }

            if (null != picPath && !"".equals(picPath.trim())) {
                dishVO.setPicPath(picPath);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_picture_empty"));
                return INPUT;
            }

            dishService.updateDish(dishVO);
        } catch (DuplicateNameException e) {
            this.setMessage(e.getMessage());
            return INPUT;
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
            return INPUT;
        } catch (DishNotFoundException e) {
            this.setMessage(e.getMessage());
            return INPUT;
        }
        this.setMessage(MessageUtil.getMessage("update_success"));
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
                dishService.deleteDish(Long.parseLong(id));
            } catch (DishNotFoundException e) {
                this.setMessage(e.getMessage());
                return INPUT;
            }
        }

        this.setMessage(MessageUtil.getMessage("delete_success"));

        // 更新记录列表
        refreshDataList();
        // 更新分类列表
        renewCategoryVOList();

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
     * 刷新列表
     */
    private void refreshDataList() {
        Long id = 0L;
        try {
            Integer recordCount = dishService.getDishCountById(categoryId);
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

            List<Dish> dishes = null;
            if (null != pageInput) {
                if (pageInput > pageTotal) {
                    pageInput = pageTotal;
                    pageNow = pageTotal;
                } else if (pageInput < 1) {
                    pageNow = 1;
                    pageInput = 1;
                }

                dishes = dishService.getDishesByPage(pageInput,
                        Constants.PAGE_SIZE, categoryId);

                pageNow = pageInput;
            } else {
                dishes = dishService.getDishesByPage(pageNow,
                        Constants.PAGE_SIZE, categoryId);
            }

            DishVO dishvo = null;
            Category category = null;
            for (Dish dish : dishes) {
                dishvo = new DishVO();
                // BeanUtils.copyProperties(dish, dishvo);
                dishvo.setId(dish.getId());
                dishvo.setName(dish.getName());
                dishvo.setPicPath(dish.getPicPath());
                dishvo.setPrice(dish.getPrice());
                id = dish.getCategory().getId();
                category = categoryService.getCategoryById(id);
                if (null != category) {
                    dishvo.setCategoryId(category.getId());
//                    dishvo.setName(category.getName());
                }
                dishvos.add(dishvo);
            }
        } catch (PageIndexOutOfBoundExcpeiton e) { // TODO
            this.setMessage(MessageUtil.getMessage(
                    "page_out_of_bound_exception", pageNow.toString(),
                    pageTotal.toString()));
        } catch (InvalidPageSizeException e) {
            this.setMessage(MessageUtil.getMessage(
                    "invalid_page_size_exception",
                    Constants.PAGE_SIZE.toString()));
        } catch (CategoryNotFoundException e) {
            this.setMessage(MessageUtil.getMessage(
                    "category_not_found_exception", id.toString()));
        }
    }

    /**
     * 
     */
    private void renewPage() {
        renewCategoryVOList();

        if (null == picPath || "".equals(picPath)) {
            this.setPicPath(Constants.DEFAULT_DISH_PIC);
        }

        // 更新页面数据
        refreshPageData();
    }

    private void renewCategoryVOList() {
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        // 更新权限列表
        CategoryVO categoryVO = null;
        // CategoryVO rootCategoryVO = new CategoryVO();
        // rootCategoryVO.setStringId("0");
        // rootCategoryVO.setName(MessageUtil.getMessage("dish_root"));
        // categoryVOList.add(rootCategoryVO);
        List<Category> categoryList = categoryService.getAllCategories();
        for (Category category : categoryList) {
            categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);
            categoryVO.setStringId(category.getId().toString());
            categoryVOList.add(categoryVO);
        }
        this.setCategoryVOList(categoryVOList);
    }

    public List<DishVO> getDishvos() {
        return dishvos;
    }

    public void setDishvos(List<DishVO> dishvos) {
        this.dishvos = dishvos;
    }

    public DishService getDishService() {
        return dishService;
    }

    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public List<CategoryVO> getCategoryVOList() {
        return categoryVOList;
    }

    public void setCategoryVOList(List<CategoryVO> categoryVOList) {
        this.categoryVOList = categoryVOList;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

}