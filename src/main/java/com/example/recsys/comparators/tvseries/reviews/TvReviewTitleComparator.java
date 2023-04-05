package com.example.recsys.comparators.tvseries.reviews;

import com.example.recsys.entity.TvSeriesReviews;

import java.util.Comparator;

public class TvReviewTitleComparator implements Comparator<TvSeriesReviews> {
    @Override
    public int compare(TvSeriesReviews o1, TvSeriesReviews o2) {
        return o1.getTvSeries().getTitle().compareTo(o2.getTvSeries().getTitle());
    }
}
