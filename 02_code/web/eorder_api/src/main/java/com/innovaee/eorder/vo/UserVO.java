package com.innovaee.eorder.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserVO extends BaseVO {
    private String username;
    private String cellphone;
    private String levelName;
    private Float discount;

    public UserVO() {
        super();
    }

    public UserVO(String username, String cellphone, String levelName,
            Float discount) {
        super();
        this.username = username;
        this.cellphone = cellphone;
        this.levelName = levelName;
        this.discount = discount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

}