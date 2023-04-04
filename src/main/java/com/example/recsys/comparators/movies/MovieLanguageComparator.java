package com.example.recsys.comparators.movies;

import com.example.recsys.entity.Movie;

import java.util.Comparator;

public class MovieLanguageComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getLanguage().compareTo(o2.getLanguage());
    }
}
