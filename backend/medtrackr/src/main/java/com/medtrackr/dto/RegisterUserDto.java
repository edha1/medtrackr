package com.medtrackr.medtrackr.dto;

/**
 * DTO for user when registering 
 */
public class RegisterUserDto {
    private String email;
    private String username;
    private String password;


    public RegisterUserDto(String email, String username, String password){
        this.email = email; 
        this.password = password; 
        this.username = username; 
    }

    public String getUsername(){
        return this.username; 
    }

    public String getPassword(){
        return this.password; 
    }

    public String getEmail(){
        return this.email; 
    }
}