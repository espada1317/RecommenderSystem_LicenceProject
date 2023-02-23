package com.example.recsys.service.implementation;

import com.example.recsys.entity.Movie;
import com.example.recsys.repository.MovieRepository;
import com.example.recsys.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public static final int LIMIT = 50;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .limit(LIMIT)
                .collect(Collectors.toList());
//        return movieRepository.findFirst10ByOrderByTitleName();
    }

    @Override
    public List<Movie> searchMoviesByKeyword(String keyword) {
        if(keyword != null) {
            return movieRepository.findByTitleContainingOrOverviewContaining(keyword);
        }
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
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

}