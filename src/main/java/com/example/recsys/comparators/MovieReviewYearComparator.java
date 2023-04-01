package com.example.recsys.comparators;

import com.example.recsys.entity.MovieReviews;

import java.util.Comparator;

public class MovieReviewYearComparator implements Comparator<MovieReviews> {
    @Override
    public int compare(MovieReviews o1, MovieReviews o2) {
        return o1.getMovie().getRelease().compareTo(o2.getMovie().getRelease());
    }
}
