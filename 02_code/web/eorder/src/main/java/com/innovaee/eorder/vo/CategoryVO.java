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

    /** 分类名称 */
    private String name;

    /** 分类图片 */
    private String picPath;

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
     * @param name
     *            分类名称
     * @param picPath
     *            分类图片
     */
    public CategoryVO(Long id, String name, String picPath) {
        super();
        this.id = id;
        this.name = name;
        this.picPath = picPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

}