package com.app.office.core.controller;


import com.app.office.user.api.dto.CurrentUser;
import com.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class OAPControllerAdvice {

    private final SecurityService securityService;

    @Autowired
    public OAPControllerAdvice(SecurityService securityService) {
        this.securityService = securityService;
    }

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser() {
        return securityService.getCurrentUser();
    }
}
