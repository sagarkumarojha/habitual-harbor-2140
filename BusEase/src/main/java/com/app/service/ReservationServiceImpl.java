package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BusException;
import com.app.exception.CustomerException;
import com.app.exception.ReservationException;
import com.app.model.Bus;
import com.app.model.Customer;
import com.app.model.Reservation;
import com.app.repository.BusRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDAO reservationDao;
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public Reservation addReservation(Reservation reservation, Integer busId, String username)
			throws ReservationException {
		Bus bus = busRepository.findById(busId).orElseThrow(() -> new BusException("Bus not found with given id"));
		Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new CustomerException("User not found with given id"));
		
		reservation.setBus(bus);
		reservation.setCustomer(customer);
		Reservation reserve = reservationDao.save(reservation);
		
		return reserve;
	}

	@Override
	public Reservation updateReservation(Reservation reservation,Integer reservationId) throws ReservationException {
		// TODO Auto-generated method stub
		Optional<Reservation> opt = reservationDao.findById(reservationId);
		
		if(opt.isPresent()) {
			Reservation res = opt.get();
			Reservation updatereservation = reservationDao.save(reservation);
			return updatereservation;
		}else {
			throw new ReservationException("No reservation found for the given reservation Id");
		}
	}

	@Override
	public Reservation deleteReservation(Integer reservationId) throws ReservationException {
		// TODO Auto-generated method stub
		Optional<Reservation> opt = reservationDao.findById(reservationId);
		
		if(opt.isPresent()) {
			Reservation res = opt.get();
			reservationDao.delete(res);
			
			return res;
		}else {
			throw new ReservationException("No reservation details found for the given reservation Id");
		}
	}

	@Override
	public Reservation viewReservation(Integer registrationId) throws ReservationException {
		// TODO Auto-generated method stub
		Optional<Reservation> res = reservationDao.findById(registrationId);
		
		if(res.isPresent()) {
			Reservation view = res.get();
			
			return view;
		}else {
			throw new ReservationException("No Details found for the given id");
		}
	}

	@Override
	public List<Reservation> viewAllReservations() throws ReservationException {
		// TODO Auto-generated method stub
		
		List<Reservation> reslist = reservationDao.findAll();
		
		if(reslist != null) {
			return reslist;
		}else {
			throw new ReservationException("No Data Found");
		}
		
	
	}

	@Override
	public List<Reservation> getAllReservation(LocalDate date) throws ReservationException {
//		List<Reservation> dateres = reservationDao.getAllReservation(date);
//		
//		if(dateres != null) {
//			return dateres;
//		}else {
//			throw new ReservationException("No Data Found");
//		}
	return null;
	}

}
