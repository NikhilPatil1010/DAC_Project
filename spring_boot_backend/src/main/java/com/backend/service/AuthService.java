package com.backend.service;

import com.backend.dto.*;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import com.backend.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil ;
	private final PasswordEncoder passwordEncoder ;


   
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        // ❗ Plain password check (TEMP / DEV MODE)
        if (! passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
	
			String token = jwtUtil.genrateToken(user.getUserId()) ;
		
        UserResponse userResponse = convertToUserResponse(user);

        // No JWT → return null or dummy token
        return new AuthResponse(token, userResponse);
    }

    /**
     * REGISTER (NO Spring Security)
     */
    public AuthResponse register(RegisterRequest request) {

        UserResponse userResponse = userService.register(request);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after registration"));

        return new AuthResponse(null, userResponse);
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
