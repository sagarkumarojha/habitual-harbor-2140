package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.CustomerException;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;
@RestController
public class Login {
	
	

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/login")
	public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth) throws CustomerException{
		
		
		Optional< Customer> customer= customerRepository.findByEmail(auth.getName());	
		 
		 if (customer.isEmpty()) {
			throw new CustomerException("Invalid Input");
		}
		 
		 return new ResponseEntity<>(customer.get(), HttpStatus.ACCEPTED);
		
		
	}
	


}
