package com.example.recsys.controller;

import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ReviewController {

    @Autowired
    private final MovieService movieService;

    public ReviewController(MovieService movieService) {
        this.movieService = movieService;
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

}