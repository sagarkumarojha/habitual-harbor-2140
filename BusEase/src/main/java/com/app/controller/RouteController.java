package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.RouteService;
import com.app.model.Route;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class RouteController {
	@Autowired
	private RouteService rService;

	@PostMapping("/addRoute")
	@CrossOrigin
	public ResponseEntity<Route> addRouteHandler(@Valid @RequestBody Route route){

		Route rot = rService.addRoute(route);

		return new ResponseEntity<Route>(rot, HttpStatus.CREATED);
	}

	@PutMapping("/updateRoute")
	@CrossOrigin
	public ResponseEntity<Route> updateRouteHandler(@Valid @RequestBody Route route)
		{

		Route rot = rService.updateRoute(route);

		return new ResponseEntity<Route>(rot, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/deleteRoute/{routeId}")
	@CrossOrigin
	public ResponseEntity<Route> deleteRouteHandler(@PathVariable("routeId") Integer routeId)
			{

		Route rot = rService.deleteRoute(routeId);

		return new ResponseEntity<Route>(rot, HttpStatus.OK);

	}
	

	@GetMapping("/viewRoute/{routeId}")
	public ResponseEntity<Route> viewRouteHandler(@PathVariable("routeId") Integer routeId){

		Route rot = rService.viewRoute(routeId);

		return new ResponseEntity<Route>(rot, HttpStatus.OK);
	}

	
	@GetMapping("/viewAllRoute")
	public ResponseEntity<List<Route>> viewAllRouteHandler(){

		List<Route> rot = rService.viewAllRoute();

		return new ResponseEntity<List<Route>>(rot, HttpStatus.OK);

	}
}
