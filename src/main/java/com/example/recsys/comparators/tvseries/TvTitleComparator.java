package com.example.recsys.comparators.tvseries;

import com.example.recsys.entity.TvSeries;

import java.util.Comparator;

public class TvTitleComparator implements Comparator<TvSeries> {
    @Override
    public int compare(TvSeries o1, TvSeries o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
