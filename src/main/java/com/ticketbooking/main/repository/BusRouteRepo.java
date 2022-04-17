package com.ticketbooking.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketbooking.main.models.BusRouteModel;

public interface BusRouteRepo extends JpaRepository<BusRouteModel, Long>{

}
