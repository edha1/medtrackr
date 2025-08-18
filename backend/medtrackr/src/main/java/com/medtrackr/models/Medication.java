package com.medtrackr.medtrackr.models;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 


@Entity
public class Medication { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    private String name; 
    private String dosage; 
    private LocalTime time; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Medication() {
        
    }
    public Medication(String name, String dosage, LocalTime time, User user){
        this.name = name; 
        this.dosage = dosage; 
        this.time = time; 
        this.user = user; 
    }

    public User getUser(){
        return this.user; 
    }

    public void setUser(User user){
        this.user = user; 
    }

    public String getName(){
        return this.name; 
    }

    public String getDosage(){
        return  this.dosage; 
    }

    public LocalTime getTime(){
        return this.time; 
    }

    public void setTime(LocalTime time){
        this.time = time; 
    }

    public void setName(String name){
        this.name = name; 
    }

    public void setDosage(String dosage){
        this.dosage = dosage; 
    }

    @Override
    public String toString() {
        return name + " (" + dosage + ") at " + time;
    }
}