package com.lvnam0801.Luna.Auth.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final UserDAO userDAO;

    public CustomUserDetails(UserDAO user) {
        this.userDAO = user;
    }

    public Integer getUserId() {
        return userDAO.userID(); // adapt to your field
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // no roles for now
    }

    @Override
    public String getPassword() {
        return userDAO.password();
    }

    @Override
    public String getUsername() {
        return userDAO.userName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}