package com.supplychain.supplychain.dao;

import java.util.Set;

import org.hyperledger.fabric.sdk.Enrollment;

public class FabricUser implements org.hyperledger.fabric.sdk.User{
	
	private String name;
	private Set<String> roles;
	private String account;
	private String affiliation;
	private Enrollment enrollment;
	private String mspId;

	public FabricUser() {
		super();
	}

	public FabricUser(String name, String affiliation, Enrollment enrollment, String mspId) {
		super();
		this.name = name;
		this.affiliation = affiliation;
		this.enrollment = enrollment;
		this.mspId = mspId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<String> getRoles() {
		return roles;
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public String getAffiliation() {
		return affiliation;
	}

	@Override
	public Enrollment getEnrollment() {
		return enrollment;
	}

	@Override
	public String getMspId() {
		return mspId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public void setMspId(String mspId) {
		this.mspId = mspId;
	}

}
