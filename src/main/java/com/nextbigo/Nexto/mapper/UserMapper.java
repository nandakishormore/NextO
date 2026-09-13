package com.nextbigo.Nexto.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import com.nextbigo.Nexto.dto.auth.SignupRequest;
import com.nextbigo.Nexto.dto.auth.UserProfileResponse;
import com.nextbigo.Nexto.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User toUserFromSignupRequest(SignupRequest request);
	
	UserProfileResponse toUserProfileResponsefromUser(User user);
}
