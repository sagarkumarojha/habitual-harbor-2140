package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.CustomerException;
import com.app.Service.CustomerService;
import com.app.model.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService userService;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addUserHandler(@RequestBody Customer customer) throws CustomerException{
		
		Customer customer2 = userService.createCustomer(customer);
		
		return new ResponseEntity<>(customer2,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/users")
	public  ResponseEntity<Customer> updateUser(@Valid @RequestBody Customer user) throws CustomerException {
		
		Customer updatedUser= userService.updateCustomer(user);
				
		return new ResponseEntity<Customer>(updatedUser,HttpStatus.OK);
		
	}

}
