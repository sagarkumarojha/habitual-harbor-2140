package com.app.Service;

import java.util.List;

import com.app.exception.RouteException;
import com.app.model.Route;

public interface RouteService {
	public Route addRoute(Route route) throws RouteException;

	public Route updateRoute(Route route) throws RouteException;

	public Route deleteRoute(Integer routeId) throws RouteException;

	public Route viewRoute(Integer routeId) throws RouteException;
	
	public List<Route> viewAllRoute() throws RouteException;
	
}
