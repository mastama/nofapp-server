package com.nofappserver.service;

import com.nofappserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserRepository create() throws Exception;
}
