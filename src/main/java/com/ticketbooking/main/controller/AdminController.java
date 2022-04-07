package com.ticketbooking.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.ErrorMessage;
import com.ticketbooking.main.models.RoleModel;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.repository.RoleRepo;
import com.ticketbooking.main.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private RoleRepo rolerepo;

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
}
