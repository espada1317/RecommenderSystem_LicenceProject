package com.example.recsys.controller;

import com.example.recsys.dto.RecentReviewsDto;
import com.example.recsys.dto.UserActivityDto;
import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private final UserAuthService userAuthService;

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final TvSeriesService tvSeriesService;

    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final AnimeService animeService;

    public ProfileController(UserAuthService userAuthService, MovieService movieService, TvSeriesService tvSeriesService, ProfileService profileService, AnimeService animeService) {
        this.userAuthService = userAuthService;
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.profileService = profileService;
        this.animeService = animeService;
    }

    @GetMapping(value = "/profile/overview")
    public String profilePage(Model model,
                              Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isOverview", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        List<UserActivityDto> userActivityDtoList = profileService.getAllUserRecentActivity(
                movieService.getAllUserAndFriendsMovieActivity(principal.getName()),
                tvSeriesService.getAllUserAndFriendsTvActivity(principal.getName()),
                animeService.getAllUserAndFriendsAnimeActivity(principal.getName()));
        model.addAttribute("userActivity", userActivityDtoList);

        return "profile_overview";
    }

    @GetMapping(value = "/profile/myMovieList")
    public String personalMovieList(Model model,
                                    @Param("category") String category,
                                    @Param("year") Integer year,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isMovieStats", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("moviesReleaseYears", movieService.getDistinctPersonalReleaseYears(principal.getName()));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalMovieList", movieService.searchPersonalMoviesByMultipleFilter(principal.getName(), category, year, sortBy));
        return "my_movies_stats";
    }

    @GetMapping(value = "/profile/myTvList")
    public String personalTvList(Model model,
                                    @Param("category") String category,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isTvStats", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalTvList", tvSeriesService.searchPersonalTvByMultipleFilter(principal.getName(), category, sortBy));
        return "my_tv_stats";
    }

    @GetMapping(value = "/profile/myAnimeList")
    public String personalAnimeList(Model model,
                                 @Param("category") String category,
                                 @Param("sortBy") String sortBy,
                                 Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isAnimeStats", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalAnimeList", animeService.searchPersonalAnimeByMultipleFilter(principal.getName(), category, sortBy));
        return "my_anime_stats";
    }

    @GetMapping(value = "/profile/dashboard")
    public String personalDashboard(Model model,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isDashboard", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("recentWatchedMovies", movieService.getRecentWatchedMovies(principal.getName()));
        model.addAttribute("planToWatchMovies", movieService.getPlanToWatchMovies(principal.getName()));
        model.addAttribute("recentWatchedTvs", tvSeriesService.getRecentWatchedTvs(principal.getName()));
        model.addAttribute("planToWatchMoviesTvs", tvSeriesService.getPlanToWatchTvs(principal.getName()));
        model.addAttribute("recentWatchedAnime", animeService.getRecentWatchedAnime(principal.getName()));
        model.addAttribute("planToWatchAnime", animeService.getPlanToWatchAnime(principal.getName()));
        return "my_dashboard";
    }

    @GetMapping(value = "/profile/reviews")
    public String personalReviews(Model model,
                                Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isReviewStats", true);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        List<RecentReviewsDto> userReviewsList = profileService.getAllRecentReviews(
                movieService.getReviewsByNickname(principal.getName()),
                tvSeriesService.getReviewsByNickname(principal.getName()),
                animeService.getReviewsByNickname(principal.getName()));
        model.addAttribute("userReviews", userReviewsList);
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
    public String cancelModifying() {
        return "redirect:/profile";
    }

}