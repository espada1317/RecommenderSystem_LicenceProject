package com.example.recsys.service;

import com.example.recsys.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByMultipleFilter(String keyword, String genre, Integer year, String language, String sortBy);

    Movie getMovieById(int id);

    Movie getMovieByTitle(String title);

    List<String> getMovieGenres();

    List<Integer> getReleaseYears();

    List<String> getOriginalLanguages();

}