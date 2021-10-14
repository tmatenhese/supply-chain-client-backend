package com.supplychain.supplychain.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.util.Properties;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supplychain.supplychain.dao.NetworkDao;
import com.supplychain.supplychain.dao.OrganisationDao;
import com.supplychain.supplychain.dao.Response;
import com.supplychain.supplychain.dao.UserDao;

@Service
public class AdminRegistrationService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	OrganisationDao organisationDao;

	public Response registerAdmin() throws IOException, CryptoException, InvalidArgumentException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, EnrollmentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, CertificateException {
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

			// Check to see if we've already enrolled the admin user.
			
			if(organisationDao==null || userDao==null) {
				throw new RuntimeException("Some values not set properly");
			}
			
			if (wallet.get(userDao.getAdminUserName()) != null) {
				registerResponse.setStatusCode("409");
				registerResponse.setStatusMessage("An identity for the admin user " + userDao.getAdminUserName()
						+ " already exists in the wallet");
				return registerResponse;
			}

			// Enroll the admin user, and import the new identity into the wallet.
			final EnrollmentRequest request = getEnrollmentRequest();
			Enrollment enrollment = caClient.enroll(userDao.getAdminUserName(), userDao.getAdminPassword(), request);
			Identity user = Identities.newX509Identity(organisationDao.getMspId(), enrollment);
			wallet.put(userDao.getAdminUserName(), user);
			registerResponse.setStatusCode("201");
			registerResponse.setStatusMessage(
					"Successfully enrolled user " + userDao.getAdminUserName() + " and imported it into the wallet");
			return registerResponse;

	}

	public EnrollmentRequest getEnrollmentRequest() {
		final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
		enrollmentRequestTLS.addHost("localhost");
		enrollmentRequestTLS.setProfile("tls");
		return enrollmentRequestTLS;
	}
}
