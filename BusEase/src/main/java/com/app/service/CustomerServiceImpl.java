package com.app.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.CustomerException;
import com.app.model.Customer;
import com.app.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository userDao;

	
	@Override
	public Customer createCustomer(Customer user) throws CustomerException {
		
		Customer existingUser= userDao.findByMobileNumber(user.getMobileNumber());
		
		if(existingUser != null) 
			throw new CustomerException("User already registered with this Mobile number!");
			
		
		return userDao.save(user);
	
	}
	@Override
	public Customer updateCustomer(Customer user) throws CustomerException {  
		
		
		Customer existingUser= userDao.findByMobileNumber(user.getMobileNumber());
		
		
		if(existingUser !=null) {
			return userDao.save(user);
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
