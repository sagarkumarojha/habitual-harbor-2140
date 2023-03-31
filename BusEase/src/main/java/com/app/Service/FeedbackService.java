package com.app.Service;

import java.util.List;

import com.app.Exception.feedbackException;
import com.app.model.Feedback;

public interface FeedbackService {

	public Feedback addfeedback(Integer busId,Feedback feedback,Integer userId)throws feedbackException;
	public Feedback updatefeedback(Feedback feedback)throws feedbackException;
	public Feedback viewFeedback(Integer feedbackId)throws feedbackException;
	public List<Feedback> viewAllFeedbacks()throws feedbackException;
	
}
