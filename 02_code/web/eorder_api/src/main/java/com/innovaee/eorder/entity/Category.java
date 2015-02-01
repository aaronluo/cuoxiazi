/***********************************************
 * Filename        : Category.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.entity;

import java.util.List;

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
    private String name;

    /** 分类图片地址 */
    private String picPath;

    /** 菜品列表 */
    private List<Dish> dishes;

    public Category() {
    }

    public Category(String name, String picPath) {
        this.name = name;
        this.picPath = picPath;
    }

    @Basic
    @Column(name = "CATEGORY_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CATEGORY_PICTURE")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @OneToMany(targetEntity = Dish.class, fetch = FetchType.LAZY, mappedBy = "category")
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}