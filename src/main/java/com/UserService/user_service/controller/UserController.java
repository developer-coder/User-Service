package com.UserService.user_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserService.user_service.model.User;
import com.UserService.user_service.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
    	System.out.println("regustering users"+user);
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
    	logger.info("heell00");
    	System.out.println("inside user");
    	System.out.println("user" +user);
        User storedUser = userService.getUserProfile(user.getUsername());
        if (storedUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<User> getProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }
}
