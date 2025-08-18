package com.medtrackr..medtrackr.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.medtrackr.medtrackr.models.User;
import com.medtrackr.medtrackr.repositories.UserRepository;

import com.medtrackr.medtrackr.services.JwtService;
import com.medtrackr.medtrackr.services.UserService;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
public class UserController {

    @Autowired
    UserService userService; 
    
    
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        User user = userService.getProfile(); 
        if (user == null) {
            return ResponseEntity.notFound().build();
        };
        return ResponseEntity.ok(user);
    }
}
