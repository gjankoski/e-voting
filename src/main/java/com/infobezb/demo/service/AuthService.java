package com.infobezb.demo.service;

import com.infobezb.demo.model.User;
import com.infobezb.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService{

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new RuntimeException();
        }
        return userRepository.findByUsername(username).orElse(null);
    }

}
