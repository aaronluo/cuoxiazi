/***********************************************
 * Filename        : Category.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: Category
 * @Description: 菜品分类的实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "t_category")
@XmlRootElement
public class Category extends BaseEntity {

    @Override
    public Serializable getPK() {
        return categoryId;
    }

    /** 用户id, 不能为空, 必须唯一 */
    @Id
    @Column(name = "category_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;

    /** 分类名称 */
    @Column(name = "category_name")
    private String categoryName;

    /** 分类图片地址 */
    @Column(name = "category_picture")
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

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName="
                + categoryName + ", categoryPicture=" + categoryPicture
                + ", createAt=" + this.getCreateAt() + ", updateAt="
                + this.getUpdateAt() + "]";
    }

}