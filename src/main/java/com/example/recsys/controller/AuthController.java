package com.example.recsys.controller;

import com.example.recsys.entity.UserInfo;
import com.example.recsys.exceptions.UserAlreadyRegisteredException;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping(value = "/signIn")
    public String signIn() {
        return "sign_in";
    }

    @GetMapping(value = "/signUp")
    public String signUpPage() {
        return "sign_up";
    }

    @PostMapping(value = "/signUp")
    public String addNewUser(@ModelAttribute("userInfo") UserInfo userInfo) {
        userInfo.setRoles("USER");
        try {
            userAuthService.addUser(userInfo);
        } catch (UserAlreadyRegisteredException userAlreadyRegisteredException) {
            return "redirect:/signUp?error";
        }

        return "redirect:/signUp?success";
    }

}