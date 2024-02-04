package com.project.ecommerce.controllers.auth;

import com.project.ecommerce.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping()
    public ModelAndView register(ModelAndView model) {
        model.addObject("user", new User());
        model.setViewName("auth/login");
        return model;
    }
}

