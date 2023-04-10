package com.app.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.BusException;
import com.app.exception.ReservationException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.model.Reservation;
import com.app.repository.BusRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.ReservationDAO;
import com.app.service.ReservationService;

@RestController
@RequestMapping("/customer")
public class ReservationController {

	@Autowired
	private ReservationService reservationservice;
	@Autowired
	private ReservationDAO reservationRepository;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/seatreservation/{busid}/{userId}/{seats}")
	public ResponseEntity<Reservation> addReservation(Authentication auth, @PathVariable("busid") Integer busId,@PathVariable("seats") Integer seats){
		
		String username=(String) auth.getPrincipal();
		//System.out.println(username);
		Bus bus = busRepository.findById(busId).orElseThrow(() -> new BusException("Bus not Found With id :"+busId));
		Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new BusException("Please SignIn First"));
		
		
		Reservation reservation = new Reservation();
		
		
		
		reservation.setBus(bus);
		reservation.setCustomer(customer);
		reservation.setDestination(bus.getRouteTo());
		reservation.setSource(bus.getRouteFrom());
		reservation.setLocalTime(LocalTime.now().toString());
		reservation.setReservationDate(bus.getBusJourneyDate());
		if(bus.getAvailableSeats() < seats) {
			reservation.setReservationStatus("Pending");
		}else {
			reservation.setReservationStatus("Confirmed");
			bus.setAvailableSeats(bus.getAvailableSeats()-seats);
		}
		
		reservation.setReservationType(bus.getBusType());
		
		Reservation bookedReservation = reservationservice.addReservation(reservation,busId,username);
		
		return new ResponseEntity<>(bookedReservation, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatereservation/{reservationId}")
	public ResponseEntity<Reservation> updateReservation(Authentication auth,@RequestBody Reservation reservation,@PathVariable Integer reservationId) {
	//	String username=(String) auth.getPrincipal();
	//	Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new BusException("Please SignIn First"));
		
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


