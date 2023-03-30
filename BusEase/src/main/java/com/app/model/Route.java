package com.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotNull(message = "Route from field should not be empty")
	private String routeFrom;
	
	@NotNull(message = "Route To field should not be empty")
	private String routeTo;
	
	@NotNull(message = "Distance field should not be null")
	private Integer distance;

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	
	
//	@OneToMany(mappedBy = "routes", cascade = CascadeType.ALL)
//	private List<Bus> bus = new ArrayList<>();
	
	
}
