/***********************************************
 * Filename        : NewOrderVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/07/2015
 ************************************************/

package com.innovaee.eorder.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @Title: NewOrderVO
 * @Description: 新订单值对象，该对象用于移动端下单信息传递给服务端
 * 
 * @version V1.0
 */
@XmlRootElement
public class NewOrderVO extends BaseVO {
    /**用餐桌号**/
    private Integer tableNumber;
    /**用餐人数**/
    private Integer attendeeNumber;
    /**服务员工号**/
    private Integer serventId;
    /**会员ID**/
    private Integer memberId;
    /**订单明细**/
    private List<NewOrderItemVO> items;

    /**无参数构造函数**/
    public NewOrderVO() {
        super();
    }
    /**有参数构造函数**/
    public NewOrderVO(Integer tableNumber, Integer attendeeNumber,
            Integer serventId, Integer memberId, List<NewOrderItemVO> items) {
        super();
        this.tableNumber = tableNumber;
        this.attendeeNumber = attendeeNumber;
        this.serventId = serventId;
        this.memberId = memberId;
        this.items = items;
    }


    /**下面为该对象属性setter/getter函数组**/
    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getAttendeeNumber() {
        return attendeeNumber;
    }

    public void setAttendeeNumber(Integer attendeeNumber) {
        this.attendeeNumber = attendeeNumber;
    }

    public Integer getServentId() {
        return serventId;
    }

    public void setServentId(Integer serventId) {
        this.serventId = serventId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public List<NewOrderItemVO> getItems() {
        return items;
    }
    
    public void setItems(List<NewOrderItemVO> items) {
        this.items = items;
    }


    /**重载toString函数**/
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
