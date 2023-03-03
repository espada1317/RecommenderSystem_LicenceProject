package com.example.recsys.controller;

import com.example.recsys.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/getById/{id}")
    public String movieById(Model model, @PathVariable("id") int movieID)
    {
        model.addAttribute("movies", movieService.getMovieById(movieID));
        return "movie_details";
    }

    @GetMapping("/getByTitle/{title}")
    public String movieByTitle(Model model, @PathVariable("title") String movieTitle) {
        model.addAttribute("movies", movieService.getMovieByTitle(movieTitle));
        return "movie_details";
    }

    @GetMapping
    public String listOfMoviesByKeyword(Model model,
                                        @Param("keyword") String keyword,
                                        Principal principal) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("movies", movieService.searchMoviesByKeyword(keyword));
        model.addAttribute("principalName", principal.getName());
        return "movies";
    }

}
