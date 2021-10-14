package com.supplychain.supplychain.service;

import java.nio.file.Paths;
import java.util.Properties;

import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.dao.FabricEnrollment;
import com.supplychain.supplychain.dao.FabricUser;
import com.supplychain.supplychain.dao.NetworkDao;
import com.supplychain.supplychain.dao.OrganisationDao;
import com.supplychain.supplychain.dao.Response;
import com.supplychain.supplychain.dao.UserDao;

@Service
public class ClientRegistrationService {
	@Autowired
	UserDao userDao;
	@Autowired
	OrganisationDao organisationDao;
	public Response register() throws Exception {
		Response registerResponse = new Response("500", "Server Error");
			// Create a CA client for interacting with the CA.
			Properties props = new Properties();
			props.put("pemFile", "../../../fabric-samples/test-network/organizations/peerOrganizations/"
					+ organisationDao.getCertificatePath());
			props.put("allowAllHostNames", "true");
			HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
			CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
			caClient.setCryptoSuite(cryptoSuite);

			// Create a wallet for managing identities
			Wallet wallet = Wallets.newFileSystemWallet(Paths.get("wallet"));

			
			if(organisationDao==null || userDao==null) {
				throw new RuntimeException("Some values not set properly");
			}
			
			// Check to see if we've already enrolled the user.
			if (wallet.get(userDao.getClientUserName()) != null) {
				registerResponse.setStatusCode("409");
				registerResponse.setStatusMessage(
						"An identity for the user " + userDao.getClientUserName() + " already exists in the wallet");
				return registerResponse;
			}

			X509Identity adminIdentity = (X509Identity) wallet.get("admin");
			if (adminIdentity == null) {
				registerResponse.setStatusCode("412");
				registerResponse.setStatusMessage("Admin user needs to be enrolled and added to the wallet first");
				return registerResponse;
			}
			Enrollment enrollment = new FabricEnrollment(adminIdentity);
			User admin = new FabricUser(userDao.getAdminUserName(), organisationDao.getAffiliation(), enrollment,
					organisationDao.getMspId());

			// Register the user, enroll the user, and import the new identity into the
			// wallet.
			RegistrationRequest registrationRequest = new RegistrationRequest(userDao.getClientUserName());
			registrationRequest.setAffiliation(organisationDao.getAffiliation());
			registrationRequest.setEnrollmentID(userDao.getClientUserName());
			String enrollmentSecret = caClient.register(registrationRequest, admin);
			caClient.enroll(userDao.getClientUserName(), enrollmentSecret);
			Identity user = Identities.newX509Identity(organisationDao.getMspId(), adminIdentity.getCertificate(),
					adminIdentity.getPrivateKey());
			wallet.put(userDao.getClientUserName(), user);
			registerResponse.setStatusCode("201");
			registerResponse.setStatusMessage(
					"Successfully enrolled user " + userDao.getClientUserName() + " and imported it into the wallet");

			return registerResponse;
	}
}
