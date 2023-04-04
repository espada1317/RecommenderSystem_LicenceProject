package com.example.recsys.comparators.movies.reviews;

import com.example.recsys.entity.MovieReviews;

import java.util.Comparator;

public class MovieReviewImdbRatingComparator implements Comparator<MovieReviews> {

    @Override
    public int compare(MovieReviews o1, MovieReviews o2) {
        return Double.compare(o1.getMovie().getVoteaverage(), o2.getMovie().getVoteaverage());
    }

}
