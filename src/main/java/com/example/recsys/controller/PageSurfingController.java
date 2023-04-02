package com.example.recsys.controller;

import com.example.recsys.service.MovieService;
import com.example.recsys.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageSurfingController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final PreferencesService preferencesService;

    public PageSurfingController(MovieService movieService, PreferencesService preferencesService) {
        this.movieService = movieService;
        this.preferencesService = preferencesService;
    }

    @GetMapping(value = "/")
    public String indexPage(Principal principal,
                            Model model) {
        if(principal != null) {
            model.addAttribute("principalName", principal.getName());
            if(preferencesService.getPreferenceProfileOfUser(principal.getName()).isEmpty()) {
                return "redirect:/preferences/movie";
            }
        }

        return "index";
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }

}