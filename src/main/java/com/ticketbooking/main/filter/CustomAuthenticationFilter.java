package com.ticketbooking.main.filter;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.ticketbooking.main.controller.JwtUtil;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	JwtUtil jwtUtil;
	
//	@Autowired
//	AuthenticationManager authenticationManager;


	@Autowired
	UserServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
//        System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

		String jwtToken = null;
		String username = null;
 
		String bearerToken = request.getHeader("Authorization").substring(1,
				request.getHeader("Authorization").length() - 1);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			jwtToken = bearerToken.substring(7, bearerToken.length());
			username = jwtUtil.extractUsername(jwtToken);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					// what is happening here
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, userDetails.getUsername(), userDetails.getAuthorities());	
				//	authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}


}




//usernamePasswordAuthenticationToken
//.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));