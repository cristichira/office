package com.app.office.core.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(final HttpServletRequest request, final Model model) {
        Throwable throwable = (Throwable) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String errorMessage = null;
        if (throwable != null) {
            throwable = unwrapCause(throwable);
            errorMessage = throwable.getMessage();
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, null);
        }
        model.addAttribute("errorMessage", errorMessage);

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    private Throwable unwrapCause(final Throwable throwable) {
        Throwable result = throwable;
        while (result.getCause() != null) {
            result = result.getCause();
        }
        return result;
    }
}