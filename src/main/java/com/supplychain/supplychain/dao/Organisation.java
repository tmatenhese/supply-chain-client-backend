package com.supplychain.supplychain.dao;

public class Organisation {
	
	private String orgName;
	private String affiliation;
	private String mspId;
	private String orgType;
	private String certificatePath;
	private String organisation;
	private String connectionFile;
	
	private String channel;
	private String contract;
	private String clientUserName;
	private String adminUserName;
	private String adminPassword;
	private  String id;
	private  String description;
	private  String variant;
	private  String prize;
	private  String quantity;
	private  String owner;
	private String status;
	private String productCode;
	
	private String newOwner;

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
	public String getClientUserName() {
		return clientUserName;
	}
	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getMspId() {
		return mspId;
	}
	public void setMspId(String mspId) {
		this.mspId = mspId;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getConnectionFile() {
		return connectionFile;
	}
	public void setConnectionFile(String connectionFile) {
		this.connectionFile = connectionFile;
	}
	@Override
	public String toString() {
		return "Organisation [orgName=" + orgName + ", affiliation=" + affiliation + ", mspId=" + mspId + ", orgType="
				+ orgType + ", certificatePath=" + certificatePath + ", organisation=" + organisation
				+ ", connectionFile=" + connectionFile + ", channel=" + channel + ", contract=" + contract
				+ ", clientUserName=" + clientUserName + ", adminUserName=" + adminUserName + ", adminPassword="
				+ adminPassword + ", id=" + id + ", description=" + description + ", variant=" + variant + ", prize="
				+ prize + ", quantity=" + quantity + ", owner=" + owner + ", status=" + status + ", productCode="
				+ productCode + ", newOwner=" + newOwner + "]";
	}

}
