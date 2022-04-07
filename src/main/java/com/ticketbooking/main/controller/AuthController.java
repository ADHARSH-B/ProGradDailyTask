package com.ticketbooking.main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.AuthRequest;
import com.ticketbooking.main.dao.AuthResponse;
import com.ticketbooking.main.dao.ErrorMessage;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.repository.UserRepo;
import com.ticketbooking.main.util.JwtUtil;

import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private UserRepo userrepo;

	@Autowired
	AuthenticationManager authenticationmanager;

	@Autowired
	AuthResponse userSuccess;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<?> sigUpuser(@RequestBody UserModel usermodel) {
		UserModel user = userrepo.findByuserName(usermodel.getUserName());

		if (user != null) {
			return new ResponseEntity<>(
					new ErrorMessage("User Already Registered Please Sign in!!", HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		}
		usermodel.setPassword(encoder.encode(usermodel.getPassword()));
		userrepo.save(usermodel);
		return ResponseEntity.ok().body(usermodel);// 200
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signInuser(@RequestBody AuthRequest authrequest,Principal principal) {
		String username = authrequest.getUserName();
		String password = authrequest.getPassword();

	
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		authenticationmanager.authenticate(usernamePasswordAuthenticationToken);

		userSuccess.setAuthToken(jwtUtil.generateToken(userDetails));
		userSuccess.setRefreshToken(jwtUtil.generateRefreshToken(userDetails));
		userSuccess.setMessage("Successfully Authenticated");
		userSuccess.setUsername(username);
		userSuccess.setRole(userDetails.getAuthorities());
		return ResponseEntity.ok(userSuccess);// 200
	}

}
