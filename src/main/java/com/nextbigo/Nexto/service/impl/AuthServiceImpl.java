package com.nextbigo.Nexto.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.Repository.UserRepository;
import com.nextbigo.Nexto.dto.auth.AuthResponse;
import com.nextbigo.Nexto.dto.auth.LoginRequest;
import com.nextbigo.Nexto.dto.auth.SignupRequest;
import com.nextbigo.Nexto.entity.User;
import com.nextbigo.Nexto.error.BadRequestException;
import com.nextbigo.Nexto.mapper.UserMapper;
import com.nextbigo.Nexto.security.AuthUtil;
import com.nextbigo.Nexto.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{
	
	UserRepository userRepository;
	UserMapper userMapper;
	PasswordEncoder passwordEncoder;
	AuthenticationManager authenticationManager;
	AuthUtil authUtil;
	

	@Override
	public AuthResponse signup(SignupRequest request) {
		userRepository.findByUsername(request.username()).ifPresent(user -> {
			throw new BadRequestException("User Already exist with " + request.username() + "!");
		});;
		
		User user = userMapper.toUserFromSignupRequest(request);
		user.setPassword(passwordEncoder.encode(request.password()));
		user = userRepository.save(user);
		String token = authUtil.generateAccessToken(user);
		
		return new AuthResponse(token, userMapper.toUserProfileResponsefromUser(user));
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.username(), request.password())
				);
		
		User user = (User) authentication.getPrincipal();
		String token = authUtil.generateAccessToken(user);
		
		return new AuthResponse(token, userMapper.toUserProfileResponsefromUser(user));
	}

}
