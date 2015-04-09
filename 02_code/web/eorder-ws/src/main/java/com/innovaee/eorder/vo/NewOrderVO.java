/***********************************************
 * Filename        : NewOrderVO.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/07/2015
 ************************************************/

package com.innovaee.eorder.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * @Title: NewOrderVO
 * @Description: 新订单值对象，该对象用于移动端下单信息传递给服务端
 * 
 * @version V1.0
 */
@XmlRootElement
public class NewOrderVO extends BaseVO {
    /**订单序号 **/
    @XmlTransient
   private String  orderSeq;
    /**收银员ID**/
    @XmlTransient
    private Long cashierId;
    /**订单创建时间 **/
    @XmlTransient
    private Date createAtMin;
    @XmlTransient
    private Date createAtMax;
    /**订单状态 **/
    @XmlTransient
    private int status;
    /**订单总价最小值 **/
    @XmlTransient
    private float totalPriceMin;
    /**订单总价最大值 **/
    @XmlTransient
    private float totalPriceMax;
    /**会员手机号 **/
    @XmlTransient
    private String  cellphone;
    /**用餐桌号**/
    private Integer tableNumber;
    /**用餐人数**/
    private Integer attendeeNumber;
    /**服务员ID**/
    private Long serventId;
    /**会员ID**/
    private Long memberId;
    /**订单明细**/
    private List<NewOrderItemVO> items;
    
    

    /**无参数构造函数**/
    public NewOrderVO() {
        super();
    }
    /**有参数构造函数**/
    public NewOrderVO(Integer tableNumber, Integer attendeeNumber,
            Long serventId, Long memberId, List<NewOrderItemVO> items) {
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

    public String getCellphone() {
        return cellphone;
    }
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public Long getServentId() {
        return serventId;
    }

    public void setServentId(Long serventId) {
        this.serventId = serventId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<NewOrderItemVO> getItems() {
        return items;
    }
    
    public void setItems(List<NewOrderItemVO> items) {
        this.items = items;
    }
    
    public String getOrderSeq() {
        return orderSeq;
    }
    public void setOrderSeq(String orderSeq) {
        this.orderSeq = orderSeq;
    }
    
    public Long getCashierId() {
        return cashierId;
    }
    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }
    
    public Date getCreateAtMin() {
        return createAtMin;
    }
    public void setCreateAtMin(Date createAtMin) {
        this.createAtMin = createAtMin;
    }
    public Date getCreateAtMax() {
        return createAtMax;
    }
    public void setCreateAtMax(Date createAtMax) {
        this.createAtMax = createAtMax;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public float getTotalPriceMin() {
        return totalPriceMin;
    }
    public void setTotalPriceMin(float totalPriceMin) {
        this.totalPriceMin = totalPriceMin;
    }
    public float getTotalPriceMax() {
        return totalPriceMax;
    }
    public void setTotalPriceMax(float totalPriceMax) {
        this.totalPriceMax = totalPriceMax;
    }
    /**重载toString函数**/
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
    
    public boolean isEmpty() {
        
        if(orderSeq != null && !this.orderSeq.isEmpty()) {
            return false;
        }
        
        if(this.serventId != null && this.serventId > 0L) {
            return false;
        }
        
        if(this.memberId != null && this.memberId > 0L) {
            return false;
        }
        
        if(this.cashierId !=null && this.cashierId > 0L) {
            return false;
        }
        
        if(this.createAtMin != null) {
            return false;
        }
        
        if(this.createAtMax != null) {
            return false;
        }
        
        if(this.status > 0) {
            return false;
        }
        
        if(this.totalPriceMin > 0) {
            return false;
        }
        
        if(this.totalPriceMax > 0) {
            return false;
        }
        return true;
    }
}
