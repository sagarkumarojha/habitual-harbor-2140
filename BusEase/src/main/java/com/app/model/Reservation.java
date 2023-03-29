package com.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	@NotNull(message = "Can't set ReservationStatus as Null")
	private String reservationStatus;
	
	@NotNull(message = "Can't set Reservation type as null")
	private String reservationType;
	
	@NotNull(message = "Reservation Date should not be null or empty")
	private LocalDate reservationDate;
	
	@NotNull(message = "Local Time should not be null")
	private LocalDateTime localTime;
	
	@NotNull(message = "Source field can't be null or empty")
	private String source;
	
	@NotNull(message = "Destination field can't be null")
	private String destination;
	
//	@ManyToOne
//	private Bus bus;
	
	
}
