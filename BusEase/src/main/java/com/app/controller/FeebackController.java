package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.BusService;
import com.app.service.CustomerService;
import com.app.service.FeedbackService;
import com.app.exception.BusException;
import com.app.exception.CustomerException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.model.Feedback;
import com.app.repository.CustomerRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeebackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BusService busService;
	
	@PostMapping("/add/{busId}")
	public ResponseEntity<Feedback> addfeedbackHandler(Authentication auth,@PathVariable Integer busId, @RequestBody Feedback feedback) {
		String username=(String) auth.getPrincipal();
		Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new BusException("Please SignIn First"));
		Bus bus = busService.viewBus(busId);
		
		if(bus == null) {
			throw new BusException("Incorrect Bus Id");
		}
		
		Feedback feedback2 = feedbackService.addfeedback(busId, feedback, username);
		
		return new ResponseEntity<>(feedback2,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Feedback> updateFeedbackHandler( @Valid @RequestBody Feedback feedback){
		
		Feedback f = feedbackService.updatefeedback(feedback);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/view/{feedbackId}")
	public ResponseEntity<Feedback> viewFeedbackHandler(@PathVariable("feedbackId") Integer feedbackId) {
		
		Feedback f = feedbackService.viewFeedback(feedbackId);
		
		return new ResponseEntity<Feedback>(f, HttpStatus.FOUND);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Feedback>> viewAllFeedbackHandler(){
		
		List<Feedback> f = feedbackService.viewAllFeedbacks();
		
		return new ResponseEntity<List<Feedback>>(f, HttpStatus.FOUND);
	}
	
}
