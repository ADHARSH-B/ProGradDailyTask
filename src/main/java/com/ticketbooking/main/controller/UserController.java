package com.ticketbooking.main.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.AuthResponse;
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
		usersuccess.setRefreshToken(jwtUtil.generateRefreshToken(userDetails));
		usersuccess.setMessage("Successfully Authenticated");
		usersuccess.setUsername(username);
		return ResponseEntity.ok(usersuccess);//200
	}
	@PostMapping("/accesstoken")
	public ResponseEntity<?> getAccessToken(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
final String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader == null) {
			return new ResponseEntity<>(new ErrorMessage("Token Not Found !!", HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);	
		}
		
//        System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

		String jwtToken = null;
		String username = null;
 
		String bearerToken = request.getHeader("Authorization").substring(1,
				request.getHeader("Authorization").length() - 1);
	
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			jwtToken = bearerToken.substring(7, bearerToken.length());
			username = jwtUtil.extractUsername(jwtToken);
			if (username != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					 String accessToken=jwtUtil.generateToken(userDetails);
					 String refreshToken=jwtUtil.generateRefreshToken(userDetails);
					 return new ResponseEntity<>(new AuthResponse(accessToken,refreshToken),HttpStatus.ACCEPTED);	
				}
			}
		}
		return new ResponseEntity<>(new ErrorMessage("Token is not valid!!", HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);	

	
	}
}
