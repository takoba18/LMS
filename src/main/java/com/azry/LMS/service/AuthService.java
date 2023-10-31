package com.azry.LMS.service;
import com.azry.LMS.model.request.LoginRequest;
import com.azry.LMS.model.request.RegisterRequest;
import com.azry.LMS.model.response.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
     JwtAuthenticationResponse register(RegisterRequest request);

     JwtAuthenticationResponse login(LoginRequest request);

}
