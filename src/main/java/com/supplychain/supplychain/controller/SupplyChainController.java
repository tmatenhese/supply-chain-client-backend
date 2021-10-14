package com.supplychain.supplychain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supplychain.supplychain.dao.Organisation;
import com.supplychain.supplychain.dao.OrganisationDao;
import com.supplychain.supplychain.dao.Product;
import com.supplychain.supplychain.service.ProductService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product")
public class SupplyChainController {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}
	@Autowired
	public SupplyChainController(ProductService productService) {
		this.productService=productService;
	}
	
    private final ProductService productService;

    @PostMapping("/add-new")
    ResponseEntity addProducts(@RequestBody Product product) {
    	try {
        	Product newProduct = productService.addNewProduct(product);
        	return ResponseEntity.ok(newProduct);	
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());
    	}
    } 
    @PutMapping("/change-owner/{id}/{newOwner}")
    ResponseEntity changeOwnership(@PathVariable String id, @PathVariable String newOwner) {
    	System.out.println("Received id "+id +"new owner "+newOwner);
    	try {
        	Product newProduct = productService.changeOwnership(id,newOwner);
        	return ResponseEntity.ok(newProduct);
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());
    	}
    } 
    
    @PostMapping("/change-status/{id}/{status}")
    ResponseEntity changeStatus(@PathVariable String id, @PathVariable String status) {
    	System.out.println("Received id "+id +" status "+status);
    	try {
        	Product newProduct = productService.changeOwnership(id,status);
        	return ResponseEntity.ok(newProduct);
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());
    	}
    }    
    
    @GetMapping("/{productCode}")
    ResponseEntity queryProductByProductCode(@PathVariable String productCode) {
    	System.out.println("Received Product ID "+productCode);
    	try {
        	Product newProduct = productService.queryProductById(productCode);
        	return ResponseEntity.ok(newProduct);
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());
    	}
    }   
   
    @GetMapping("/")
    List<Product> listAlldProducts() {
    	try {
    		System.out.println("Getting Products");
        	List<Product> newProduct = productService.queryProductsByRange("","");
        	return newProduct;	
    	}catch(Exception e) {
    		System.out.println("Exception Getting Products\n");e.printStackTrace();
    		return new ArrayList<Product>();
    	}
    }    
}