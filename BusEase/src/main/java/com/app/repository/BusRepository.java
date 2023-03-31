package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Bus;


@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{

	public List<Bus> findByBusType(String busType);
}
