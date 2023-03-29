package com.example.recsys.service;

import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.entity.Movie;
import com.example.recsys.entity.MovieReviews;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByMultipleFilter(String keyword, String genre, Integer year, String language, String sortBy);

    Movie getMovieById(int id);

    List<String> getMovieGenres();

    List<Integer> getReleaseYears();

    List<String> getOriginalLanguages();

    void saveReview(Integer movieId, String nickname, MovieReviewDto movieReviewsDto);

    Optional<MovieReviews> getReviewByNicknameAndMovieId(String nickname, Integer movieId);

    void updateReview(String nickname, Integer movieId, MovieReviewDto movieReviewDto);

    void deleteReview(String nickname, Integer movieId);

    List<MovieReviews> getReviewActivity(String nickname);
}