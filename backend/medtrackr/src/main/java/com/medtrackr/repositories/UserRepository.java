package com.medtrackr.medtrackr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medtrackr.medtrackr.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username); 

    public User findByEmail(String email); 

    public Boolean existsByUsername(String username); 
}
