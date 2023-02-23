package com.example.recsys.service;

import com.example.recsys.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByKeyword(String keyword);

    Movie getMovieById(int id);

    Movie getMovieByTitle(String title);
}