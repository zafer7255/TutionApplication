package com.coaching_system.CoachingApp.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private StudentNewTable studentNewTable;
    public UserPrincipal(StudentNewTable studentNewTable)
    {
        this.studentNewTable = studentNewTable;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USERS"));
    }
    @Override
    public String getPassword() {
        return studentNewTable.getPassword();
    }

    @Override
    public String getUsername() {
        return studentNewTable.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }
}
