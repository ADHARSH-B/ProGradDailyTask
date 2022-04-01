package com.ticketbooking.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity(name = "user")

public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	
	@NotNull(message = "Username is null")
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true,nullable = false)
	private String email;

	public UserModel() {
	}

	public UserModel(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
