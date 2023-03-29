package com.app.exception;

public class ReservationException extends RuntimeException{
	public ReservationException() {
		super();
	}
	
	public ReservationException(String message) {
		super(message);
	}
}
