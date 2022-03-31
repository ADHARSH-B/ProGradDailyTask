package com.ticketbooking.main.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.ErrorMessage;
import com.ticketbooking.main.dao.UserSignInSuccess;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.models.UserSignInModel;
import com.ticketbooking.main.service.UserServiceImpl;

import com.ticketbooking.main.repository.UserRepo;

@RestController

public class UserController {
	@Autowired
	private UserRepo userrepo;


	@Autowired
	UserSignInSuccess usersuccess;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserServiceImpl userDetailsService;

	// protected router only authenticated users can access
	@GetMapping("/welcome")
	public ResponseEntity<String> Welcome() {
		return ResponseEntity.ok("Welcome " + SecurityContextHolder.getContext().getAuthentication().getName());
		}

	@PostMapping("/signUpuser")
	public ResponseEntity<?> sigUpuser(@RequestBody UserModel usermodel) {
		UserModel user = userrepo.findByuserName(usermodel.getUserName());
		if (user != null) {
			return new ResponseEntity<>(
					new ErrorMessage("User Already Registered Please Sign in!!", HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		}
		usermodel.setPassword(encoder.encode(usermodel.getPassword()));
		userrepo.save(usermodel);
		return ResponseEntity.ok().body(usermodel);//200
	}

	@PostMapping("/signinuser")
	public ResponseEntity<?> signInuser(@RequestBody UserSignInModel authenticationRequest) {
		
		String username = authenticationRequest.getUserName();
		String password = authenticationRequest.getPassword();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (userDetails == null) {
			return new ResponseEntity<>(new ErrorMessage("User Not Registered Please Signup !!", HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		if (!encoder.matches(password, userDetails.getPassword()))
			return new ResponseEntity<>(new ErrorMessage("UserName Or Password Is Invalid", HttpStatus.UNAUTHORIZED),
					HttpStatus.UNAUTHORIZED);
		
		usersuccess.setAuthToken(jwtUtil.generateToken(userDetails));
		usersuccess.setMessage("Successfully Authenticated");
		usersuccess.setUsername(username);
		return ResponseEntity.ok(usersuccess);//200
	}

}
