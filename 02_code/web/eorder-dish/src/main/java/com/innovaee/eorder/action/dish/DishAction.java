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

    /** 功能名称 */
    private String dishName;

    /** 菜品价格 */
    private float dishPrice;

    /** 菜品图片 */
    private String dishPicture;

    /** 在售状态 */
    private boolean onSell;

    /** 分类id */
    @XmlTransient
    private Long categoryId;

    /** 更多信息 */
    @XmlTransient
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
        // refreshDataList();
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
     * 加载单个功能信息
     * 
     * @return
     */
    public String edit() {
        try {
            if (null != id && !"".equals(id.trim())) {
                Dish dish = dishService.getDishById(Long.parseLong(id));
                dishName = dish.getDishName();
                dishPrice = dish.getDishPrice();
                dishPicture = dish.getDishPicture();
                onSell = dish.isOnSell();
                categoryId = dish.getCategory().getId();
            }
        } catch (DishNotFoundException e) {
            this.setMessage(MessageUtil.getMessage("duplicate_name_exception"));
            return INPUT;
        }
        // 更新页面数据
        renewPage();

        return SUCCESS;
    }

    /**
     * 刷新页面数据
     */
    public void refreshPageData() {
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
    public void refreshDataList() {
        Long id = 0L;
        try {
            Integer recordCount = dishService.count();
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
                BeanUtils.copyProperties(dish, dishvo);
                id = dish.getCategory().getId();
                category = categoryService.getCategoryById(id);
                if (null != category) {
                    dishvo.setCategoryId(category.getId());
                    dishvo.setCategoryName(category.getCategoryName());
                }
                dishvos.add(dishvo);
            }
        } catch (PageIndexOutOfBoundExcpeiton e) {
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
     * 保存功能
     * 
     * @return
     */
    public String save() {
        // 更新页面数据
        renewPage();
        try {
            // 查看用户名是否已存在
            Dish dbdish = dishService.getDishByName(dishName);

            if (null != dbdish) {
                this.setMessage(MessageUtil.getMessage("dish_name_occupy"));
                renewPage();
                return INPUT;
            }

            DishVO dishVO = new DishVO();

            if (null != dishName && !"".equals(dishName.trim())) {
                dishVO.setDishName(dishName);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_name_empty"));
                // 更新页面数据
                refreshPageData();
                return INPUT;
            }

            if (0 != dishPrice) {
                dishVO.setDishPrice(dishPrice);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_price_empty"));
                // 更新页面数据
                refreshPageData();
                return INPUT;
            }

            if (null != dishPicture && !"".equals(dishPicture.trim())) {
                dishVO.setDishPicture(dishPicture);
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
                this.setDishName("");
                this.setDishPrice(0F);
                this.setDishPicture("");
                renewPage();
            } else {
                this.setMessage(MessageUtil.getMessage("add_failure"));
                return INPUT;
            }
        } catch (DuplicateNameException e) {
            this.setMessage(MessageUtil.getMessage("duplicate_name_exception"));
            return INPUT;
        } catch (CategoryNotFoundException e) {
            this.setMessage(MessageUtil
                    .getMessage("category_not_found_exception"));
            return INPUT;
        } catch (DishNotFoundException e1) {
            this.setMessage(MessageUtil.getMessage("dish_not_found_exception"));
            return INPUT;
        }
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
            Dish dish1 = dishService.getDishById(Long.parseLong(id));
            // 查看菜品名称是否已存在
            Dish dish2 = dishService.getDishByName(dishName);
            // 可以找到，而且和自己的名字不同，则说明已经被占用
            if (null != dish2 && !dish1.getId().equals(dish2.getId())) {
                this.setMessage(MessageUtil.getMessage("dish_name_occupy"));
                return INPUT;
            }

            Dish dish = null;
            DishVO dishVO = null;
            if (null != id) {
                dish = dishService.getDishById(Long.parseLong(id));
            }

            if (null != dish) {
                dishVO = new DishVO();
                dishVO.setId(dish.getId());
            }

            if (null != dishName && !"".equals(dishName.trim())) {
                dishVO.setDishName(dishName);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_name_empty"));
                return INPUT;
            }

            if (0 != dishPrice) {
                dishVO.setDishPrice(dishPrice);
            } else {
                this.setMessage(MessageUtil.getMessage("dish_desc_empty"));
                return INPUT;
            }

            dishService.updateDish(dishVO);
        } catch (DuplicateNameException e) {
            this.setMessage(MessageUtil.getMessage("duplicate_name_exception",
                    dishName));
            return INPUT;
        } catch (CategoryNotFoundException e) {
            this.setMessage(MessageUtil
                    .getMessage("category_not_found_exception"));
            return INPUT;
        } catch (DishNotFoundException e1) {
            this.setMessage(MessageUtil.getMessage("dish_not_found_exception",
                    id.toString()));
            return INPUT;
        }
        this.setMessage(MessageUtil.getMessage("update_success"));
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

    /**
     * 
     */
    public void renewPage() {
        renewCategoryVOList();

        if (null == dishPicture || "".equals(dishPicture)) {
            this.setDishPicture(Constants.DEFAULT_DISH_PIC);
        }

        // 更新页面数据
        refreshPageData();
    }

    public void renewCategoryVOList() {
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        // 更新权限列表
        CategoryVO categoryVO = null;
        // CategoryVO rootCategoryVO = new CategoryVO();
        // rootCategoryVO.setStringId("0");
        // rootCategoryVO.setCategoryName(MessageUtil.getMessage("dish_root"));
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

    /**
     * 删除功能
     * 
     * @return
     */
    public String remove() {
        // 更新页面数据
        refreshPageData();

        // if (null != id) {
        // 先判断角色功能关联关系，如果此功能已授权给某个角色，则不能删除
        // Dish dish = dishService.getDishById(Long.parseLong(id));
        // Set<Role> roles = dish.getRoles();
        // if (null != roles && 0 < roles.size()) {
        // this.setMessage(MessageUtil.getMessage("dish_using"));
        //
        // // 更新数据
        // refreshPageData();
        // // 更新数据列表
        // refreshDataList();
        //
        // return INPUT;
        // }
        // }

        if (null != id) {
            try {
                dishService.deleteDish(Long.parseLong(id));
            } catch (DishNotFoundException e1) {
                this.setMessage(MessageUtil.getMessage(
                        "dish_not_found_exception", id));
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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishPicture() {
        return dishPicture;
    }

    public void setDishPicture(String dishPicture) {
        this.dishPicture = dishPicture;
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