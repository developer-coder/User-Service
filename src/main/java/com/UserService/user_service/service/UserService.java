package com.UserService.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UserService.user_service.model.User;
import com.UserService.user_service.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int registerUser(User user) {
        return userRepository.registerUser(user);
    }

    public User getUserProfile(String username) {
        return userRepository.findByUsername(username);
    }
}

