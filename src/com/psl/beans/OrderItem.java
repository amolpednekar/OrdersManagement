package com.psl.beans;

public class OrderItem {

	private Integer orderno;
	private Integer stockId;
	private Integer qty;
		
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderItem [orderno=" + orderno + ", stockId=" + stockId
				+ ", qty=" + qty + "]";
	}

	public OrderItem(Integer orderno, Integer stckId, Integer qty) {
		super();
		this.orderno = orderno;
		this.stockId = stckId;
		this.qty = qty;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}

