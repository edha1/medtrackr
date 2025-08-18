package com.medtrackr.medtrackr.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Medication> meds; 


    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;
    private boolean enabled;

    public User (){
        this.meds = new ArrayList<>();
    } 

    public void addMed(Medication med) {
        if (this.meds == null){
            this.meds = new ArrayList<>();
        }
        
        this.meds.add(med); 
        
    }
    
    public List<Medication> getMeds(){
        return this.meds; 
    }

    
    public String getVerificationCode(){
        return this.verificationCode; 
    }

    public LocalDateTime getVerificationCodeExpiresAt(){
        return this.verificationCodeExpiresAt; 
    }

    public boolean getEnabled(){
        return this.enabled; 
    }

    public void setEnabled(boolean v){
        this.enabled = v; 
    }

    public void setVerificationCode(String v){
        this.verificationCode = v; 
    }

    public void setVerificationCodeExpiresAt(LocalDateTime v){
        this.verificationCodeExpiresAt = v; 
    }


    public boolean isActive(){
        return true; 
    }
    
    public String getEmail(){
        return this.email; 
    }

    public void setEmail(String e){
        this.email = e; 
    }

    public Long getId(){
        return this.id;
    }

    public String getPassword(){
        return this.password; 
    }

    public String getUsername(){
        return this.username; 
    }

    public void setId(Long i){
        this.id = i; 
    }

    public void setPassword(String pass){
        this.password = pass; 
    }

    public void setUsername(String user){
        this.username = user; 
    }

}
