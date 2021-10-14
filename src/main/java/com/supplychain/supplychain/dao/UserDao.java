package com.supplychain.supplychain.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class UserDao{
	@Value( "${clientUserName:appUser}" )
	private String clientUserName;
	@Value( "${adminUserName:admin}" )
	private String adminUserName;
	@Value( "${adminPassword:adminpw}" )
	private String adminPassword;

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
	@Override
	public String toString() {
		return "UserDao [clientUserName=" + clientUserName + ", adminUserName=" + adminUserName + ", adminPassword="
				+ adminPassword + "]";
	}
}
