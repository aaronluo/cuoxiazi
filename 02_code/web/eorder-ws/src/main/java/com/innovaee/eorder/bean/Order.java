package com.innovaee.eorder.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_order")
@XmlRootElement
public class Order extends BaseEntity {

	@Override
	public Serializable getPK() {
		return orderId;
	}

	// 订单id, 不能为空, 必须唯一
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	// 订单序列号
	@Column(name = "order_seq")
	private String order_seq;

	// 桌号
	@Column(name = "table_number")
	private Integer tableNumber;

	// 就餐人数
	@Column(name = "attendee_number")
	private Integer attendeeNumber;

	// 密码
	@Column(name = "total_price")
	private float totalPrice;

	// 手机号码
	@Column(name = "cellphone")
	private String cellphone;

	// 点餐员ID
	@Column(name = "servent_id")
	private Integer serventId;

	// 用户ID（会员）
	@Column(name = "member_id")
	private Integer memberId;

	// 收银员ID
	@Column(name = "casher_id")
	private Integer casherId;

	// 创建时间
	@Column(name = "create_at")
	private Timestamp createAt;

	// 更新时间
	@Column(name = "update_at")
	private Timestamp updateAt;

	public Order() {
	}

	public Order(Integer orderId, String order_seq, Integer tableNumber,
			Integer attendeeNumber, float totalPrice, String cellphone,
			Integer serventId, Integer memberId, Integer casherId,
			Timestamp createAt, Timestamp updateAt) {
		super();
		this.orderId = orderId;
		this.order_seq = order_seq;
		this.tableNumber = tableNumber;
		this.attendeeNumber = attendeeNumber;
		this.totalPrice = totalPrice;
		this.cellphone = cellphone;
		this.serventId = serventId;
		this.memberId = memberId;
		this.casherId = casherId;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrder_seq() {
		return order_seq;
	}

	public void setOrder_seq(String order_seq) {
		this.order_seq = order_seq;
	}

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

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	public Integer getCasherId() {
		return casherId;
	}

	public void setCasherId(Integer casherId) {
		this.casherId = casherId;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", order_seq=" + order_seq
				+ ", tableNumber=" + tableNumber + ", attendeeNumber="
				+ attendeeNumber + ", totalPrice=" + totalPrice
				+ ", cellphone=" + cellphone + ", serventId=" + serventId
				+ ", memberId=" + memberId + ", casherId=" + casherId
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}

}