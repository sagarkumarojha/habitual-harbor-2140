package com.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Exception.feedbackException;
import com.app.model.Feedback;
import com.app.repository.FeedbackRepository;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public Feedback addfeedback(Integer busId, Feedback feedback,Integer userId) throws feedbackException {
		
		Feedback feedback2 = feedbackRepository.save(feedback);
		
		return feedback2;
	}

	

	@Override
	public Feedback updatefeedback(Feedback feedback) throws feedbackException {
		
		Feedback f = feedbackRepository.findById(feedback.getFeedbackId()).orElseThrow(() -> new feedbackException("Feedback with Id " + feedback.getFeedbackId() + " does not exist"));
		
		if (feedback.getComments() != null) f.setComments(feedback.getComments());
		if (feedback.getDriverRating() != null) f.setDriverRating(feedback.getDriverRating());
		if (feedback.getServiceRating() != null) f.setServiceRating(feedback.getServiceRating());
		if (feedback.getOverallRating() != null) f.setOverallRating(feedback.getOverallRating());
		
		Feedback updated = feedbackRepository.save(f);
		
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
