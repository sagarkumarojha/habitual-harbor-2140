package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BusException;
import com.app.exception.CustomerException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;
import com.app.service.BusService;
import com.app.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public")
public class OpenControllers {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private BusService busService;
	
	
	

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(Authentication auth) throws CustomerException{
		
		
		 Customer customer= customerRepository.findByEmail(auth.getName());	
		 
		 if (customer!=null) {
			throw new CustomerException("Invalid Input");
		}
		 
		 return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
		
		
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Customer> saveUser(@Valid @RequestBody Customer user) throws CustomerException {
		
		Customer savedUser= customerService.createCustomer(user);
		
		return new ResponseEntity<Customer>(savedUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/bus")
	public ResponseEntity<Bus> getBusesById(@RequestParam Integer busId) throws BusException{
		
		Bus bus=busService.viewBus(busId);
		
		return new ResponseEntity<Bus>(bus,HttpStatus.OK);
	}
	
	
	@GetMapping("/buses/{busType}")
	public ResponseEntity<List<Bus>> getBusesByType(@PathVariable("busType") String busType) throws BusException{
		
		List<Bus> listOfBuses=busService.viewBusByType(busType);
		
		return new ResponseEntity<List<Bus>>(listOfBuses,HttpStatus.OK);
	}
	
	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> getAllBuses() throws BusException{
		
		List<Bus> listOfBuses=busService.viewAllBuses();
		
		return new ResponseEntity<List<Bus>>(listOfBuses,HttpStatus.OK);
	}

}
