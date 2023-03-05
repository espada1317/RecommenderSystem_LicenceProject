package com.example.recsys.controller;

import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PageSurfingController {

    @Autowired
    private final UserAuthService userAuthService;

    public PageSurfingController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping(value = "/")
    public String indexPage(Principal principal,
                            Model model) {
        if(principal != null) {
            model.addAttribute("principalName", principal.getName());
        }

        return "index";
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping(value = "/profile")
    public String profilePage(Model model,
                              Principal principal) {
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        return "profile_overview";
    }

    @GetMapping(value = "/profile/settings")
    public String profileSettings(Principal principal,
                                  Model model) {
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        return "profile_settings";
    }

    @PostMapping(value= "/profile/settings", params = "action=update")
    public String modifyUserSettings(@ModelAttribute("userSettingsDto") UserSettingsDto userSettingsDto,
                                     Principal principal,
                                     Model model) {
        userAuthService.updateUser(userSettingsDto, principal);
        return "redirect:/profile/settings?success";
    }

    @PostMapping(value= "/profile/settings", params = "action=cancel")
    public String cancelModifying(@ModelAttribute("userSettingsDto") UserSettingsDto userSettingsDto,
                                     Principal principal,
                                     Model model) {
        return "redirect:/profile";
    }

}
