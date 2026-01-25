package com.Imart.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.Imart.Entity.User;

public interface UserRepo extends JpaRepository<User,Long>{
	 Optional<User> findByEmailAndPassword(String email, String password);
}
