package com.supplychain.supplychain.dao;

public class Response {
	private String statusCode;
	private String statusMessage;
	
	public Response(String statusCode,String statusMessage) {
		this.statusCode=statusCode;
		this.statusMessage=statusMessage;
	}
	
	
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
}
