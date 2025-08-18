package com.medtrackr.medtrackr.dto;

/**
 * DTO for user when logging in 
 */
public class LoginUserDto {
    private String username;
    private String password;


    public LoginUserDto(String email, String username, String password){
        this.password = password; 
        this.username = username; 
    }

    public String getUsername(){
        return this.username; 
    }

    public String getPassword(){
        return this.password; 
    }
}