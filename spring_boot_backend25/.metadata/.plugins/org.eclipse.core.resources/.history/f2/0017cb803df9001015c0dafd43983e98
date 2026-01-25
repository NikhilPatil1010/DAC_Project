package com.Imart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Imart.Entity.User;
import com.Imart.service.UserServiceImpl;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServiceImpl user1;
	
	@PostMapping("/Add")
	public ResponseEntity Add(@RequestBody User user) {
		return ResponseEntity.ok(user1.Addition(user));
	}
}
