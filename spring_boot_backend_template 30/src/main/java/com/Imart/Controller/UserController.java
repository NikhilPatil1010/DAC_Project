package com.Imart.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Imart.Entity.User;
import com.Imart.dto.Userdata;
import com.Imart.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/hii")
public class UserController {
    @Autowired
    private UserServiceImpl user1;
    @GetMapping
    public String hii() {
        return "Hello from i-mart backend 🚀";
    }
	@PostMapping("/Add")
	public ResponseEntity Add(@RequestBody User user) {
		System.out.println(user.toString()) ;
		return ResponseEntity.ok(user1.Addition(user));
	}
	@PostMapping("/Login")
	
	public ResponseEntity<?> login(@RequestBody Userdata user) {

	    boolean success = user1.Login1(user);

	    if (!success) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("message", "Invalid email or password"));
	    }

	    return ResponseEntity.ok(Map.of("message", "Login successful"));
	}

	
}
