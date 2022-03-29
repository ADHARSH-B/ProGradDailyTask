package com.ticketbooking.main.dao;

import org.springframework.stereotype.Component;

@Component
public class UserSignInSuccess {
	private String message;
	private String AuthToken;
	private String username;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthToken() {
		return AuthToken;
	}

	public void setAuthToken(String authToken) {
		AuthToken = authToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
