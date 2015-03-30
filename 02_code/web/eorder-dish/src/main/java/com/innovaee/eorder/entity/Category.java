/***********************************************
 * Filename        : Category.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import org.hibernate.annotations.Where;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @Title: Category
 * @Description: 菜品分类的实体
 * 
 * @version V1.0
 */
@Entity
@Table(name = "T_CATEGORY")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 分类名称 */
    private String categoryName;

    /** 分类图片地址 */
    private String categoryPicture;

    /** 菜品列表 */
    private Set<Dish> dishes;

    /**
     * 默认构造函数
     */
    public Category() {
    }

    /**
     * 构造函数
     * 
     * @param name
     *            分类名称
     * @param picPath
     *            分类图片地址
     */
    public Category(String categoryName, String categoryPicture) {
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
    }

    @Basic
    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "CATEGORY_PICTURE")
    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

    @OneToMany(targetEntity = Dish.class, fetch = FetchType.EAGER, mappedBy = "category")
    @Where(clause = "ON_SELL=1")
    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

}