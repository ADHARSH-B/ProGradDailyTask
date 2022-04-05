package com.ticketbooking.main.models;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

@Entity(name = "user")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "user_id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@NotNull(message = "Username is null")
	private String userName;

	@Column(nullable = false)
	private String password;

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void addRoles(RoleModel role) {
		this.roles.add(role);
	}

	@Column(unique = true, nullable = false)
	private String email;
	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleModel> roles = new HashSet<RoleModel>();

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
