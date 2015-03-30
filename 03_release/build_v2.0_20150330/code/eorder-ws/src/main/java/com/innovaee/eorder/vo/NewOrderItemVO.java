/***********************************************
 * Filename        : NewOrderItemVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/07/2015
 ************************************************/


package com.innovaee.eorder.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Title: NewOrderItemVO
 * @Description: 新订单明细值对象，改对象用于描述客户订单中菜品明细信息
 * 
 * @version V1.0
 */
@XmlRootElement
public class NewOrderItemVO extends BaseVO {
    /**菜品id**/
    private Long dishId;
    /**菜品份数**/
    private Integer dishAmount;

    /**无参数构造函数**/
    public NewOrderItemVO() {
        super();
    }
    /**有参数构造函数**/
    public NewOrderItemVO(Long dishId, Integer dishAmount) {
        super();
        this.dishId = dishId;
        this.dishAmount = dishAmount;
    }
    
    /**下面为该对象属性setter/getter函数组**/
    public Long getDishId() {
        return dishId;
    }
    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }
    public Integer getDishAmount() {
        return dishAmount;
    }
    public void setDishAmount(Integer dishAmount) {
        this.dishAmount = dishAmount;
    }
    /**重载toString函数**/
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
