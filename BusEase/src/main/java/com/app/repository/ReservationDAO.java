package com.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Bus;
import com.app.model.Reservation;


@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

	Optional<Reservation> findByCustomer(String username);

//	@Query("select r from Reservation where reservationDate > ?1")
//	public List<Reservation> getAllReservation(LocalDate date);
}
