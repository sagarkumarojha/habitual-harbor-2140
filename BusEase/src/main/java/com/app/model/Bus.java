package com.app.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	@NotNull(message = "Bus name cannot be null!")
	private String busName;
	@NotNull(message = "Bus driver name cannot be null!")
	private String driverName;
	private String busType;
	
	@NotNull(message = "Bus registeration number cannot be null!")
	private String busRegNumber;
	
	@NotNull(message = "Start point cannot be null!")
	@NotBlank(message = "Start point cannot be blank!")
	private String routeFrom;
	@NotNull(message = "Destination point cannot be null!")
	@NotBlank(message = "Destination point cannot be blank!")
	private String routeTo;
	
	@NotNull(message = "Arrival time cannot be null!")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime arrivalTime;
	
	@NotNull(message = "Departure time cannot be null!")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime departureTime;
	
	@NotNull(message = "Total Seats can not be less than 10 !")
	@Min(10)
	private Integer seats;
	
	@NotNull(message = "Available seats cannot be null!")
	private Integer availableSeats;
	
	@NotNull(message = "Fare can not be less than 100!")
	@Min(100)
	private Integer farePerSeat;
	
	@NotNull(message = "Bus journey date cannot be null!")
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate busJourneyDate;
	
	@ManyToOne
	private Route route;
	
	
}
