/***********************************************
 * Filename    : CategoryDataBean.java 
 * Copyright   : Copyright (c) 2014
 * Company     : Innovaee
 * Created : 12/20/2014
 ************************************************/

package com.innovaee.eorder.mobile.databean;


/**
 * 菜品分类数据Bean
 * 
 * 
 */
public class CategoryDataBean implements Serializable {
    //序列化id
    private static final long serialVersionUID = 1L;

    // 表单名定义
    public static final String TABLE = "category";

    // 分类id定义
    public static final String COLUM_ID = "id";

    // 分类名字定义
    public static final String COLUM_NAME = "name";

    // 分类背景图片定义
    public static final String COLUM_BITMAPUIL = "url";

    // 分类id
    private int id;

    // 分类名字
    private String name;

    // 分类背景图片
    private String bitmapUrl;

    /**
     * 构造函数
     */
    public CategoryDataBean() {
    }

    /**
     * 构造函数
     * @param id 分类id
     * @param name 分类名
     * @param bitmapUrl 分类图片的url地址
     */
    public CategoryDataBean(int id, String name, String bitmapUrl) {
        this.id = id;
        this.name = name;
        this.bitmapUrl = bitmapUrl;
    }

    /**
     * 获取分类id
     * 
     * @return 分类id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置分类id
     * 
     * @param id
     *            分类id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取分类名
     * 
     * @return 分类名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置分类名
     * 
     * @param name
     *            分类名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类图片url地址
     * 
     * @return 图片url
     */
    public String getBitmapUrl() {
        return this.bitmapUrl;
    }

    /**
     * 设置分类图片url地址
     * 
     * @param bitmapUrl
     *            图片url地址
     */
    public void setBitmapUrl(String bitmapUrl) {
        this.bitmapUrl = bitmapUrl;
    }
}
