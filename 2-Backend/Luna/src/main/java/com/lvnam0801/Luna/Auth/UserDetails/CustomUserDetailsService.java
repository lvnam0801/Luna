package com.lvnam0801.Luna.Auth.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lvnam0801.Luna.Resource.Core.User.Repository.UserDAO;
import com.lvnam0801.Luna.Resource.Core.User.Service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userRepository;

    public CustomUserDetailsService(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDAO user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(user);
    }
}