package com.example.ecommerce.controller.user;
import com.example.ecommerce.DTO.UserLoginDto;
import com.example.ecommerce.DTO.UserRegistrationDto;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @ModelAttribute("user")
    public UserLoginDto loginDto(){
        return new UserLoginDto();
    }

    @GetMapping
    public String getLoginForm(){
        return "user/auth/login";
    }

    @PostMapping
    public String login(@ModelAttribute("user") UserLoginDto loginDto, HttpSession session) {
        User user = userService.login(loginDto);
        if (user == null) {
            return "user/auth/login";
        }

        // Đặt thông tin người dùng vào phiên làm việc
        session.setAttribute("loggedInUser", user);

        return "redirect:/";
    }

}