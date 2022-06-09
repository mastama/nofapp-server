package com.nofappserver.service;

import com.nofappserver.dto.RegisterDto;
import com.nofappserver.payload.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponse create(RegisterDto regiterDto) throws Exception;
}
