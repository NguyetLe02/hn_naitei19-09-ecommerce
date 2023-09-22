package com.example.ecommerce.controller.user;

import com.example.ecommerce.DTO.UserRegistrationDto;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationCotroller {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto registrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "user/auth/registration";
    }

    @PostMapping
    public String registration(@ModelAttribute("user")UserRegistrationDto registrationDto){
        if(userService.save(registrationDto) == null){ return "user/auth/registration"; }
        return "redirect:/login";
    }

}
