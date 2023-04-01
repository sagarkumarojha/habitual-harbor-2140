package com.app.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.exception.CustomerException;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	
	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		
		Optional<Customer> existingUser= userDao.findByEmail(customer.getMobileNumber());
		
		if(existingUser.isPresent()) 
			
			throw new CustomerException("User already registered with this Mobile number!");

		
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		
		return userDao.save(customer);
	
	}
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {  
		
		
Optional<Customer> existingUser= userDao.findByEmail(customer.getMobileNumber());
		
	
		if(existingUser.isPresent()) {
			return userDao.save(customer);
		}
		else
			throw new CustomerException("Invalid User Details! please login first.");
	}
	
	
	@Override
	public Customer deleteCustomer(Integer userId) throws CustomerException {
		Customer user = userDao.findById(userId).orElseThrow(()-> new CustomerException("Invalid user Id!"));
		userDao.delete(user);
		return user;
		
		
	}
	@Override
	public Customer viewCustomerbyId(Integer userId) throws CustomerException {

		Customer user = userDao.findById(userId).orElseThrow(()-> new CustomerException("Invalid user Id!"));
		return user;
	}
	
	@Override
	public List<Customer> viewCustomers() throws CustomerException {
		
	
		
		List<Customer> userList = userDao.findAll();
		if(userList.isEmpty()) throw new CustomerException("No users found!");
		
		return userList;
	}
	

}
