package com.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.Exception.RouteException;
import com.app.model.Route;
import com.app.repository.RouteDAO;


@Service
public class RouteServiceImpl implements RouteService{

	private RouteDAO routeDao;
	
	@Override
	public Route addRoute(Route route) throws RouteException {
		
//		List<Bus> busList = new ArrayList<>();
//		route.setBuslist(busList);
	
		
		return  routeDao.save(route);
		
		
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {
Optional<Route> opt = routeDao.findById(route.getRouteId());
		
		if(opt.isPresent()) {
			   
//			 Route existingRoute = opt.get();
//			 
//			 if (!existingRoute.getBus().isEmpty())
//				 throw new RouteException("Cannot update Route ! Already buses are Scheduled for this route");
//			 
//			 if (route.getDistance() != null) existingRoute.setDistance(route.getDistance());
//			 if (route.getRouteFrom() != null) existingRoute.setRouteFrom(route.getRouteFrom());
//			 if (route.getRouteTo() != null) existingRoute.setRouteTo(route.getRouteTo());
//			 
//			Route saved =  routeDao.save(existingRoute);
//			
//			return saved;
			   return null;
		  }
		  else {
			 throw new RouteException("No route exist to update please save the Route first");
		  }
		
	
	}

	@Override
	public Route deleteRoute(Integer routeId) throws RouteException {
Optional<Route> opt =	routeDao.findById(routeId);
		
		if(opt.isPresent()) {
			
//			Route route = opt.get();
//			
//			if (!route.getBus().isEmpty())
//				 throw new RouteException("Cannot delete Route ! Already buses are Scheduled for this route");
//			
//			routeDao.delete(route);
			
//			return route;
			return null;
			
		}
		else {
			throw new RouteException("No route found on this "+routeId+" id");
		}
	}

	@Override
	public Route viewRoute(Integer routeId) throws RouteException {
		Optional<Route> opt =routeDao.findById(routeId);
		
	     if(opt.isPresent()) {
	    	 
	    	 return opt.get();
	     }
	     else {
	    	 throw new RouteException("No route found on this "+routeId+" id");
	     }
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
List<Route> routeList = routeDao.findAll();
		
		if(routeList.size()!=0) {
			
			return routeList;
		}else {
			throw new RouteException("Route list is empty");
		}
	}

}
