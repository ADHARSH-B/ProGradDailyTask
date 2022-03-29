package com.ticketbooking.main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService,UserDetailsService  {
	@Autowired
	private  UserRepo userrepo;

	@Override
	public UserModel saveUser(UserModel user) {
		return userrepo.save(user);
	}

	@Override
	public UserModel getUser(String userName) {
		return userrepo.findByuserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel usermodel = userrepo.findByuserName(username);
		if(usermodel==null) {
			return null;
		}
		return new User(usermodel.getUserName(), usermodel.getPassword(), new ArrayList<>());
	}
}
