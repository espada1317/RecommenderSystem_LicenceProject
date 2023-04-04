package com.example.recsys.comparators.tvseries;

import com.example.recsys.entity.TvSeries;

import java.util.Comparator;

public class TvImdbComparator implements Comparator<TvSeries> {
    @Override
    public int compare(TvSeries o1, TvSeries o2) {
        return Double.compare(o1.getVoteAverage(), o2.getVoteAverage());
    }
}
