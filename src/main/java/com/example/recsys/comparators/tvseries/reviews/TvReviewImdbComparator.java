package com.example.recsys.comparators.tvseries.reviews;

import com.example.recsys.entity.TvSeriesReviews;

import java.util.Comparator;

public class TvReviewImdbComparator implements Comparator<TvSeriesReviews> {
    @Override
    public int compare(TvSeriesReviews o1, TvSeriesReviews o2) {
        return Double.compare(o1.getTvSeries().getVoteAverage(), o2.getTvSeries().getVoteAverage());
    }
}
