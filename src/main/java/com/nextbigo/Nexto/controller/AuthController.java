package com.nextbigo.Nexto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbigo.Nexto.dto.auth.AuthResponse;
import com.nextbigo.Nexto.dto.auth.LoginRequest;
import com.nextbigo.Nexto.dto.auth.SignupRequest;
import com.nextbigo.Nexto.dto.auth.UserProfileResponse;
import com.nextbigo.Nexto.service.AuthService;
import com.nextbigo.Nexto.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api/auth")
public class AuthController {

	AuthService authService;
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signup(@RequestBody @Valid SignupRequest request) {
		return ResponseEntity.ok(authService.signup(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserProfileResponse> getProfile(){
		Long userId=1l;
		return ResponseEntity.ok(userService.getProfile(userId));
	}
	

}
