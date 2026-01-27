package com.backend.controller;



import com.backend.dto.*;
import com.backend.security.CurrentUser;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<UserResponse> getProfile(@CurrentUser Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }
    
    @PutMapping
    public ResponseEntity<UserResponse> updateProfile(@CurrentUser Long userId,
                                                     @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }
}