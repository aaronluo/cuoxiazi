/***********************************************
 * Filename        : DishAction.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.action.dish;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
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
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.CategoryVO;
import com.innovaee.eorder.vo.DishVO;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * @Title: DishAction
 * @Description: 功能Action（查询和删除）
 *
 * @version V1.0
 */
@Conversion(conversions = { @TypeConversion(key = "dish.price", converter = "com.innovaee.eorder.utils.FloatConverter") })
public class DishAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Dish";

    /** 菜品名称 */
    private DishVO dish;

    /** 分类id */
    private Long categoryId;

    /** 菜品分类列表 */
    private List<Category> categoryList;

    /** 菜品分类列表 */
    private List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();

    /** 功能值对象列表 */
    private List<DishVO> dishvos = new ArrayList<DishVO>();

    /** 功能服务类对象 */
    @Resource
    private DishService dishService;

    /** 功能服务类对象 */
    @Resource
    private CategoryService categoryService;

    /**
     * 进入菜品页面
     * 
     * @return
     */
    public String dish() {
        dish = new DishVO();

        renewCategoryVOList();

        // 刷新系统菜单
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 进入菜品页面
     * 
     * @return
     */
    public String list() {
        dish = new DishVO();

        renewCategoryVOList();

        getDishList();
        // 刷新系统菜单
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 新增页面
     * 
     * @return
     */
    public String add() {
        // 刷新系统菜单
        refreshPageData();

        renewCategoryVOList();

        dish = new DishVO();

        if (null == dish.getPicPath() || "".equals(dish.getPicPath())) {
            dish.setPicPath(Constants.DEFAULT_DISH_PIC);
        }

        return SUCCESS;
    }

    /**
     * 保存功能
     * 
     * @return
     */
    public String save() {
        try {
            if (!checkDishVO()) {
                return ERROR;
            } else {
                dish.setCategoryId(categoryId);
                // 新增成功
                dishService.addDish(dish);
                this.setMessage(MessageUtil.getMessage("dish_save_success",
                        dish.getName()));
                dish = new DishVO();
                getDishList();// TODO
            }
        } catch (DuplicateNameException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } finally {
            renewCategoryVOList();
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
        //
        renewCategoryVOList();
        dish = new DishVO();
        try {
            if (null != id && !"".equals(id.trim())) {
                Dish dishDB = dishService.getDishById(Long.parseLong(id));
                dish.setId(dishDB.getId());
                dish.setName(dishDB.getName());
                dish.setPrice(dishDB.getPrice());
                dish.setPicPath(dishDB.getPicPath());
                dish.setOnSell(dishDB.isOnSell());
                dish.setName(dishDB.getName());
                dish.setCategoryId(dishDB.getCategory().getId());
            } else {
                this.setMessage("ERROR");
                return ERROR;
            }
        } catch (DishNotFoundException e) {
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
            // 校验输入的值
            if (!checkDishVO()) {
                return ERROR;
            } else {
                dish.setCategoryId(categoryId);
                // 新增成功
                dishService.updateDish(dish);
                this.setMessage(MessageUtil.getMessage("dish_update_success",
                        dish.getName()));
                dish = new DishVO();
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
        } catch (DishNotFoundException e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } finally {
            renewCategoryVOList();
            refreshPageData();
            getDishList();
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
                dishService.deleteDish(Long.parseLong(id));
                this.setMessage(MessageUtil.getMessage("delete_success"));
            } catch (DishNotFoundException e) {
                this.setMessage(e.getMessage());
                return ERROR;
            }
        }

        // 更新记录列表
        getDishList();
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
    private void getDishList() {
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
                dishvo.setId(dish.getId());
                dishvo.setName(dish.getName());
                dishvo.setPicPath(dish.getPicPath());
                dishvo.setPrice(dish.getPrice());
                id = dish.getCategory().getId();
                category = categoryService.getCategoryById(id);
                if (null != category) {
                    dishvo.setCategoryId(category.getId());
                }
                dishvos.add(dishvo);
            }
        } catch (PageIndexOutOfBoundExcpeiton e) {
            this.setMessage(e.getMessage());
        } catch (InvalidPageSizeException e) {
            this.setMessage(e.getMessage());
        } catch (CategoryNotFoundException e) {
            this.setMessage(e.getMessage());
        }
    }

    /**
     * 
     */
    private void renewCategoryVOList() {
        List<CategoryVO> categoryVOList = new ArrayList<CategoryVO>();
        // 更新权限列表
        CategoryVO categoryVO = null;
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
     * 检查一个菜品的设定是否合法
     */
    private boolean checkDishVO() {
        boolean isValidVO = true;

        if (null == dish) {
            isValidVO = false;
            addFieldError("dish.dishvo",
                    MessageUtil.getMessage("dish_invalid_vo"));
            return isValidVO;
        }

        if (dish.getPrice() < 1f || dish.getPrice() > 100000f) {
            isValidVO = false;
            addFieldError("dish.price",
                    MessageUtil.getMessage("dish_price_rule"));
        }

        if (StringUtil.isEmpty(dish.getName())) {
            isValidVO = false;
            addFieldError("dish.name", MessageUtil.getMessage("dish_name_rule"));
        }

        if (StringUtil.isEmpty(dish.getPicPath())) {
            isValidVO = false;
            addFieldError("dish.pic.path",
                    MessageUtil.getMessage("pic_path_rule"));
        }

        return isValidVO;
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

    public DishVO getDish() {
        return dish;
    }

    public void setDish(DishVO dish) {
        this.dish = dish;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    /** 代表上传文件的file对象 */
    private File file;

    /** 上传文件名 */
    private String fileFileName;

    /** 上传文件的MIME类型 */
    private String fileContentType;

    /** 上传文件的描述信息 */
    private String description;

    /** 保存上传文件的目录，相对于Web应用程序的根路径，在struts.xml文件中配置 */
    private String uploadDir;

    /** 保存的文件名 */
    private String newFileName;

    /** 保存页面的菜品Id */
    private Long dishId;

    /** 保存页面的菜品名称 */
    private String dishName;

    /** 保存页面的菜品价格 */
    private Float dishPrice;

    /** 保存页面的菜品价格 */
    private Long oldCategoryId;

    /** 上传文件的列表 */
    private List<String> dishImages;

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    /**
     * 上传图片
     * 
     * @return
     * @throws Exception
     */
    public String uploadImage() {
        // 得到当前时间自1970年1月1日0时0分0秒开始流逝的毫秒数，将这个毫秒数作为上传文件新的文件名。
        long now = new Date().getTime();

        // 得到保存上传文件的目录的真实路径
        String path = ServletActionContext.getServletContext().getRealPath(
                uploadDir);

        File dir = new File(path);
        // 如果这个目录不存在，则创建它。
        if (!dir.exists()) {
            dir.mkdir();
        }

        if (null != fileFileName && !"".equals(fileFileName.trim())) {
            int index = fileFileName.lastIndexOf('.');
            // 判断上传文件名是否有扩展名
            if (index != -1) {
                newFileName = now + fileFileName.substring(index);
            } else {
                newFileName = Long.toString(now);
            }
            this.setNewFileName(newFileName);

            BufferedOutputStream bos = null;
            BufferedInputStream bis = null;
            // 读取保存在临时目录下的上传文件，写入到新的文件中。
            try {
                FileInputStream fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);

                FileOutputStream fos = new FileOutputStream(new File(dir,
                        newFileName));
                bos = new BufferedOutputStream(fos);

                byte[] buf = new byte[4096];

                int len = -1;
                while ((len = bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
            } catch (FileNotFoundException e) {
                this.setMessage(MessageUtil.getMessage(
                        "file_not_found_fxception", fileFileName));
                return INPUT;
            } catch (IOException e) {
                this.setMessage(MessageUtil.getMessage("io_exception",
                        newFileName));
                return INPUT;
            } finally {
                refreshPageData();
                renewCategoryVOList();
                try {
                    if (null != bis) {
                        bis.close();
                    }
                    if (null != bos) {
                        bos.close();
                    }
                } catch (IOException e) {
                    this.setMessage(MessageUtil.getMessage("io_exception",
                            newFileName));
                    return INPUT;
                }
            }
        }

        if (null == dish) {
            dish = new DishVO();
        }

        if (null != dish) {
            if (null != dishId) {
                dish.setId(dishId);
                setId(dishId.toString());
            }
            dish.setName(dishName);
            if (null != dishPrice) {
                dish.setPrice(dishPrice);
            }
            if (null != oldCategoryId) {
                dish.setCategoryId(oldCategoryId);
                setCategoryId(oldCategoryId);
            }
            dish.setPicPath("/dish/" + newFileName);
        }
        this.setMessage(MessageUtil.getMessage("upload_picture_success_msg"));
        return SUCCESS;
    }

    public List<String> getDishImages() {
        return dishImages;
    }

    public void setDishImages(List<String> dishImages) {
        this.dishImages = dishImages;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getOldCategoryId() {
        return oldCategoryId;
    }

    public void setOldCategoryId(Long oldCategoryId) {
        this.oldCategoryId = oldCategoryId;
    }

}