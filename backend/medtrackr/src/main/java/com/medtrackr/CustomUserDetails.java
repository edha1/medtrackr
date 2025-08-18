package com.medtrackr.medtrackr;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String email;  
    private Collection<? extends GrantedAuthority> authorities;
    private boolean active;

    public CustomUserDetails(String username, String password, String email,
                             Collection<? extends GrantedAuthority> authorities, boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
