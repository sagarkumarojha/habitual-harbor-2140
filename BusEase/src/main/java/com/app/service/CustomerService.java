package com.app.service;

import java.util.List;

import com.app.exception.CustomerException;
import com.app.model.Customer;



public interface CustomerService {

	
	public Customer createCustomer(Customer user)throws CustomerException;
	
	public Customer updateCustomer(Customer user)throws CustomerException;
	
	public Customer deleteCustomer(Integer userId) throws CustomerException;
	
	public Customer viewCustomerbyId(Integer userId) throws CustomerException;
	
	public List<Customer> viewCustomers() throws CustomerException; 
	
}
