package com.azry.LMS.config;

import com.azry.LMS.entity.User;
import com.azry.LMS.service.impl.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            User userDetails = (User) authentication.getPrincipal();
            String loggedUsername = userDetails.getUsername();
            return userDetails;
        } else throw new RuntimeException("User is not authenticated");
    }
}