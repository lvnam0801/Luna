package com.lvnam0801.Luna.Resource.Core.User.Context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lvnam0801.Luna.Auth.UserDetails.CustomUserDetails;

@Component
public class UserContext {
    
    public int getCurrentUserID() {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if (authentication != null && authentication.isAuthenticated()) {
        //     Object principal = authentication.getPrincipal();

        //     if (principal instanceof CustomUserDetails) {
        //         return ((CustomUserDetails) principal).getUserId();
        //     }
        // }
        // throw new RuntimeException("User is not authenticated or user ID is not available");
        return 1;
    }
}