package com.Imart.service;

import org.springframework.stereotype.Service;

import com.Imart.Entity.User;
import com.Imart.Respository.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
     UserRepo userr;
	@Override
	public int Addition(User user) {
		userr.save(user);
		return 1;
	}
   
}
