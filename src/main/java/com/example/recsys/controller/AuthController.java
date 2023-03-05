package com.example.recsys.controller;

import com.example.recsys.entity.UserInfo;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping(value = "/signIn")
    public String signIn(Principal principal) {
        if(principal == null) {
            return "sign_in";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/signUp")
    public String signUpPage(Principal principal) {
        if(principal == null) {
            return "sign_up";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/signUp")
    public String addNewUser(@ModelAttribute("userInfo") UserInfo userInfo) {
        userInfo.setRoles("USER");
        return userAuthService.addUser(userInfo) ? "redirect:/signUp?success" : "redirect:/signUp?error";
    }

}