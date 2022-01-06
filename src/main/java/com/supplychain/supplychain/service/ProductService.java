package com.supplychain.supplychain.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supplychain.supplychain.dao.NetworkDao;
import com.supplychain.supplychain.dao.OrganisationDao;
import com.supplychain.supplychain.dao.Product;
import com.supplychain.supplychain.dao.ProductDao;
import com.supplychain.supplychain.dao.UserDao;

@Service
public class ProductService {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	UserDao userDao;
	@Autowired
	NetworkDao networkDao;
	@Autowired
	OrganisationDao organisationDao;	
	
	public Product addNewProduct(Product product) throws IOException, ContractException, TimeoutException, InterruptedException  {
		// Load a file system based wallet for managing identities.
		if(product==null) {
			throw new RuntimeException("Some Values have not been set");
		}
		Path walletPath = Paths.get("wallet");
		Wallet wallet = Wallets.newFileSystemWallet(walletPath);
		// load a CCP
		Path networkConfigPath = Paths.get("..","fabric-samples","test-network", "organizations", "peerOrganizations", organisationDao.getOrganisation(), organisationDao.getConnectionFile());

		Gateway.Builder builder = Gateway.createBuilder();
		builder.identity(wallet, userDao.getClientUserName()).networkConfig(networkConfigPath).discovery(true);

		// create a gateway connection
		try (Gateway gateway = builder.connect()) {

			// get the network and contract
			Network network = gateway.getNetwork(networkDao.getChannel());
			Contract contract = network.getContract(networkDao.getContract());

			byte[] result;		
			contract.submitTransaction("addNewProduct",product.getId(),product.getDescription(),product.getQuantity(),product.getPrize(),product.getVariant(),product.getOwner(),product.getStatus(),product.getProductCode());
			result = contract.evaluateTransaction("queryProductById", product.getId());
			Product newProduct = objectMapper.readValue(result, Product.class);
			System.out.println("new added product "+ new String(result));
			return newProduct;
		}			
	}
	
		public Product changeOwnership(String id, String newOwner) throws IOException, ContractException, TimeoutException, InterruptedException {
			// Load a file system based wallet for managing identities.
			if(networkDao==null || userDao==null) {
				throw new RuntimeException("Some Values have not been set");
			}
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallets.newFileSystemWallet(walletPath);
			// load a CCP
			Path networkConfigPath = Paths.get("..","fabric-samples","test-network", "organizations", "peerOrganizations", organisationDao.getOrganisation(), organisationDao.getConnectionFile());

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, userDao.getClientUserName()).networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			try (Gateway gateway = builder.connect()) {

				// get the network and contract
				Network network = gateway.getNetwork(networkDao.getChannel());
				Contract contract = network.getContract(networkDao.getContract());

				byte[] result;
				contract.submitTransaction("changeProductOwnership", id, newOwner);
				result = contract.evaluateTransaction("queryProductById", id);
				Product product = objectMapper.readValue(result, Product.class);
				System.out.println("Returned Product New Owner  " +new String(result));
				return product;
			}			
		}
		
		public Product changeStatus(String id,String status) throws IOException, ContractException, TimeoutException, InterruptedException {
			// Load a file system based wallet for managing identities.
			if(networkDao==null || userDao==null) {
				throw new RuntimeException("Some Values have not been set");
			}
			
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallets.newFileSystemWallet(walletPath);
			// load a CCP
			Path networkConfigPath = Paths.get("..","fabric-samples","test-network", "organizations", "peerOrganizations", organisationDao.getOrganisation(), organisationDao.getConnectionFile());

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, userDao.getClientUserName()).networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			try (Gateway gateway = builder.connect()) {

				// get the network and contract
				Network network = gateway.getNetwork(networkDao.getChannel());
				Contract contract = network.getContract(networkDao.getContract());

				byte[] result;
				contract.submitTransaction("changeProductStatus", id, status);
				result = contract.evaluateTransaction("queryProductById", id);
				Product product = objectMapper.readValue(result, Product.class);
				System.out.println("Product with new Owner "+new String(result));
				return product;
			}			
		}		
		
		public Product queryProductById(String productId) throws IOException, ContractException, TimeoutException, InterruptedException {
			// Load a file system based wallet for managing identities.
			
			if(productId==null || userDao.getClientUserName()==null || networkDao.getContract()==null) {
				throw new RuntimeException("Some Values have not been set");
			}
			productId = productId.trim();
			System.out.println("user "+userDao.getClientUserName() +" channel "+networkDao.getChannel());
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallets.newFileSystemWallet(walletPath);
			// load a CCP
			Path networkConfigPath = Paths.get("..","fabric-samples","test-network", "organizations", "peerOrganizations", organisationDao.getOrganisation(), organisationDao.getConnectionFile());

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, userDao.getClientUserName()).networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			try (Gateway gateway = builder.connect()) {

				// get the network and contract
				Network network = gateway.getNetwork(networkDao.getChannel());
				Contract contract = network.getContract(networkDao.getContract());
				System.out.println("B4 Query "+ productId);
				byte[] result= contract.evaluateTransaction("queryProductById", productId);
				Product product = objectMapper.readValue(result, Product.class);
				System.out.println("retrieved product "+new String(result));
				return product;
			}			
		}		
		
		public List<Product> queryProductsByRange(String startKey,String endKey) throws IOException, ContractException, TimeoutException, InterruptedException {
			// Load a file system based wallet for managing identities.
			
			if(userDao.getClientUserName()==null || networkDao.getContract()==null) {
				throw new RuntimeException("Some Values have not been set");
			}
			System.out.println("user "+userDao.getClientUserName() +" channel "+networkDao.getChannel());
			Path walletPath = Paths.get("wallet");
			Wallet wallet = Wallets.newFileSystemWallet(walletPath);
			// load a CCP
			Path networkConfigPath = Paths.get("..","fabric-samples","test-network", "organizations", "peerOrganizations", organisationDao.getOrganisation(), organisationDao.getConnectionFile());

			Gateway.Builder builder = Gateway.createBuilder();
			builder.identity(wallet, userDao.getClientUserName()).networkConfig(networkConfigPath).discovery(true);

			// create a gateway connection
			try (Gateway gateway = builder.connect()) {

				// get the network and contract
				Network network = gateway.getNetwork(networkDao.getChannel());
				Contract contract = network.getContract(networkDao.getContract());

				byte[] result= contract.evaluateTransaction("queryProductsByRange", startKey,endKey);
				System.out.println("Evaluate Transaction: getProductsByRange, result: " + new String(result));
				ProductDao[] productDao = objectMapper.readValue(result, ProductDao[].class);
				List<Product> productList = new ArrayList<>();
				for(ProductDao products : productDao) {
					productList.add(products.getRecord());
				}
				
				return productList;
			}			
		}		
}

