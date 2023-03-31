package com.app.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Exception.ReservationException;
import com.app.model.Reservation;
import com.app.repository.ReservationDAO;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationDAO reservationDao;
	
	
	@Override
	public Reservation addReservation(Reservation reservation, Integer busId, Integer userid)
			throws ReservationException {
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
