package com.supplychain.supplychain.dao;

public enum OrganisationType {	
	Producer("Producer"),Wholesaler("Wholesaler"),Retailer("Retailer"),Authority("Authority");
	private String type;
	private OrganisationType(String type) {
		this.type=type;		
	}
	

}
