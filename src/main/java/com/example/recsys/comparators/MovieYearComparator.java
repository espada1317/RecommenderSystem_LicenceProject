package com.example.recsys.comparators;

import com.example.recsys.entity.Movie;

import java.util.Comparator;

public class MovieYearComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getRelease().compareTo(o2.getRelease());
    }
}
