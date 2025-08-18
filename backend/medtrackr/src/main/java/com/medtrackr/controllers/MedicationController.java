package com.medtrackr.medtrackr..controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medtrackr.medtrackr.dto.MedicationDto;
import com.medtrackr.medtrackr.models.Medication;
import com.medtrackr.medtrackr.services.MedicationService;



@RestController 
public class MedicationController {
    

    @Autowired
    MedicationService medService; 

    
    @GetMapping("/getMedications")
    public ResponseEntity<?> getMethodName() {
        try{
            List<Medication> meds = medService.getMeds(); 
            return ResponseEntity.ok(meds); 
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build(); 
        }
    }

    @PostMapping("/saveMedication")
    public ResponseEntity<?> postMethodName(@RequestBody MedicationDto medDto) {
        try {
            Medication med = medService.saveMed(medDto); 
            return ResponseEntity.ok("saved"); 
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    
}
