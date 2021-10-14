package com.supplychain.supplychain.dao;


public class Product {
	
	private  String id;
	private  String description;
	private  String variant;
	private  String prize;
	private  String quantity;
	private  String owner;
	private String status;
	private String productCode;
	
	
	public Product() {
		super();
	}
	public Product(String id, String description, String variant, String prize, String quantity, String owner,
			String status, String productCode) {
		super();
		this.id = id;
		this.description = description;
		this.variant = variant;
		this.prize = prize;
		this.quantity = quantity;
		this.owner = owner;
		this.status = status;
		this.productCode = productCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", variant=" + variant + ", prize=" + prize
				+ ", quantity=" + quantity + ", owner=" + owner + ", status=" + status + ", productCode=" + productCode
				+ "]";
	}
	
	
}
