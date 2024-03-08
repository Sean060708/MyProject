package com.Ispan.bean;

import java.util.Date;

public class OrdersBean {
	private int orderId;
    private int buyerId;
    private int sellerId;
    private int shippingDetailsId;
    private int paymentDetailsId;
    public int getPaymentDetailsId() {
		return paymentDetailsId;
	}
	public void setPaymentDetailsId(int paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
	private int orderStatus;
    public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	private Date createdAt;
    private Date modifiedAt;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getShippingDetailsId() {
		return shippingDetailsId;
	}
	public void setShippingDetailsId(int shippingDetailsId) {
		this.shippingDetailsId = shippingDetailsId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public OrdersBean() {
		super();
	}
    
}
