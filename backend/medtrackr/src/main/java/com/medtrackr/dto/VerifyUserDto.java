package com.medtrackr.medtrackr.dto;


/**
 * DTO for user when verifying 
 */
public class VerifyUserDto {
    private String email;
    private String verificationCode;

    public VerifyUserDto(String email, String verificationCode){
        this.email = email; 
        this.verificationCode = verificationCode; 
    }

    public String getEmail(){
        return this.email; 
    }

    public String getVerificationCode(){
        return this.verificationCode; 
    }

    public void setEmail(String e){
        this.email = e; 
    }

    public void setVerificationCode(String v){
        this.verificationCode = v;
    }
}