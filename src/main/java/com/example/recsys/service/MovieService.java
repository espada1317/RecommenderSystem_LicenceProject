package com.example.recsys.service;

import com.example.recsys.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByMultipleFilter(String keyword, String genre);

    Movie getMovieById(int id);

    Movie getMovieByTitle(String title);

    List<String> getMovieGenres();
}