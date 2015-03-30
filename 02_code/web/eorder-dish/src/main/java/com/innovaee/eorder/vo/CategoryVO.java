/***********************************************
 * Filename       : CategoryVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: CategoryVO
 * @Description: 分类值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class CategoryVO extends BaseVO {

    /** 对象序列化ID */
    private static final long serialVersionUID = 4332034709631128295L;

    /** 分类名称 */
    private String categoryName;

    /** 分类图片地址 */
    private String categoryPicture;

    /**
     * 默认构造函数
     */
    public CategoryVO() {
        super();
    }

    /**
     * 构造函数
     * 
     * @param id
     *            ID
     * @param categoryName
     *            分类名称
     * @param categoryPicture
     *            分类图片
     */
    public CategoryVO(Long id, String categoryName, String categoryPicture) {
        super();
        this.id = id;
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

}