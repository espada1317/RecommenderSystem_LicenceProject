package com.example.recsys.controller;

import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.entity.MovieReviews;
import com.example.recsys.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getById/{id}")
    public String movieById(Model model,
                            @PathVariable("id") int movieID,
                            Principal principal) {
        model.addAttribute("movies", movieService.getMovieById(movieID));
        model.addAttribute("movieID", movieID);

        Optional<MovieReviews> movieReview = movieService.getReviewByNicknameAndMovieId(principal.getName(), movieID);
        movieReview.ifPresent(movieReviews -> model.addAttribute("reviewInfo", movieReviews));

        return "movie_details";
    }

    @PostMapping(value = "/saveReview", params = "action=save")
    public String saveMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                     @RequestParam("id") Integer movieId,
                                     Principal principal) {
        movieService.saveReview(movieId, principal.getName(), movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/saveReview", params = "action=update")
    public String updateMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                  @RequestParam("id") Integer movieId,
                                  Principal principal) {
        movieService.updateReview(principal.getName(), movieId, movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/saveReview", params = "action=delete")
    public String deleteMovieReview(@RequestParam("id") Integer movieId,
                                  Principal principal) {
        movieService.deleteReview(principal.getName(), movieId);
        return "redirect:/movies/getById/" + movieId;
    }

    @GetMapping
    public String listOfMovies( Model model,
                                @Param("keyword") String keyword,
                                @Param("genre") String genre,
                                @Param("year") Integer year,
                                @Param("language") String language,
                                @Param("sortBy") String sortBy,
                                Principal principal) {
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("movieGenres", movieService.getMovieGenres());
        model.addAttribute("releaseYears", movieService.getReleaseYears());
        model.addAttribute("originLang", movieService.getOriginalLanguages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre",genre);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedLang", language);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("movies", movieService.searchMoviesByMultipleFilter(keyword, genre, year, language, sortBy));
        return "movies";
    }

}
