package com.ticketbooking.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticketbooking.main.models.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
	UserModel findByuserName(String username);
	 
}
