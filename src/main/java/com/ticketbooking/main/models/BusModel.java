package com.ticketbooking.main.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "busdetails")
public class BusModel {
	public BusModel() {

	}

	public BusModel(String busName, boolean isAvailable, String currentLocation) {
		super();
		this.busName = busName;
		this.isAvailable = isAvailable;
		this.currentLocation = currentLocation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bus_id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String busName;

	
	@Column(name = "isAvailable", nullable = false)
	private boolean isAvailable;

	@Column(name = "currentLocation", nullable = false)
	private String currentLocation;

	@Column(name = "totalSeats", nullable = false)
	private int totalSeats;

	@Column(name = "seatsBooked", nullable = false)
	private int seatsBooked;

	@Column(name = "seatsAvailable", nullable = false)
	private int seatsAvailable;
	
	@Column(name="busType",nullable = false)
	private String busType;

	

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public List<BusRouteModel> getRoutes() {
		return routes;
	}

	public void setRoutes(List<BusRouteModel> routes) {
		this.routes = routes;
	}

	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "bus_route", joinColumns = @JoinColumn(name = "bus_Id"), inverseJoinColumns = @JoinColumn(name = "route_Id"))
	private List<BusRouteModel> routes=new ArrayList<BusRouteModel>();
	
	public List<BusRouteModel> getroutes() {
		return routes;
	}

	public void addRoutes(BusRouteModel routes) {
		this.routes.add(routes);
	}	



	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public DriverModel getDriver() {
//		return driver;
//	}
//
//	public void setDriver(DriverModel driver) {
//		this.driver = driver;
//	}

//	@OneToOne(fetch = FetchType.EAGER, mappedBy = "bus")
//	private DriverModel driver;

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

}
