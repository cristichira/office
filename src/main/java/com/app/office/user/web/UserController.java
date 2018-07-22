package com.app.office.user.web;

import com.app.office.user.api.UserService;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.api.enumeration.UserRole;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(BASE_URL + "/{id}/profile")
    public ModelAndView getProfile(@PathVariable("id") Long userId, ModelAndView mav) {
        UserDTO currentUser = userService.findById(userId);

        mav.addObject("user", currentUser);
        mav.setViewName("user/profile");
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView getMyProfile(ModelAndView mav) {
        UserDTO currentUser = userService.getCurrentUser();

        mav.addObject("user", currentUser);
        mav.setViewName("user/profile");
        return mav;
    }

    @GetMapping(BASE_URL + "/{id}/edit")
    public ModelAndView getEdit(@PathVariable("id") Long userId, ModelAndView mav) {
        UserDTO userDTO = userService.findById(userId);
        UserCommand userCommand = userCommandFromUserDTO(userDTO);

        mav.addObject("user", userCommand);
        mav.setViewName("user/edit");
        return mav;
    }

    @PostMapping(BASE_URL + "/update")
    public String update(UserCommand userCommand) {
        UserDTO userDTO = userDTOFromEditCommand(userCommand);
        Long userId = userService.save(userDTO);

        return "redirect:/user/" + userId + "/profile";
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
        private String firstName;
        private String lastName;
        private String phone;
        private String mobilePhone;

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

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }
    }

    private UserDTO userDTOFromRegisterCommand(UserCommand userCommand) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userCommand.getEmail());
        userDTO.setPassword(userCommand.getPassword());

        return userDTO;
    }

    private UserDTO userDTOFromEditCommand(UserCommand userCommand) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(userCommand.getId());
        userDTO.setEmail(userCommand.getEmail());
        userDTO.setPassword(userCommand.getPassword());
        userDTO.setFirstName(userCommand.getFirstName());
        userDTO.setLastName(userCommand.getLastName());
        userDTO.setPhone(userCommand.getPhone());
        userDTO.setMobilePhone(userCommand.getMobilePhone());

        return userDTO;
    }

    private UserCommand userCommandFromUserDTO(UserDTO userDTO) {
        final UserCommand userCommand = new UserCommand();
        userCommand.setId(userDTO.getId());
        userCommand.setEmail(userDTO.getEmail());
        userCommand.setFirstName(userDTO.getFirstName());
        userCommand.setLastName(userDTO.getLastName());
        userCommand.setPhone(userDTO.getPhone());
        userCommand.setMobilePhone(userDTO.getMobilePhone());

        return userCommand;
    }
}
