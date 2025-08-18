package com.medtrackr.medtrackr.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.medtrackr.medtrackr.dto.LoginUserDto;
import com.medtrackr.medtrackr.dto.RegisterUserDto;
import com.medtrackr.medtrackr.dto.VerifyUserDto;
import com.medtrackr.medtrackr.dto.AuthRequest;
import com.medtrackr.medtrackr.dto.AuthResponse;
import com.medtrackr.medtrackr.models.User;
import com.medtrackr.medtrackr.repositories.UserRepository;

import jakarta.mail.MessagingException;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired 
    private CustomUserDetailsService customUserDetailsService; 

    @Autowired 
    JwtService jwtService; 

    @Autowired 
    UserRepository userRep; 

    @Autowired
    PasswordEncoder passwordEncoder; 

    @Autowired
    EmailService emailService; 

    public String register(RegisterUserDto input) {
        if (userRep.existsByUsername(input.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());


        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);
        User savedUser = userRep.save(user);
        sendVerificationEmail(user);
        
        return "Registration successful, please verify email before logging in"; 
    }

    public AuthResponse authenticate(LoginUserDto input) {
        User user = userRep.findByUsername(input.getUsername());

        if (user == null){
            throw new RuntimeException("Email doesn't exist"); 
        }

        if (!user.getEnabled()) {
            
            throw new RuntimeException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
            );

        // load user details and generate JWT
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(input.getUsername());
        String jwtToken = jwtService.generateToken(userDetails.getUsername());

        AuthResponse res = new AuthResponse(userDetails.getUsername(), jwtToken, user.getId()); 
            
        // return token
        return res; 
    }

    public void verifyUser(VerifyUserDto input) {
        Optional<User> optionalUser = Optional.ofNullable(userRep.findByEmail(input.getEmail()));
        if (optionalUser.isPresent()) { 
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                System.out.println("expired"); 
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                userRep.save(user);
                
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void resendVerificationCode(String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRep.findByEmail(email));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getEnabled()) {
                throw new RuntimeException("Account is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRep.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private void sendVerificationEmail(User user) { //TODO: Update with company logo
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
    
}
