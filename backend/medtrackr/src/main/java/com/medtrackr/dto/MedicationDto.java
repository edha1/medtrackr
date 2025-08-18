package com.medtrackr.medtrackr.dto;

import java.time.LocalTime;

import com.medtrackr.medtrackr.models.User;

public class MedicationDto {
    private String name; 
    private String dosage; 
    private String time;

    public MedicationDto(){
        
    }
    
    public MedicationDto(String name, String dosage, String time){
        this.name = name; 
        this.dosage = dosage; 
        this.time = time; 
    }

    public String getName(){
        return this.name; 
    }

    public String getDosage(){
        return  this.dosage; 
    }

    public String getTime(){
        return this.time; 
    }

    public void setTime(String time){
        this.time = time; 
    }

    public void setName(String name){
        this.name = name; 
    }

    public void setDosage(String dosage){
        this.dosage = dosage; 
    }
}
