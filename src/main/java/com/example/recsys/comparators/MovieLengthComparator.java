package com.example.recsys.comparators;

import com.example.recsys.entity.Movie;

import java.util.Comparator;

public class MovieLengthComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getLength(), o2.getLength());
    }
}
