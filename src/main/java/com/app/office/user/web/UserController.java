package com.app.office.user.web;

import com.app.office.user.api.UserService;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.api.enumeration.UserRole;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private static final String BASE_URL = "/user";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(BASE_URL + "/list")
    public ModelAndView getList(ModelAndView mav) {
        List<UserDTO> users = userService.find();

        mav.addObject("users", users);
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping(BASE_URL + "/profile")
    public ModelAndView getProfile(ModelAndView mav) {
        UserDTO currentUser = userService.getCurrentUser();

        mav.addObject("user", currentUser);
        mav.setViewName("user/profile");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView mav) {
        mav.addObject("user", new UserCommand());
        mav.setViewName("user/register");
        return mav;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(value = "user") UserCommand userCommand) {
        UserDTO userDTO = userDTOFromRegisterCommand(userCommand);
        userDTO.getRoles().add(UserRole.END_CUSTOMER);

        userService.save(userDTO);
        return "redirect:/login";
    }

    @ScriptAssert(lang = "javascript", script = "_this.password == _this.passwordConfirm",
            reportOn = "password")
    private static final class UserCommand {
        private Long id;
        private String email;
        private String password;
        private String passwordConfirm;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordConfirm() {
            return passwordConfirm;
        }

        public void setPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
        }
    }

    private UserDTO userDTOFromRegisterCommand(UserCommand userCommand) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(userCommand.getId());
        userDTO.setEmail(userCommand.getEmail());
        userDTO.setPassword(userCommand.getPassword());

        return userDTO;
    }
}
