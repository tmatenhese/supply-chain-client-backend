package com.supplychain.supplychain.dao;

import java.security.PrivateKey;

import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;

public class FabricEnrollment implements Enrollment {
	private X509Identity adminIdentity;
	public FabricEnrollment(X509Identity adminIdentity) {
		this.adminIdentity = adminIdentity;
	}

	@Override
	public PrivateKey getKey() {
		return adminIdentity.getPrivateKey();
	}

	@Override
	public String getCert() {
		return Identities.toPemString(adminIdentity.getCertificate());	
		
	}
}
