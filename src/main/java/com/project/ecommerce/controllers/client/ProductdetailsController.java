package com.project.ecommerce.controllers.client;

import com.project.ecommerce.entities.User;
import com.project.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/productdetails")
public class ProductdetailsController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView register(ModelAndView model) {
        model.addObject("user", new User());
        model.setViewName("client/productdetails");
        return model;
    }
}