package com.ticketbooking.main.securitycon;

import java.time.LocalDateTime;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ticketbooking.main.filter.CustomAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserDetailsService myuserdetailsserivce;

	@Autowired
	CustomAuthenticationFilter customAuthenticationFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myuserdetailsserivce).passwordEncoder(encoder);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(myuserdetailsserivce).passwordEncoder(encoder);
	}

	@Override
	public void configure(HttpSecurity security) throws Exception {

		// how to enable cross platform request (check for CORS policy )

		security.csrf().disable().authorizeRequests()//.antMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
				.antMatchers("/api/v1/user/**").hasAuthority("USER").antMatchers("/api/v1/auth/**").permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		security.exceptionHandling().accessDeniedHandler((request, response, e) -> {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write(
					new JSONObject().put("timestamp", LocalDateTime.now()).put("message", "Access denied").toString());
		});
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

//security.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
//response.setContentType("application/json");
//response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//response.getWriter().write(
//		new JSONObject().put("timestamp", LocalDateTime.now()).put("message", "Access denied").toString());
//});