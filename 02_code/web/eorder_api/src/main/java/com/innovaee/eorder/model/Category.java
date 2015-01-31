package com.innovaee.eorder.model;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="T_CATEGORY")
public class Category extends BaseEntity{
    private static final long serialVersionUID = 1L;
    

    private String name;

    private String picPath;

    private Set<Dish> dishes;
    
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

    @OneToMany(targetEntity=Dish.class,
            fetch=FetchType.LAZY, mappedBy="category")
    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

}
