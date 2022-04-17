package com.ticketbooking.main.service;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ticketbooking.main.models.RoleModel;
import com.ticketbooking.main.models.UserModel;
import com.ticketbooking.main.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userrepo;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("check "+username);
		UserModel usermodel = userrepo.findByuserName(username);
//		System.out.println(userrepo.findByuserName(username));
		if (usermodel == null) {
			throw new UsernameNotFoundException("User not exists");
		}
		//
		//System.out.println("hi" + new UserServiceImpl(usermodel).getAuthorities());
		return new UserServiceImpl(usermodel);
	}
}
