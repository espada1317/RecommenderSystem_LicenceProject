package com.example.recsys.comparators.movies;

import com.example.recsys.entity.Movie;

import java.util.Comparator;

public class MovieImdbRatingComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Double.compare(o1.getVoteaverage(), o2.getVoteaverage());
    }
}
