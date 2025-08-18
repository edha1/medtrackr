package com.medtrackr.medtrackr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.medtrackr.medtrackr.models.User;
import com.medtrackr.medtrackr.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRep; 


    public User getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        String username  = authentication.getName(); 
        User user = userRep.findByUsername(username); 

        return user; 
    }
}
