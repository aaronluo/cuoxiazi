package com.innovaee.eorder.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_DISH")
public class Dish extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    private String picPath;

    private float price;

    private boolean onSell;

    private String misc;

    private Category category;

    public Dish() {
    }

    public Dish(String name, String picPath, float price) {
        super();
        this.name = name;
        this.picPath = picPath;
        this.price = price;
    }

    @Basic
    @Column(name = "DISH_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DISH_PICTURE")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Basic
    @Column(name = "DISH_PRICE")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "ON_SELL")
    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    @Basic
    @Column(name = "MISC")
    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    @ManyToOne(targetEntity = com.innovaee.eorder.model.Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}