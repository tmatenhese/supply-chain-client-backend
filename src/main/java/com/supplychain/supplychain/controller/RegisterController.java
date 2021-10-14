package com.supplychain.supplychain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.supplychain.supplychain.dao.OrganisationDao;
import com.supplychain.supplychain.service.AdminRegistrationService;
import com.supplychain.supplychain.service.ClientRegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/register")
public class RegisterController {
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
	}
	private final AdminRegistrationService adminRegistrationService;
	private final ClientRegistrationService clientRegistrationService;	
	@Autowired
	public RegisterController(AdminRegistrationService adminRegistrationService, ClientRegistrationService clientRegistrationService) {
		this.adminRegistrationService=adminRegistrationService;
		this.clientRegistrationService=clientRegistrationService;
	}

    @PostMapping("/admin")
    ResponseEntity registerAdmin() {
    	try {
    		return ResponseEntity.ok().body( adminRegistrationService.registerAdmin());    		
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());    		
    	}    	
    }
    @PostMapping("/client")
    ResponseEntity registerClient() {
    	try {
    		return ResponseEntity.ok().body(clientRegistrationService.register());    		
    	}catch(Exception e) {
    		return ResponseEntity.internalServerError().body(e.getMessage());    		
    	}
    }      
}