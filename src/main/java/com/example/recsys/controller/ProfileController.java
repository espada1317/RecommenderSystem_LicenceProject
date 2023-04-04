package com.example.recsys.controller;

import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.service.MovieService;
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
public class ProfileController {

    @Autowired
    private final UserAuthService userAuthService;

    @Autowired
    private final MovieService movieService;

    public ProfileController(UserAuthService userAuthService, MovieService movieService) {
        this.userAuthService = userAuthService;
        this.movieService = movieService;
    }

    @GetMapping(value = "/profile/overview")
    public String profilePage(Model model,
                              Principal principal) {
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("userActivity", movieService.getAllUserAndFriendActivity(principal.getName()));

        return "profile_overview";
    }

    @GetMapping(value = "/profile/myMovieList")
    public String personalMovieList(Model model,
                                    @Param("category") String category,
                                    @Param("year") Integer year,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("moviesReleaseYears", movieService.getDistinctPersonalReleaseYears(principal.getName()));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalMovieList", movieService.searchPersonalMoviesByMultipleFilter(principal.getName(), category, year, sortBy));
        return "my_movies_stats";
    }

    @GetMapping(value = "/profile/dashboard")
    public String personalDashboard(Model model,
                                    Principal principal) {
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("recentWatched", movieService.getRecentWatchedMovies(principal.getName()));
        model.addAttribute("planToWatchMovies", movieService.getPlanToWatchMovies(principal.getName()));
        return "my_dashboard";
    }

    @GetMapping(value = "/profile/reviews")
    public String personalReviews(Model model,
                                Principal principal) {
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("personalReviews", movieService.getReviewsByNickname(principal.getName()));
        return "my_reviews";
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

}