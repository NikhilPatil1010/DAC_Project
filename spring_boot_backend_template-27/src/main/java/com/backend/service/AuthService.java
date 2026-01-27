package com.backend.service;


import com.backend.dto.*;
import com.backend.entity.User;
import com.backend.security.JwtTokenProvider;
import com.backend.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        
        String token = jwtTokenProvider.generateToken(user);
        UserResponse userResponse = convertToUserResponse(user);
        
        return new AuthResponse(token, userResponse);
    }
    
    public AuthResponse register(RegisterRequest request) {
        UserResponse userResponse = userService.register(request);
        
        // Auto login after registration
        User user = userService.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found after registration"));
        
        String token = jwtTokenProvider.generateToken(user);
        
        return new AuthResponse(token, userResponse);
    }
    
    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setRole(user.getRole().name());
        return response;
    }
}