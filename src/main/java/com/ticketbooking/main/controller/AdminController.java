package com.ticketbooking.main.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.ErrorMessage;
import com.ticketbooking.main.dao.SearchBus;
import com.ticketbooking.main.dao.SuccessMessage;
import com.ticketbooking.main.models.BusModel;
import com.ticketbooking.main.models.BusRouteModel;
import com.ticketbooking.main.models.BusStationModel;
import com.ticketbooking.main.models.DriverModel;
import com.ticketbooking.main.models.RoleModel;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.repository.BusRepo;
import com.ticketbooking.main.repository.BusRouteRepo;
import com.ticketbooking.main.repository.BusStationsRepo;
import com.ticketbooking.main.repository.DriverRepo;
import com.ticketbooking.main.repository.RoleRepo;
import com.ticketbooking.main.repository.UserRepo;
import com.ticketbooking.main.repository.BusStationsRepo;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private RoleRepo rolerepo;
	
	@Autowired
	private DriverRepo driverrepo;
	
	@Autowired
	BusRouteRepo busRouteRepo;
	
	@Autowired
	private BusStationsRepo busStationRepo;
	
	@Autowired
	private BusRepo busrepo;
	

	@DeleteMapping("/deleteuser/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		UserModel user = userrepo.findByuserName(username);
		if (user != null) {
			userrepo.delete(user);
			return new ResponseEntity<>(new ErrorMessage("userdeletion Success ", HttpStatus.ACCEPTED),
					HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(new ErrorMessage("userdeletion error ", HttpStatus.ACCEPTED), HttpStatus.ACCEPTED);
	}

	@PostMapping("/{studentname}/addRoleToUser/{roleid}")
	public ResponseEntity<?> addRoleToUser(@PathVariable String studentname,
			@PathVariable Long roleid){
		System.out.println("hi");
		UserModel user = userrepo.findByuserName(studentname);
		RoleModel role =rolerepo.findById(roleid).get();
		user.addRoles(role);
		userrepo.save(user);
		return ResponseEntity.ok("done");
	}

	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers() {
		java.util.List<UserModel> user = userrepo.findAll();
		if (user == null)
			return new ResponseEntity<>(new ErrorMessage("No users available", HttpStatus.UNAUTHORIZED),
					HttpStatus.UNAUTHORIZED);
		return ResponseEntity.ok(user);
	}
//	@PostMapping("/addNewBusDriver")
//	public ResponseEntity<?> addDriverDetails(@RequestBody DriverModel driver){
//		BusModel bus = busrepo.findById((long) 1).get();
//		driver.setBus(bus);
//		driverrepo.save(driver);
//		return new ResponseEntity<>(new SuccessMessage(bus.getDriver().getDriverName(), HttpStatus.CREATED),HttpStatus.CREATED);
//	}
	@GetMapping("/searchBuses")
	public ResponseEntity<?> searchBuses(@RequestBody SearchBus searchBus) {
		List<BusModel> bus=  busrepo.findAll().stream().filter(bb->{
			return bb.getroutes().get(0).getDestinationpoint().equals(searchBus.getDestinationPoint()) && bb.getroutes().get(0).getboardingPoint().equals(searchBus.getBoardingPoint());
		}).toList();
		return ResponseEntity.ok(bus);
		
	}
	
	@GetMapping("/getbusRoute")
	public ResponseEntity<?> getbusRoute() {
		BusModel bus = busrepo.findById((long) 1).get();
		BusRouteModel routemodel = new BusRouteModel();
		routemodel.setboardingPoint("chennai");
		routemodel.setDestinationpoint("coimbatore");
		BusStationModel b =  busStationRepo.findById((long)2).get();
		busStationRepo.save(b);
		busRouteRepo.save(routemodel);
		routemodel.addStation(b);
		bus.addRoutes(routemodel);
		busrepo.save(bus);
		
		return ResponseEntity.ok("ok");
	}
//	@GetMapping("/bookTickets")
//	public boolean bookTicket() {
//		
//	}
		
}
