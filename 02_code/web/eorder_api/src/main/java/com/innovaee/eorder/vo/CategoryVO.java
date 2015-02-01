package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CategoryVO extends BaseVO{

    private String name;
    
    private String picPath;

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

    public CategoryVO() {
        super();
    }

    public CategoryVO(Integer id, String name, String picPath) {
        super();
        this.id = id;
        this.name = name;
        this.picPath = picPath;
    }
}
