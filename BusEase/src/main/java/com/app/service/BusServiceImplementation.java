package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.BusException;
import com.app.model.Bus;
import com.app.model.Route;
import com.app.repository.BusRepository;
import com.app.repository.RouteDAO;



@Service
public class BusServiceImplementation implements BusService{
	
	@Autowired
	private BusRepository busRepository;
	
	@Autowired
	private RouteDAO routeDao;
	

	@Override
	public Bus addBus(Bus bus) throws BusException {
		Route route=routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
		
		if(route != null) {
			route.getBus().add(bus);
			bus.setRoute(route);
			return busRepository.save(bus);
		}
		else
			throw new BusException("Bus detail not saved");
	}

	@Override
	public Bus updateBus(Bus bus) throws BusException {
	
		
		Optional<Bus> existingBusOpt=busRepository.findById(bus.getBusId());
		
		if(existingBusOpt.isPresent()) {
			
			Bus existingBus = existingBusOpt.get();
			
			if(existingBus.getAvailableSeats()!=existingBus.getSeats()) throw new BusException("Scheduled bus with booked seats  can't be updated!");
			
			Route route=routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
			if(route == null) throw new BusException("Invalid route!");
			bus.setRoute(route);
			return busRepository.save(bus);
		}
		else
			throw new BusException("Bus doesn't exist with this busId : "+ bus.getBusId());

	}

	@Override
	public Bus deleteBus(Integer busId) throws BusException {
	
		
		Optional<Bus> bus=busRepository.findById(busId);
		
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();			
			
			if(LocalDate.now().isBefore(existingBus.getBusJourneyDate()) && existingBus.getAvailableSeats()!=existingBus.getSeats())
				throw new BusException("Scheduled bus with booked seats can't be deleted!");
			
			
			busRepository.delete(existingBus);
			
			return existingBus;
		}
		else
			throw new BusException("Bus doesn't exist with busId : "+busId);
		
	}

	@Override
	public List<Bus> viewBusByType(String BusType) throws BusException {
		List<Bus> listOfBusType = busRepository.findByBusType(BusType);
		
		if(listOfBusType.size() >0)
			return listOfBusType;
		else
			throw new BusException("This Bus type is not available "+ BusType);
	
	}

	@Override
	public List<Bus> viewAllBuses() throws BusException {
		
		List<Bus> buses= busRepository.findAll();
		if(buses.size()>0)
			return buses;
		else
			throw new BusException("There is no bus");

	}

	@Override
	public Bus viewBus(Integer busId) throws BusException {
		Optional<Bus> bus=busRepository.findById(busId);
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();
			return existingBus;
		}
		else
			throw new BusException("Bus doesn't exist with busId : "+busId);
	}
	
}
