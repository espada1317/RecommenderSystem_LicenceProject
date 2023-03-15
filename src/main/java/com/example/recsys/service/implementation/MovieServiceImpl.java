package com.example.recsys.service.implementation;

import com.example.recsys.comparators.*;
import com.example.recsys.entity.Movie;
import com.example.recsys.repository.MovieRepository;
import com.example.recsys.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public static final int LIMIT = 48;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesByMultipleFilter(String keyword, String genre, Integer year, String language, String sortBy) {
        List<Movie> result = getAllMovies();

        if(keyword != null) {
           List<Movie> keywordList = movieRepository.findByTitleContainingOrOverviewContaining(keyword);
           result.retainAll(keywordList);
        }
        if(genre != null && !genre.equals("*")) {
            List<Movie> genreList = movieRepository.findByGenreContaining(genre);
            result.retainAll(genreList);
        }
        if(year != null && year != 0) {
            List<Movie> yearList = movieRepository.findByYearContaining(year);
            result.retainAll(yearList);
        }
        if(language != null && !language.equals("*")) {
            List<Movie> langList = movieRepository.findByLanguageContaining(language);
            result.retainAll(langList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new MovieTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new MovieTitleComparator().reversed());
                }
                case "yearAsc" -> {
                    result.sort(new MovieYearComparator());
                }
                case "yearDesc" -> {
                    result.sort(new MovieYearComparator().reversed());
                }
                case "langAsc" -> {
                    result.sort(new MovieLanguageComparator());
                }
                case "langDesc" -> {
                    result.sort(new MovieLanguageComparator().reversed());
                }
                case "lengthAsc" -> {
                    result.sort(new MovieLengthComparator());
                }
                case "lengthDesc" -> {
                    result.sort(new MovieLengthComparator().reversed());
                }
                case "popularityAsc" -> {
                    result.sort(new MoviePopularityComparator());
                }
                case "popularityDesc" -> {
                    result.sort(new MoviePopularityComparator().reversed());
                }
                case "imdbAsc" -> {
                    result.sort(new MovieImdbRatingComparator());
                }
                case "imdbDesc" -> {
                    result.sort(new MovieImdbRatingComparator().reversed());
                }
            }
        }

        return StreamSupport.stream(result.spliterator(), false)
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<String> getMovieGenres() {
        return movieRepository.findAllDistinctGenreList();
    }

    @Override
    public List<Integer> getReleaseYears() {
        return movieRepository.findAllDistinctYearList();
    }

    @Override
    public List<String> getOriginalLanguages() {
        return movieRepository.findAllDistinctLanguageList();
    }

}