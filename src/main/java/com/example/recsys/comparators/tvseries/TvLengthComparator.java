package com.example.recsys.comparators.tvseries;

import com.example.recsys.entity.TvSeries;

import java.util.Comparator;

public class TvLengthComparator implements Comparator<TvSeries> {
    @Override
    public int compare(TvSeries o1, TvSeries o2) {
        int o1_length = o1.getAverageRuntime() * o1.getNumberEpisodes();
        int o2_length = o2.getAverageRuntime() * o2.getNumberEpisodes();
        return Integer.compare(o1_length, o2_length);
    }
}
