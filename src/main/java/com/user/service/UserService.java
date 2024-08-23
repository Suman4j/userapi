package com.user.service;

import com.user.etity.User;
import com.user.etity.UserRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registeUser(User user);
    boolean VerifyUser(String token);
    public String registerUser(UserRegistrationRequest request);
}
