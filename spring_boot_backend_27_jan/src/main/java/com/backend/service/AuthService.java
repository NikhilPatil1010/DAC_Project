package com.backend.service;

import com.backend.dto.*;
import com.backend.entity.User;
import com.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * LOGIN (NO Spring Security)
     */
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // ❗ Plain password check (TEMP / DEV MODE)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        UserResponse userResponse = convertToUserResponse(user);

        // No JWT → return null or dummy token
        return new AuthResponse(null, userResponse);
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
