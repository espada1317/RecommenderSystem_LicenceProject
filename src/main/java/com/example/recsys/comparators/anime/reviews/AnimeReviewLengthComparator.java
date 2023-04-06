package com.example.recsys.comparators.anime.reviews;

import com.example.recsys.entity.AnimeReview;

import java.util.Comparator;

public class AnimeReviewLengthComparator implements Comparator<AnimeReview> {
    @Override
    public int compare(AnimeReview o1, AnimeReview o2) {
        int o1_length = o1.getAnime().getAverageRuntime() * o1.getAnime().getNumberEpisodes();
        int o2_length = o2.getAnime().getAverageRuntime() * o2.getAnime().getNumberEpisodes();
        return Integer.compare(o1_length, o2_length);
    }
}
