package com.supplychain.supplychain.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class OrganisationDao {
	@Value( "${orgName:Org1}" )
	private String orgName;
	@Value( "${affiliation:org1.department1}" )
	private String affiliation;
	@Value( "${mspid:Org1MSP}" )
	private String mspId;
	@Value( "${orgType:Producer}" )
	private String orgType;
	@Value( "${certificationPath:org1.example.com//ca//ca.org1.example.com-cert.pem}" )
	private String certificatePath;
	@Value( "${organisation:org1.example.com}" )
	private String organisation;
	@Value( "${connectionFile:connection-org1.yaml}" )
	private String connectionFile;
	private Product product;
	private ChangeOwner changeOwner;
	public ChangeOwner getChangeOwner() {
		return changeOwner;
	}
	public void setChangeOwner(ChangeOwner changeOwner) {
		this.changeOwner = changeOwner;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "OrganisationDao [orgName=" + orgName + ", affiliation=" + affiliation + ", mspId=" + mspId
				+ ", orgType=" + orgType + ", certificatePath=" + certificatePath + ", organisation=" + organisation
				+ ", connectionFile=" + connectionFile
				+ ", product=" + product + ", changeOwner=" + changeOwner + "]";
	}
}
