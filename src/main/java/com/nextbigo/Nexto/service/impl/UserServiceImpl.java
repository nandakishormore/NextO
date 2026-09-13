package com.nextbigo.Nexto.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.Repository.UserRepository;
import com.nextbigo.Nexto.dto.auth.UserProfileResponse;
import com.nextbigo.Nexto.error.ResourceNotFoundException;
import com.nextbigo.Nexto.service.UserService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService, UserDetailsService{
	
	UserRepository userRepository;

	@Override
	public UserProfileResponse getProfile(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(()-> new ResourceNotFoundException("User", username));
	}

}
