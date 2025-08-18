package com.medtrackr.medtrackr.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medtrackr.medtrackr.dto.LoginUserDto;
import com.medtrackr.medtrackr.dto.RegisterUserDto;
import com.medtrackr.medtrackr.dto.VerifyUserDto;
import com.medtrackr.medtrackr.dto.AuthResponse;
import com.medtrackr.medtrackr.services.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Controller for authorisation (register and logins)
 */
@RestController
public class AuthController {

    
    @Autowired
    AuthenticationService authService; 

    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // immediately expires
        response.addCookie(cookie);
        
        SecurityContextHolder.clearContext(); // optional
        

        return ResponseEntity.ok("Logged out");
    }


    /**
     * Login route 
     */
    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginUserDto input, HttpServletResponse response) throws Exception {
        try{
           AuthResponse res = authService.authenticate(input);  
           ResponseCookie cookie = ResponseCookie.from("jwt", res.getToken())
            .httpOnly(true)
            .secure(false) // set to true in production with HTTPS
            .path("/")
            .sameSite("Lax") // or "Strict"
            .maxAge(Duration.ofHours(1))
            .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

           return ResponseEntity.ok(res); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

    }

    /**
     * Register route 
     * @param registerUserDto
     * @return
     * @throws Exception
     */
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) throws Exception{

        try {
            String res = authService.register(registerUserDto);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    /**
     * Verification route 
     */
    @PostMapping("/auth/verify")
        public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
            
            try {
                authService.verifyUser(verifyUserDto);
                return ResponseEntity.ok("Account verified successfully");
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


    /**
     * Verification email resend route 
     * @param email
     * @return
     */
    @PostMapping("auth/resend")
        public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
            try {
                authService.resendVerificationCode(email);
                return ResponseEntity.ok("Verification code sent");
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
}
