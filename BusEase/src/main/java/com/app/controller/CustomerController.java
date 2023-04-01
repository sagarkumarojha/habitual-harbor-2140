package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.CustomerService;
import com.app.exception.CustomerException;
import com.app.model.Customer;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;

@SecurityRequirements
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService userService;
	
	@PutMapping("/users")
	public  ResponseEntity<Customer> updateUser(@Valid @RequestBody Customer user) throws CustomerException {
		
		Customer updatedUser= userService.updateCustomer(user);
				
		return new ResponseEntity<Customer>(updatedUser,HttpStatus.OK);
		
	}

}
