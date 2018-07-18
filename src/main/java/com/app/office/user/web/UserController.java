package com.app.office.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public ModelAndView getList(ModelAndView mav) {
        mav.setViewName("user/list");
        return mav;
    }
}
