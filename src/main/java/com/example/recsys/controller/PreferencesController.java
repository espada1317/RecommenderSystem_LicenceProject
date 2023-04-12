package com.example.recsys.controller;

import com.example.recsys.dto.AnimePreferenceProfileDto;
import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.dto.TvPreferenceProfileDto;
import com.example.recsys.service.AnimeService;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.PreferencesService;
import com.example.recsys.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TvSeriesService tvSeriesService;

    @Autowired
    private final AnimeService animeService;

    @Autowired
    private final PreferencesService preferencesService;

    public PreferencesController(MovieService movieService, TvSeriesService tvSeriesService, AnimeService animeService, PreferencesService preferencesService) {
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.animeService = animeService;
        this.preferencesService = preferencesService;
    }

    @GetMapping(value = "/preferences")
    public String getStartPreferencePage(Model model) {
        return "start_preferences";
    }

    @PostMapping(value = "/preferences", params = "act=submit")
    public String startUserPreferences() {
        return "redirect:/preferences/movie";
    }

    @PostMapping(value = "/preferences", params = "act=skip")
    public String skippAllUserPreferences(Principal principal) {
        preferencesService.skipAllPreference(principal.getName());
        return "redirect:/";
    }

    @GetMapping(value = "/preferences/movie")
    public String getMoviePreferencesByUser(Model model) {
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
        return "redirect:/preferences/tv";
    }

    @GetMapping(value = "/preferences/tv")
    public String getTvPreferencesByUser(Model model) {
        model.addAttribute("tvGenres", tvSeriesService.getTvGenres());
        return "tv_preferences";
    }

    @PostMapping(value = "/preferences/tv", params = "act=submit")
    public String saveUserTvPreferences(@ModelAttribute("tvPreferencesDto") TvPreferenceProfileDto tvPreferenceProfileDto,
                                           Principal principal) {
        preferencesService.saveTvPreference(principal.getName(), tvPreferenceProfileDto);
        return "redirect:/preferences/anime";
    }

    @PostMapping(value = "/preferences/tv", params = "act=skip")
    public String skippedUserTvPreferences(Principal principal) {
        preferencesService.skipTvPreference(principal.getName());
        return "redirect:/preferences/anime";
    }

    @GetMapping(value = "/preferences/anime")
    public String getAnimePreferencesByUser(Model model) {
        model.addAttribute("animeGenres", animeService.getAnimeGenres());
        return "anime_preferences";
    }

    @PostMapping(value = "/preferences/anime", params = "act=submit")
    public String saveUserAnimePreferences(@ModelAttribute("animePreferencesDto") AnimePreferenceProfileDto animePreferenceProfileDto,
                                        Principal principal) {
        preferencesService.saveAnimePreference(principal.getName(), animePreferenceProfileDto);
        return "redirect:/";
    }

    @PostMapping(value = "/preferences/anime", params = "act=skip")
    public String skippedUserAnimePreferences(Principal principal) {
        preferencesService.skipAnimePreference(principal.getName());
        return "redirect:/";
    }

}