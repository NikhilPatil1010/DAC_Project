package com.Imart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Imart.Entity.User;
import com.Imart.Respository.UserRepo;
import com.Imart.dto.Userdata;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
     UserRepo userr;
	@Override
	public int Addition(User user) {
		System.out.println(user.toString());
		userr.save(user);
		return 1;
	}
	public boolean Login1(Userdata user) {
		Optional<User> m=userr.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return m.isPresent();
	}
   
}
