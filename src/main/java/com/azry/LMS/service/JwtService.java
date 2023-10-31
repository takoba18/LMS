package com.azry.LMS.service;

import com.azry.LMS.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface JwtService {
    String extractUserId(String token);

    String generateToken(User userDetails);

    boolean isTokenValid(String token, User userDetails);
}