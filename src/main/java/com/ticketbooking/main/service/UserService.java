package com.ticketbooking.main.service;

import com.ticketbooking.main.models.UserModel;

public interface UserService {
	UserModel saveUser(UserModel user);

	UserModel getUser(String userName);
}
