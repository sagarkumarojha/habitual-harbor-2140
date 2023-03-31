package com.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.Service.ReservationService;
import com.app.model.Reservation;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;

@SecurityRequirements
@Controller
@RequestMapping("customer/")
public class ReservationController {

	@Autowired
	private ReservationService reservationservice;
	
	@PostMapping("seatreservation/{busid}/{userId}")
	@CrossOrigin
	public ResponseEntity<Reservation> addReservation( @RequestBody Reservation reservation, @PathVariable("busid") Integer busId,@PathVariable("userId") Integer userId){
		
		Reservation bookedReservation = reservationservice.addReservation(reservation,busId,userId);
		
		return new ResponseEntity<Reservation>(bookedReservation, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatereservation/{reservationId}")
	public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation,@PathVariable Integer reservationId) {
		Reservation updatereservation = reservationservice.updateReservation(reservation, reservationId);
		
		return new ResponseEntity<>(updatereservation, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletereservation/{reservationId}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer reservationId){
		Reservation deletereservation = reservationservice.deleteReservation(reservationId);
		
		return new ResponseEntity<>(deletereservation, HttpStatus.OK);
	}
	
	@GetMapping("/viewreservationdetails/{reservationId}")
	public ResponseEntity<Reservation> viewReservationDetail(@PathVariable Integer reservationId){
		Reservation viewreservation = reservationservice.viewReservation(reservationId);
		
		return new ResponseEntity<>(viewreservation, HttpStatus.OK);
	}
	

}


