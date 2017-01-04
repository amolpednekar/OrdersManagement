package com.psl.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PurchaseOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5843755628012663124L;
	
	private Integer purchOrder;
	private Integer orderNo;
	private Date orderDate;
	private Date shipdate;
	transient private List<OrderItem> ordList;
	
	public PurchaseOrder() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseOrder(Integer orderNo, Date orderDate, Date shipdate) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.shipdate = shipdate;
	}

	public Integer getPurchOrder() {
		return purchOrder;
	}

	public void setPurchOrder(Integer purchOrder) {
		this.purchOrder = purchOrder;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShipdate() {
		return shipdate;
	}

	public void setShipdate(Date shipdate) {
		this.shipdate = shipdate;
	}

	public List<OrderItem> getOrdList() {
		return ordList;
	}

	public void setOrdList(List<OrderItem> ordList) {
		this.ordList = ordList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [purchOrder=" + purchOrder + ", orderNo="
				+ orderNo + ", orderDate=" + orderDate + ", shipdate="
				+ shipdate + ", ordList=" + ordList + "]";
	}
	
	
}
