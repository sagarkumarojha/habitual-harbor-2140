package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.exception.ReservationException;
import com.app.model.Reservation;

public interface ReservationService {
	public Reservation addReservation(Reservation reservation,Integer busId, Integer userid) throws ReservationException;
	
	public Reservation  updateReservation(Reservation reservation,Integer reservationId) throws ReservationException ;
	
	public Reservation  deleteReservation(Integer reservationId) throws ReservationException;
	
	public Reservation  viewReservation(Integer reservationId) throws ReservationException ;
	
	public List<Reservation> viewAllReservations() throws ReservationException;
	
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationException;
	
	
	
}
