package com.medtrackr.medtrackr.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.medtrackr.medtrackr.dto.MedicationDto;
import com.medtrackr.medtrackr.models.Medication;
import com.medtrackr.medtrackr.models.User;
import com.medtrackr.medtrackr.repositories.MedicationRepository;
import com.medtrackr.medtrackr.repositories.UserRepository;


@Service 
public class MedicationService {
   
    
    @Autowired 
    UserRepository userRep; 

    @Autowired
    MedicationRepository medRep; 

    @Autowired
    RemindersService remService; 

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    
    public List<Medication> getMeds(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username  = authentication.getName(); 
        
        User user = userRep.findByUsername(username); 

        if (user == null) {
            throw new RuntimeException(); 
        }

        return user.getMeds(); 
    }

    public Medication saveMed(MedicationDto medDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String username  = authentication.getName(); 
        User user = userRep.findByUsername(username); 
        System.out.println("name: " + medDto.getName()); 
        System.out.println("time: " + medDto.getTime());
        System.out.println("dosage: " + medDto.getDosage());  
        if ( user == null) {
            throw new RuntimeException(); 
        }

        Medication med = new Medication(medDto.getName(), medDto.getDosage(), LocalTime.parse(medDto.getTime()), user); // make a new medication 

        user.addMed(med); // Add to the list (updates inverse side)

        userRep.save(user); // Save user with cascade saving medication
        scheduleReminder(med); 
        return med; 
    }

    /**
     * Scheduling reminders for that medicine 
     * @param med
     */
    public void scheduleReminder(Medication med) {
        executorService.submit(() -> {
            try {
                while (true) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime reminderTime = LocalDateTime.of(LocalDate.now(), med.getTime());

                    if (now.isAfter(reminderTime)) {
                        reminderTime = reminderTime.plusDays(1); // schedule for tomorrow
                    }

                    long delay = Duration.between(now, reminderTime).toMillis();

                    Thread.sleep(delay);

                    // Instead of System.out, send to frontend
                    remService.sendReminder("🔔 Reminder: Take " + med.getName() + " at " + med.getTime());

                    Thread.sleep(60_000); // optional pause before next loop
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}
