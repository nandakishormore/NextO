package com.nextbigo.Nexto.service;

import org.springframework.stereotype.Component;

import com.nextbigo.Nexto.dto.auth.AuthResponse;
import com.nextbigo.Nexto.dto.auth.LoginRequest;
import com.nextbigo.Nexto.dto.auth.SignupRequest;

@Component
public interface AuthService {

	AuthResponse signup(SignupRequest request);

	AuthResponse login(LoginRequest request);

}
