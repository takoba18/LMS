package com.azry.LMS.service.impl;

import com.azry.LMS.entity.User;
import com.azry.LMS.model.request.LoginRequest;
import com.azry.LMS.model.request.RegisterRequest;
import com.azry.LMS.model.response.JwtAuthenticationResponse;
import com.azry.LMS.model.response.UserDTO;
import com.azry.LMS.repository.UserRepository;
import com.azry.LMS.service.AuthService;
import com.azry.LMS.service.JwtService;
import com.azry.LMS.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public JwtAuthenticationResponse register(RegisterRequest request) {
        User user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).user(UserDTO.convertFromUser(user)).build();
    }

    @Override
    public JwtAuthenticationResponse login(LoginRequest request) {

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        if(passwordEncoder.matches(request.getPassword(),user.getPassword()))
            return JwtAuthenticationResponse.builder().token(jwt).user(UserDTO.convertFromUser(user)).build();

        else throw new RuntimeException("Invalid email or password");



    }
}
