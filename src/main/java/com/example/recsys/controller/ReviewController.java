package com.example.recsys.controller;

import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.dto.TvReviewDto;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.TvSeriesService;
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

    @Autowired
    private final TvSeriesService tvSeriesService;

    public ReviewController(MovieService movieService, TvSeriesService tvSeriesService) {
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=save")
    public String saveMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                  @RequestParam("id") Integer movieId,
                                  Principal principal) {
        movieService.saveReview(movieId, principal.getName(), movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=update")
    public String updateMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                    @RequestParam("id") Integer movieId,
                                    Principal principal) {
        movieService.updateReview(principal.getName(), movieId, movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=delete")
    public String deleteMovieReview(@RequestParam("id") Integer movieId,
                                    Principal principal) {
        movieService.deleteReview(principal.getName(), movieId);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=save")
    public String saveTvReview(@ModelAttribute("tvReviewDto") TvReviewDto tvReviewDto,
                                  @RequestParam("id") Integer movieId,
                                  Principal principal) {
        tvSeriesService.saveReview(movieId, principal.getName(), tvReviewDto);
        return "redirect:/tv/getById/" + movieId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=update")
    public String updateTvReview(@ModelAttribute("tvReviewDto") TvReviewDto tvReviewDto,
                                    @RequestParam("id") Integer tvId,
                                    Principal principal) {
        tvSeriesService.updateReview(principal.getName(), tvId, tvReviewDto);
        return "redirect:/tv/getById/" + tvId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=delete")
    public String deleteTvReview(@RequestParam("id") Integer movieId,
                                    Principal principal) {
        tvSeriesService.deleteReview(principal.getName(), movieId);
        return "redirect:/tv/getById/" + movieId;
    }

}