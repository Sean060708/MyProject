package com.Ispan.bean;

import java.util.Date;

public class OrdersManageBean {

	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(int shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	
	public OrdersManageBean(int orderId, int buyerId, int sellerId, int orderStatus, String shippingName,
			String shippingAddress, String shippingTel, String productName, int shippingMethod, int quantity,
			String paymentStatus, int paymentAmount, Date createdAt) {
		super();
		this.orderId = orderId;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.orderStatus = orderStatus;
		this.shippingName = shippingName;
		this.shippingAddress = shippingAddress;
		this.shippingTel = shippingTel;
		this.productName = productName;
		this.shippingMethod = shippingMethod;
		this.quantity = quantity;
		this.paymentStatus = paymentStatus;
		this.paymentAmount = paymentAmount;
		this.createdAt = createdAt;
	}

	private int orderId;
    private int buyerId;
    private int sellerId;
    private int orderStatus;
    private String shippingName;
    private String shippingAddress;
    private String shippingTel;
    private String productName;
    private int shippingMethod;
    private int quantity;
    private String paymentStatus;
    private int paymentAmount;
    private Date createdAt;
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


	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getShippingTel() {
		return shippingTel;
	}
	public void setShippingTel(String shippingTel) {
		this.shippingTel = shippingTel;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public OrdersManageBean() {
		super();
	}
    
}
