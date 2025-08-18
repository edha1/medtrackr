package com.medtrackr.medtrackr.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.medtrackr.medtrackr.models.User;
import com.medtrackr.medtrackr.repositories.UserRepository;
import com.medtrackr.medtrackr.CustomUserDetails;


@Service // Spring knows to inject this 
public class CustomUserDetailsService implements UserDetailsService{
    
    
    @Autowired 
    UserRepository userRep; 
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRep.findByUsername(username); 

        if (user == null){ // if we don't  find the user 
            throw new UsernameNotFoundException("User not found with email: " + user); 
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>(); // granted authority represents a permission or role granted to an authenticated user  
        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getEmail(), authorities, user.getEnabled()); 
    }
    

}
