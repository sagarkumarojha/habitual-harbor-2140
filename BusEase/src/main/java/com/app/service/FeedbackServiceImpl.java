package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BusException;
import com.app.exception.feedbackException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.model.Feedback;
import com.app.repository.BusRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.FeedbackRepository;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Feedback addfeedback(Integer busId, Feedback feedback,Integer userId) throws feedbackException {
		
		Bus bus = busRepository.findById(busId).orElseThrow(() -> new BusException("Bus not Found With id :"+busId));
		Customer customer = customerRepository.findById(userId).orElseThrow(() -> new BusException("User not Found With id :"+userId));
		
		feedback.setBus(bus);
		feedback.setUser(customer);
//		System.out.println(feedback);
		Feedback feedback2 = feedbackRepository.save(feedback);
		
		
		return feedback2;
	}

	

	@Override
	public Feedback updatefeedback(Feedback feedback) throws feedbackException {
		
		Feedback f = feedbackRepository.findById(feedback.getFeedbackId()).orElseThrow(() -> new feedbackException("Feedback with Id " + feedback.getFeedbackId() + " does not exist"));
		
	    
		
//		if (feedback.getComments() != null) f.setComments(feedback.getComments());
//		if (feedback.getDriverRating() != null) f.setDriverRating(feedback.getDriverRating());
//		if (feedback.getServiceRating() != null) f.setServiceRating(feedback.getServiceRating());
//		if (feedback.getOverallRating() != null) f.setOverallRating(feedback.getOverallRating());
//		
		Feedback updated = feedbackRepository.save(feedback);
		
		return updated;
		
		
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId) throws feedbackException {
		
		Feedback f = feedbackRepository.findById(feedbackId).orElseThrow(() -> new feedbackException("Feedback with Id " + feedbackId + " does not exist"));
		return f;
	
	}

	@Override
	public List<Feedback> viewAllFeedbacks() throws feedbackException {
		
		List<Feedback> feedbacks = feedbackRepository.findAll();
		
		if(feedbacks.isEmpty()) {
			throw new feedbackException("No Feedback found");
		}else
		
		return feedbacks;
	}

	
	
	
}
