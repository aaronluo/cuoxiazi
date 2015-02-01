package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DishVO extends BaseVO {

    private String name;
    private float price;
    private String picPath;
    private boolean onSell;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    public DishVO(Integer id, String name, float price, String picPath,
            boolean onSell) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.picPath = picPath;
        this.onSell = onSell;
    }

    public DishVO() {
        super();
    }
}
