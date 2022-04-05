package com.ticketbooking.main.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbooking.main.dao.TokenResponse;
import com.ticketbooking.main.dao.AuthRequest;
import com.ticketbooking.main.dao.ErrorMessage;
import com.ticketbooking.main.dao.AuthResponse;
import com.ticketbooking.main.models.RoleModel;
import com.ticketbooking.main.models.UserModel;

import com.ticketbooking.main.service.UserServiceImpl;
import com.ticketbooking.main.util.JwtUtil;

import antlr.collections.List;

import com.ticketbooking.main.repository.UserRepo;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	private UserRepo userrepo;

	@Autowired
	AuthResponse userSuccess;

	@Autowired
	AuthenticationManager authenticationmanager;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;

	// protected router only authenticated users can access
	@GetMapping("/welcome")
	public ResponseEntity<String> Welcome() {
		return ResponseEntity.ok("Welcome " + SecurityContextHolder.getContext().getAuthentication().getName());
	}

//	@PostMapping("/signUpuser")
//	public ResponseEntity<?> sigUpuser(@RequestBody UserModel usermodel) {
//		UserModel user = userrepo.findByuserName(usermodel.getUserName());
////		Set<RoleModel> roles=new HashSet<RoleModel>();
////		roles.add(new RoleModel("USER"));
////		
//		if (user != null) {
//			return new ResponseEntity<>(
//					new ErrorMessage("User Already Registered Please Sign in!!", HttpStatus.BAD_REQUEST),
//					HttpStatus.BAD_REQUEST);
//		}
//		usermodel.setPassword(encoder.encode(usermodel.getPassword()));
//		//usermodel.setRoles(roles);
//		userrepo.save(usermodel);
//		return ResponseEntity.ok().body(usermodel);// 200
//	}

//	@PostMapping("/signinuser")
//	public ResponseEntity<?> signInuser(@RequestBody AuthRequest authrequest) {
//
//		String username = authrequest.getUserName();
//		String password = authrequest.getPassword();
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//		try {
//			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//					username, password);
//			authenticationmanager.authenticate(usernamePasswordAuthenticationToken);
//		} catch (BadCredentialsException e) {
//			return new ResponseEntity<>(new ErrorMessage("Incorrect Username or Password!!", HttpStatus.BAD_REQUEST),
//					HttpStatus.BAD_REQUEST);
//		}
//
////		/UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//		// SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
////		if (userDetails == null) {
////			return new ResponseEntity<>(new ErrorMessage("User Not Registered Please Signup !!", HttpStatus.NOT_FOUND),
////					HttpStatus.NOT_FOUND);
////		}
////		if (!encoder.matches(password, userDetails.getPassword()))
////			return new ResponseEntity<>(new ErrorMessage("UserName Or Password Is Invalid", HttpStatus.UNAUTHORIZED),
////					HttpStatus.UNAUTHORIZED);
//		// UserDetails userDetails = new UserDetails
//		userSuccess.setAuthToken(jwtUtil.generateToken(userDetails));
//		userSuccess.setRefreshToken(jwtUtil.generateRefreshToken(userDetails));
//		userSuccess.setMessage("Successfully Authenticated");
//		userSuccess.setUsername(username);
//		userSuccess.setRole(userDetails.getAuthorities());
//		return ResponseEntity.ok(userSuccess);// 200
//	}

	@PostMapping("/accesstoken")
	public ResponseEntity<?> getAccessToken(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		final String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null) {
			return new ResponseEntity<>(new ErrorMessage("Token Not Found !!", HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}

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
					String accessToken = jwtUtil.generateToken(userDetails);
					String refreshToken = jwtUtil.generateRefreshToken(userDetails);
					return new ResponseEntity<>(new TokenResponse(accessToken, refreshToken), HttpStatus.ACCEPTED);
				}
			}
		}
		return new ResponseEntity<>(new ErrorMessage("Token is not valid!!", HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);

	}

//	@GetMapping("/404")
//	public ResponseEntity<?> AccessDenied(){
//		 return new ResponseEntity<>(new ErrorMessage("You are allowed to view this page ", HttpStatus.UNAUTHORIZED),
//				HttpStatus.UNAUTHORIZED);
//	}
	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers() {
		System.out.println("check");
		java.util.List<UserModel> user = userrepo.findAll();
		if (user == null)
			return new ResponseEntity<>(new ErrorMessage("No users available", HttpStatus.UNAUTHORIZED),
					HttpStatus.UNAUTHORIZED);
		return ResponseEntity.ok(user);
	}

//	@DeleteMapping("/deleteuser/{username}")
//	public ResponseEntity<?> deleteUser(@PathVariable String username) {
//		UserModel user=userrepo.findByuserName(username);
//		if(user!=null) {
//			userrepo.delete(user);
//			return new ResponseEntity<>(new ErrorMessage("userdeletion Success ", HttpStatus.ACCEPTED),
//					HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<>(new ErrorMessage("userdeletion error ", HttpStatus.ACCEPTED),
//				HttpStatus.ACCEPTED);
//	}
}
