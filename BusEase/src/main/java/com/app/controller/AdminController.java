package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BusException;
import com.app.exception.CustomerException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.service.BusService;
import com.app.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CustomerService customerService;
	

	@Autowired
	private BusService busService;
	
	@DeleteMapping("/customers/{userId}")
	public  ResponseEntity<Customer> deleteUser(@PathVariable("userId") Integer userId ) throws CustomerException {
		
		Customer deletedUser= customerService.deleteCustomer(userId);
				
		return new ResponseEntity<Customer>(deletedUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{userId}")
	public  ResponseEntity<Customer> viewUser(@PathVariable("userId") Integer userId  ) throws CustomerException{
		
		Customer user= customerService.viewCustomerbyId(userId);
				
		return new ResponseEntity<Customer>(user,HttpStatus.OK);
		
	}
	
	@GetMapping("/customers")
	public  ResponseEntity<List<Customer>> viewAllUser() throws CustomerException {
		
		List<Customer> userList= customerService.viewCustomers();
				
		return new ResponseEntity<List<Customer>>(userList,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/bus")
	public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus bus) throws BusException{
		
		Bus newBus=busService.addBus(bus);
		
		return new ResponseEntity<Bus>(newBus,HttpStatus.OK);
	}
	
	@PutMapping("/bus")
	public ResponseEntity<Bus> updateBus(@Valid @RequestBody Bus bus) throws BusException{
		
		Bus newBus=busService.updateBus(bus);
		
		return new ResponseEntity<Bus>(newBus,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/bus/{busId}")
	public ResponseEntity<Bus> DeleteBus(@PathVariable Integer busId) throws BusException{
		
		Bus bus=busService.deleteBus(busId);
		
		return new ResponseEntity<Bus>(bus,HttpStatus.OK);
	}


}
