package com.ticketbooking.main.dao;

public class AuthRequest {
	private String UserName;
	private String password;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthRequest(String userName, String password) {
		super();
		UserName = userName;
		this.password = password;
	}

}
