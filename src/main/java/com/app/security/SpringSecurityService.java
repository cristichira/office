package com.app.security;

import com.app.office.shared.api.dto.NameIdDTO;
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

    @Override
    public NameIdDTO getCurrentUserAsNameIdDTO() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null && securityContext.getAuthentication() != null && securityContext.getAuthentication().getPrincipal() instanceof CurrentUser) {
            CurrentUser currentUser = (CurrentUser) securityContext.getAuthentication().getPrincipal();
            return new NameIdDTO(currentUser.getUsername(), currentUser.getUserId());
        }
        return null;
    }
}
