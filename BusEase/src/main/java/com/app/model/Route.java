package com.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Entity
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

	
	
	
	@OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
	private List<Bus> bus = new ArrayList<>();
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(distance, other.distance) && Objects.equals(routeFrom.toLowerCase(), other.routeFrom.toLowerCase())
				&& Objects.equals(routeId, other.routeId) && Objects.equals(routeTo.toLowerCase(), other.routeTo.toLowerCase());
	}

	@Override
	public int hashCode() {
		return Objects.hash(distance, routeFrom, routeId, routeTo);
	}
	
	
	
	
}
