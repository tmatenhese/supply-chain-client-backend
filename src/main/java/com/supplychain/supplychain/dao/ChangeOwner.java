package com.supplychain.supplychain.dao;

public class ChangeOwner {
	
	private String newOwner;
	private String productCode;
	
	public ChangeOwner(String newOwner, String productCode) {
		super();
		this.newOwner = newOwner;
		this.productCode = productCode;
	}
	public String getNewOwner() {
		return newOwner;
	}
	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@Override
	public String toString() {
		return "ChangeOwner [newOwner=" + newOwner + ", productCode=" + productCode + "]";
	}

}
