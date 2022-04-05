package com.ticketbooking.main.dao;

public class TokenResponse {
	private String accessToken;
	private String refreshToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public TokenResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

}
