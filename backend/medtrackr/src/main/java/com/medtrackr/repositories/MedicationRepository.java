package com.medtrackr.medtrackr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medtrackr.medtrack.models.User;
import com.medtrackr.medtrackr.models.Medication; 

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}