/***********************************************
 * Filename        : CategoryVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: CategoryVO
 * @Description: 分类值对象
 * 
 * @version V1.0
 */
@XmlRootElement
public class CategoryVO implements Serializable {

    /** 对象序列化ID */
    private static final long serialVersionUID = 6233142152909758256L;

    /** 分类ID */
    private Integer categoryId;

    /** 分类名称 */
    private String categoryName;

    /** 分类图片 */
    private String categoryPicture;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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