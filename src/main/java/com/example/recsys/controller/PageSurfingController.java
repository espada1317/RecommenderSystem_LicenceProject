package com.example.recsys.controller;

import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.PreferencesService;
import com.example.recsys.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final PreferencesService preferencesService;

    public PageSurfingController(UserAuthService userAuthService, MovieService movieService, PreferencesService preferencesService) {
        this.userAuthService = userAuthService;
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

    @PostMapping(value = "/profile/settings", params = "action=update")
    public String modifyUserSettings(@ModelAttribute("userSettingsDto") UserSettingsDto userSettingsDto,
                                     Principal principal) {
        userAuthService.updateUser(userSettingsDto, principal);
        return "redirect:/profile/settings?success";
    }

    @PostMapping(value = "/profile/settings", params = "action=cancel")
    public String cancelModifying(@ModelAttribute("userSettingsDto") UserSettingsDto userSettingsDto) {
        return "redirect:/profile";
    }

    @GetMapping(value = "/preferences/movie")
    public String getMoviePreferencesByUser(@Param("action") String action,
                                            Model model) {
        model.addAttribute("movieGenres", movieService.getMovieGenres());
        return "movie_preferences";
    }

    @PostMapping(value = "/preferences/movie", params = "act=submit")
    public String saveUserMoviePreferences(@ModelAttribute("moviePreferencesDto") MoviePreferenceProfileDto moviePreferenceProfileDto,
                                           Principal principal) {
        preferencesService.saveMoviePreference(principal.getName(), moviePreferenceProfileDto);
        return "redirect:/";
    }

    @PostMapping(value = "/preferences/movie", params = "act=skip")
    public String skippedUserMoviePreferences(Principal principal) {
        preferencesService.skipMoviePreference(principal.getName());
        return "redirect:/";
    }

}
