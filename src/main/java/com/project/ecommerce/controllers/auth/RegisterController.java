package com.project.ecommerce.controllers.auth;

import com.project.ecommerce.entities.ERole;
import com.project.ecommerce.entities.Role;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.services.RoleService;
import com.project.ecommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/register")
    public ModelAndView register(ModelAndView model) {
        model.addObject("user", new User());
        model.setViewName("/auth/register");
        return model;
    }
    @PostMapping("/register")
    public String createUser(ModelAndView model, @Valid User user, BindingResult result) {

        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getEmail() != null
                && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addObject("user", user);
            return "/auth/register";
        }

        List<Role> roles = new ArrayList<>();
        Role role = roleService.findRoleByName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setName(ERole.valueOf(ERole.ROLE_USER.name()));
        }
        roles.add(role);
        user.setRoles(roles);
        userService.create(user);

        return "redirect:/";
    }
}