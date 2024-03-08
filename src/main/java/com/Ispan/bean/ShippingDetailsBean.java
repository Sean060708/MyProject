package com.Ispan.bean;

import java.util.Date;

public class ShippingDetailsBean {
	private int shippingDetailsId;
	private int cartItemId;
    public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}
	private String name;
    private String address;
    private String tel;
    private int shippingMethod;
    private int shippingFee;
    private Date createdAt;
    private Date modifiedAt;
	public int getShippingDetailsId() {
		return shippingDetailsId;
	}
	public void setShippingDetailsId(int shippingDetailsId) {
		this.shippingDetailsId = shippingDetailsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(int shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public int getShippingFee() {
		return shippingFee;
	}
	public void setShippingFee(int shippingFee) {
		this.shippingFee = shippingFee;
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
	public ShippingDetailsBean() {
		super();
	}
    
}
