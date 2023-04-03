package com.app.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Integer feedbackId;
	
	@Min(value = 1,message = "Rating Should be between 1 to 10 ")
	@Max(value = 10,message = "Rating Should be between 1 to 10")
	private  Integer driverRating;
	
	@Min(value = 1,message = "Rating Should be between 1 to 10 ")
	@Max(value = 10,message = "Rating Should be between 1 to 10")
	private  Integer serviceRating;
	
	@Min(value = 1,message = "Rating Should be between 1 to 10 ")
	@Max(value = 10,message = "Rating Should be between 1 to 10")
	private Integer overallRating;
	
	private String comments;
	
	@PastOrPresent(message = "Please add a present or past date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate feedbackdate;
	
//	@Column(name = "user_user_id")
//	private Integer userId;
//	@Column(name = "bus_bus_id")
//	private Integer busId;
	
	@JsonIgnore
	@OneToOne
	private Customer user;
	@JsonIgnore
	@OneToOne
	private Bus bus;
	
}
