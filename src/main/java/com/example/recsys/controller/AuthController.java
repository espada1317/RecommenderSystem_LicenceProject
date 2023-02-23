package com.example.recsys.controller;

import com.example.recsys.entity.UserInfo;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping(value = "/signIn")
    public String loginPage(Model model) {
        return "sign_in";
    }

    @GetMapping(value = "/signUp")
    public String signUpPage(Model model) {
        return "sign_up";
    }

    @PostMapping(value = "/signUp")
    public String addNewUser(@ModelAttribute UserInfo userInfo) {
        userInfo.setRoles("USER");
        userAuthService.addUser(userInfo);
        return "redirect:/signIn";
    }

}
