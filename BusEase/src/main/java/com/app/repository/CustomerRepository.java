package com.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByMobileNumber(String mobileNumber);
	
}
