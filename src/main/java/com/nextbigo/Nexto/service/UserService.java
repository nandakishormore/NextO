package com.nextbigo.Nexto.service;

import com.nextbigo.Nexto.dto.auth.UserProfileResponse;

public interface UserService {

	UserProfileResponse getProfile(Long userId);

}
