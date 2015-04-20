/***********************************************
 * Filename       : UserLevelVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/19/2015
 ************************************************/

package com.innovaee.eorder.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @Title: UserLevelVO
 * @Description: 会员等级值对象
 * 
 * @version V1.0
 */
public class UserLevelVO extends BaseVO {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    private String name;
    
    private Integer levelScore;
    
    private Float discount;
    
    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(Integer levelScore) {
        this.levelScore = levelScore;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
