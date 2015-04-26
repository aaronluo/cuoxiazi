package com.innovaee.eorder.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 用户的菜单
 * 
 * @author kenshin
 * 
 */
@Entity
@Table(name = "SMS_MENU")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu {

    private Integer id;
    private String name;
    private String link;
    private Integer grade; // 菜单等级
    private Integer morder; // 同一级菜单中的顺序

    private Menu parentMenu;
    // 子菜单列表
    private List<Menu> childMenu = new ArrayList<Menu>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "GRADE")
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "MORDER")
    public Integer getMorder() {
        return morder;
    }

    public void setMorder(Integer morder) {
        this.morder = morder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }

    public Menu() {
        super();
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    public Menu getParentMenu() {
        return parentMenu;
    }

    @OneToMany(targetEntity = Menu.class, cascade = { CascadeType.ALL }, mappedBy = "parentMenu")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("morder")
    public List<Menu> getChildMenu() {
        return childMenu;
    }

}