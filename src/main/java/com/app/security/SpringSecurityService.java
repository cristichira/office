package com.app.security;

import com.app.office.user.api.dto.CurrentUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityService implements SecurityService {
    @Override
    public CurrentUser getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null && securityContext.getAuthentication().getPrincipal() instanceof CurrentUser) {
            return (CurrentUser) securityContext.getAuthentication().getPrincipal();
        }
        return null;
    }
}
