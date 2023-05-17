package com.example.recsys.controller;

import com.example.recsys.entity.Movie;
import com.example.recsys.entity.MovieReviews;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final ProfileService profileService;

    public MovieController(MovieService movieService, ProfileService profileService) {
        this.movieService = movieService;
        this.profileService = profileService;
    }

    @GetMapping("/getById/{id}")
    public String movieById(Model model,
                            @PathVariable("id") int movieID,
                            Principal principal) {
        model.addAttribute("isMoviePage", true);
        Movie movieDetails = movieService.getMovieById(movieID);
        model.addAttribute("similarMovies", movieService.getSimilarContent(movieDetails));

        model.addAttribute("movies", movieDetails);
        model.addAttribute("movieID", movieID);
        Optional<MovieReviews> movieReview = movieService.getReviewByNicknameAndMovieId(principal.getName(), movieID);
        movieReview.ifPresent(movieReviews -> model.addAttribute("reviewInfo", movieReviews));

        return "movie_details";
    }

    @GetMapping
    public String listOfMovies( Model model,
                                @Param("keyword") String keyword,
                                @Param("genre") String genre,
                                @Param("year") Integer year,
                                @Param("language") String language,
                                @Param("sortBy") String sortBy,
                                Principal principal) {
        model.addAttribute("isMoviePage", true);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("movieGenres", movieService.getMovieGenres());
        model.addAttribute("releaseYears", movieService.getReleaseYears());
        model.addAttribute("originLang", movieService.getOriginalLanguages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre",genre);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedLang", language);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("personalRecommend", movieService.personalRecommended(principal.getName(), profileService.getAllActiveFollowers(principal.getName())));
        model.addAttribute("movies", movieService.searchMoviesByMultipleFilter(keyword, genre, year, language, sortBy));
        model.addAttribute("friendsRecommend", movieService.recommendedByFriends( profileService.getAllActiveFollowers(principal.getName()), principal.getName() ));
        return "movies";
    }

}
