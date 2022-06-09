package com.nofappserver.service.impl;

import com.nofappserver.dto.RegisterDto;
import com.nofappserver.model.User;
import com.nofappserver.payload.response.UserResponse;
import com.nofappserver.repository.UserRepository;
import com.nofappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponse create(RegisterDto regiterDto) throws Exception {
        User user = new User();
        user.setUsername(regiterDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(regiterDto.getPassword()));

        User userSaved = this.userRepository.save(user);
            UserResponse userResponse = new UserResponse();
            userResponse.setId(userSaved.getId());
            userResponse.setUsername(userSaved.getUsername());

            return userResponse;
    }
}
