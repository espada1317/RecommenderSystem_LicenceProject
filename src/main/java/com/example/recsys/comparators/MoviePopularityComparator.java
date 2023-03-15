package com.example.recsys.comparators;

import com.example.recsys.entity.Movie;

import java.util.Comparator;

public class MoviePopularityComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Double.compare(o1.getPopularity(), o2.getPopularity());
    }
}
