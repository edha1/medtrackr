package com.medtrackr.medtrackr.models;


// DTO 
public class AuthResponse {
    

    private String username; 
    private Long id; 

    private String jwtToken; 

    public AuthResponse(String user, String jwtToken, Long id){
        this.username = user; 
        this.jwtToken = jwtToken; 
        this.id = id; 
    }

    public String getUsername(){
        return this.username; 
    }

    public void setUsername(String u){
        this.username = u; 
    }

    public Long getId(){
        return this.id; 
    }

    public void setId(Long id){
        this.id = id; 
    }

    public String getToken(){
        return this.jwtToken; 
    }

    public void setToken(String token){
        this.jwtToken = token; 
    }
}
