package com.example.recsys.controller;

import com.example.recsys.dto.CurrentNicknameDto;
import com.example.recsys.dto.RecentReviewsDto;
import com.example.recsys.dto.UserActivityDto;
import com.example.recsys.dto.UserSettingsDto;
import com.example.recsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private final BookService bookService;

    public ProfileController(UserAuthService userAuthService, MovieService movieService, TvSeriesService tvSeriesService, ProfileService profileService, AnimeService animeService, BookService bookService) {
        this.userAuthService = userAuthService;
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.profileService = profileService;
        this.animeService = animeService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/profile/overview")
    public String profilePage(Model model,
                              Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isOverview", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        List<UserActivityDto> userActivityDtoList = profileService.getAllUserRecentActivity(
                movieService.getAllUserAndFriendsMovieActivity(principal.getName()),
                tvSeriesService.getAllUserAndFriendsTvActivity(principal.getName()),
                animeService.getAllUserAndFriendsAnimeActivity(principal.getName()),
                bookService.getAllUserAndFriendsBooksActivity(principal.getName()));
        model.addAttribute("userActivity", userActivityDtoList);

        return "profile_overview";
    }

    @GetMapping(value = "/profile/overview/{nickname}")
    public String profilePageAnotherUser(Model model,
                                        @PathVariable("nickname") String nickname,
                                         Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isOverview", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        List<UserActivityDto> userActivityDtoList = profileService.getAllUserRecentActivity(
                movieService.getAllUserAndFriendsMovieActivity(nickname),
                tvSeriesService.getAllUserAndFriendsTvActivity(nickname),
                animeService.getAllUserAndFriendsAnimeActivity(nickname),
                bookService.getAllUserAndFriendsBooksActivity(nickname));
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
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("moviesReleaseYears", movieService.getDistinctPersonalReleaseYears(principal.getName()));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalMovieList", movieService.searchPersonalMoviesByMultipleFilter(principal.getName(), category, year, sortBy));
        return "my_movies_stats";
    }

    @GetMapping(value = "/profile/myMovieList/{nickname}")
    public String personalMovieList(Model model,
                                    @PathVariable("nickname") String nickname,
                                    @Param("category") String category,
                                    @Param("year") Integer year,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isMovieStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("moviesReleaseYears", movieService.getDistinctPersonalReleaseYears(nickname));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalMovieList", movieService.searchPersonalMoviesByMultipleFilter(nickname, category, year, sortBy));
        return "my_movies_stats";
    }

    @GetMapping(value = "/profile/myTvList")
    public String personalTvList(Model model,
                                    @Param("category") String category,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isTvStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalTvList", tvSeriesService.searchPersonalTvByMultipleFilter(principal.getName(), category, sortBy));
        return "my_tv_stats";
    }

    @GetMapping(value = "/profile/myTvList/{nickname}")
    public String personalTvList(Model model,
                                 @PathVariable("nickname") String nickname,
                                 @Param("category") String category,
                                 @Param("sortBy") String sortBy,
                                 Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isTvStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalTvList", tvSeriesService.searchPersonalTvByMultipleFilter(nickname, category, sortBy));
        return "my_tv_stats";
    }

    @GetMapping(value = "/profile/myAnimeList")
    public String personalAnimeList(Model model,
                                 @Param("category") String category,
                                 @Param("sortBy") String sortBy,
                                 Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isAnimeStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalAnimeList", animeService.searchPersonalAnimeByMultipleFilter(principal.getName(), category, sortBy));
        return "my_anime_stats";
    }

    @GetMapping(value = "/profile/myAnimeList/{nickname}")
    public String personalAnimeList(Model model,
                                    @PathVariable("nickname") String nickname,
                                    @Param("category") String category,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isAnimeStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalAnimeList", animeService.searchPersonalAnimeByMultipleFilter(nickname, category, sortBy));
        return "my_anime_stats";
    }

    @GetMapping(value = "/profile/myBookList")
    public String personalBookList(Model model,
                                    @Param("category") String category,
                                    @Param("sortBy") String sortBy,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isBookStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalBookList", bookService.searchPersonalBooksByMultipleFilter(principal.getName(), category, sortBy));
        return "my_books_stats";
    }

    @GetMapping(value = "/profile/myBookList/{nickname}")
    public String personalBookList(Model model,
                                   @PathVariable("nickname") String nickname,
                                   @Param("category") String category,
                                   @Param("sortBy") String sortBy,
                                   Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isBookStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("personalBookList", bookService.searchPersonalBooksByMultipleFilter(nickname, category, sortBy));
        return "my_books_stats";
    }

    @GetMapping(value = "/profile/dashboard")
    public String personalDashboard(Model model,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isDashboard", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("recentWatchedMovies", movieService.getRecentWatchedMovies(principal.getName()));
        model.addAttribute("planToWatchMovies", movieService.getPlanToWatchMovies(principal.getName()));
        model.addAttribute("recentWatchedTvs", tvSeriesService.getRecentWatchedTvs(principal.getName()));
        model.addAttribute("planToWatchMoviesTvs", tvSeriesService.getPlanToWatchTvs(principal.getName()));
        model.addAttribute("recentWatchedAnime", animeService.getRecentWatchedAnime(principal.getName()));
        model.addAttribute("planToWatchAnime", animeService.getPlanToWatchAnime(principal.getName()));
        model.addAttribute("recentCompletedBooks", bookService.getRecentCompletedBook(principal.getName()));
        model.addAttribute("planToReadBooks", bookService.getPlanToReadBook(principal.getName()));
        return "my_dashboard";
    }

    @GetMapping(value = "/profile/dashboard/{nickname}")
    public String personalDashboard(Model model,
                                    @PathVariable("nickname") String nickname,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isDashboard", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("recentWatchedMovies", movieService.getRecentWatchedMovies(nickname));
        model.addAttribute("planToWatchMovies", movieService.getPlanToWatchMovies(nickname));
        model.addAttribute("recentWatchedTvs", tvSeriesService.getRecentWatchedTvs(nickname));
        model.addAttribute("planToWatchMoviesTvs", tvSeriesService.getPlanToWatchTvs(nickname));
        model.addAttribute("recentWatchedAnime", animeService.getRecentWatchedAnime(nickname));
        model.addAttribute("planToWatchAnime", animeService.getPlanToWatchAnime(nickname));
        model.addAttribute("recentCompletedBooks", bookService.getRecentCompletedBook(nickname));
        model.addAttribute("planToReadBooks", bookService.getPlanToReadBook(nickname));
        return "my_dashboard";
    }

    @GetMapping(value = "/profile/reviews")
    public String personalReviews(Model model,
                                Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isReviewStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        List<RecentReviewsDto> userReviewsList = profileService.getAllRecentReviews(
                movieService.getReviewsByNickname(principal.getName()),
                tvSeriesService.getReviewsByNickname(principal.getName()),
                animeService.getReviewsByNickname(principal.getName()),
                bookService.getReviewsByNickname(principal.getName()));
        model.addAttribute("userReviews", userReviewsList);
        return "my_reviews";
    }

    @GetMapping(value = "/profile/reviews/{nickname}")
    public String personalReviews(Model model,
                                  @PathVariable("nickname") String nickname,
                                  Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isReviewStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        List<RecentReviewsDto> userReviewsList = profileService.getAllRecentReviews(
                movieService.getReviewsByNickname(nickname),
                tvSeriesService.getReviewsByNickname(nickname),
                animeService.getReviewsByNickname(nickname),
                bookService.getReviewsByNickname(nickname));
        model.addAttribute("userReviews", userReviewsList);
        return "my_reviews";
    }

    @GetMapping(value="/profile/stats")
    public String personalStats(Model model,
                                Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isPersonalStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("movieStats", profileService.getPersonalMovieStats(movieService.getAllActivityByNickname(principal.getName())));
        model.addAttribute("tvStats", profileService.getPersonalTvStats(tvSeriesService.getAllActivityByNickname(principal.getName())));
        model.addAttribute("animeStats", profileService.getPersonalAnimeStats(animeService.getAllActivityByNickname(principal.getName())));
        model.addAttribute("bookStats", profileService.getPersonalBookStats(bookService.getAllActivityByNickname(principal.getName())));
        model.addAttribute("totalStats", profileService.getTotalTime(movieService.getAllActivityByNickname(principal.getName()),
                                                                                tvSeriesService.getAllActivityByNickname(principal.getName()),
                                                                                animeService.getAllActivityByNickname(principal.getName())
        ));
        return "my_stats";
    }

    @GetMapping(value="/profile/stats/{nickname}")
    public String personalStats(Model model,
                                @PathVariable("nickname") String nickname,
                                Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isPersonalStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("nickname", nickname);
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), nickname));
        model.addAttribute("userDetails", userAuthService.findUserByNickname(nickname));
        model.addAttribute("movieStats", profileService.getPersonalMovieStats(movieService.getAllActivityByNickname(nickname)));
        model.addAttribute("tvStats", profileService.getPersonalTvStats(tvSeriesService.getAllActivityByNickname(nickname)));
        model.addAttribute("animeStats", profileService.getPersonalAnimeStats(animeService.getAllActivityByNickname(nickname)));
        model.addAttribute("bookStats", profileService.getPersonalBookStats(bookService.getAllActivityByNickname(nickname)));
        model.addAttribute("totalStats", profileService.getTotalTime(movieService.getAllActivityByNickname(nickname),
                                                                                tvSeriesService.getAllActivityByNickname(nickname),
                                                                                animeService.getAllActivityByNickname(nickname)
        ));
        return "my_stats";
    }

    @GetMapping(value = "/profile/friends")
    public String personalFriends(Model model,
                                    Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isFriendStats", true);
        model.addAttribute("followerPage", false);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(principal.getName()));
        model.addAttribute("friends", profileService.getAllFollowersInfo(profileService.getAllActiveFollowers(principal.getName()), principal.getName()));
        return "my_friends";
    }

    @GetMapping(value = "/profile/friends/{username}")
    public String personalFriends(Model model,
                                  @PathVariable("username") String username,
                                  Principal principal) {
        model.addAttribute("isProfilePage", true);
        model.addAttribute("isFriendStats", true);
        model.addAttribute("followerPage", true);
        model.addAttribute("username", username);
        model.addAttribute("userDetails", userAuthService.findUserByNickname(username));
        model.addAttribute("isFollow", profileService.getExistingFollowRelation(principal.getName(), username));
        model.addAttribute("friends", profileService.getAllFollowersInfo(profileService.getAllActiveFollowers(username), principal.getName()));
        return "my_friends";
    }

    @PostMapping(value = "/profile/follow/{nickname}", params = "action=follow")
    public String saveFollower(@PathVariable("nickname") String nickname,
                               Principal principal) {
        profileService.saveFollower(principal.getName(), nickname);
        return "redirect:/profile/dashboard/" + nickname;
    }

    @PostMapping(value = "/profile/follow/{nickname}", params = "action=unfollow")
    public String deleteFollower(@PathVariable("nickname") String nickname,
                                 Principal principal) {
        profileService.deleteFollower(principal.getName(), nickname);
        return "redirect:/profile/dashboard/" + nickname;
    }

    @PostMapping(value = "/profile/unfollow/{nickname}", params = "action=unfollow")
    public String deleteFollowerFromProfile(@PathVariable("nickname") String nickname,
                                            @ModelAttribute("currentNicknameDto") CurrentNicknameDto currentNicknameDto,
                                            Principal principal) {
        profileService.deleteFollower(principal.getName(), nickname);
        return "redirect:/profile/friends/" + currentNicknameDto.getNickname();
    }

    @PostMapping(value = "/profile/unfollow/{nickname}", params = "action=follow")
    public String saveFollowerFromProfile(@PathVariable("nickname") String nickname,
                                          @ModelAttribute("currentNicknameDto") CurrentNicknameDto currentNicknameDto,
                                          Principal principal) {
        profileService.saveFollower(principal.getName(), nickname);
        return "redirect:/profile/friends/" + currentNicknameDto.getNickname();
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