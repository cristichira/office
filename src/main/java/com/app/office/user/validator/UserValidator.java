package com.app.office.user.validator;

import com.app.office.user.impl.repository.UserRepository;
import com.app.office.user.web.UserController;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateRegister(UserController.UserCommand userCommand, BindingResult result) {

        if (userRepository.findByEmailIgnoreCase(userCommand.getEmail()).isPresent()) {
            result.rejectValue("email", "Duplicate.email", "An user with this email already exists.");
        }

        if (!StringUtils.equals(userCommand.getPassword(), userCommand.getPasswordConfirm())) {
            result.rejectValue("passwordConfirm", "Missmatch.passwordConfirm", "The given passwords do not match.");
        }
    }
}
