package com.user.service;

import com.user.etity.User;
import com.user.etity.UserRegistrationRequest;
import com.user.etity.VerificationToken;
import com.user.exception.CustomException;
import com.user.repository.UserRepository;
import com.user.repository.VerificationTokenRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomException customException;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;



    @Transactional
    @Override
    public User registeUser(User user) {
        user.setPassaward(bCryptPasswordEncoder.encode(user.getPassaward()));
        user.setEnable(false);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpireddate(LocalDateTime.now().plusHours(24));

        verificationTokenRepository.save(verificationToken);
        return user;

    }

    @Override
    public boolean VerifyUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null || verificationToken.getExpireddate().isBefore(LocalDateTime.now())) {
            return false;
        }
        User user = verificationToken.getUser();
        user.setEnable(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public String registerUser(UserRegistrationRequest request) {

        if (userRepository.existsById(request.getId())){
            throw new IdAlreadyExist
        }
        return "";
    }


}