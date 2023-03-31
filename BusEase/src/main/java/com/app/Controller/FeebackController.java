package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.FeedbackService;
import com.app.model.Feedback;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeebackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@PutMapping("/add/{busId}")
	public ResponseEntity<Feedback> addfeedbackHandler(@PathVariable Integer busId, @RequestBody Feedback feedback,@PathVariable Integer userId){
	
		Feedback feedback2 = feedbackService.addfeedback(busId, feedback, userId);
		
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
