package com.ticketbooking.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketbooking.main.models.BusModel;

public interface BusRepo extends JpaRepository<BusModel, Long> {

	
}
