package com.example.recsys.controller;

import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PreferencesController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final PreferencesService preferencesService;

    public PreferencesController(MovieService movieService, PreferencesService preferencesService) {
        this.movieService = movieService;
        this.preferencesService = preferencesService;
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