package com.example.recsys.comparators.anime;

import com.example.recsys.entity.Anime;

import java.util.Comparator;

public class AnimeLengthComparator implements Comparator<Anime> {
    @Override
    public int compare(Anime o1, Anime o2) {
        int o1_length = o1.getAverageRuntime() * o1.getNumberEpisodes();
        int o2_length = o2.getAverageRuntime() * o2.getNumberEpisodes();
        return Integer.compare(o1_length, o2_length);
    }
}
