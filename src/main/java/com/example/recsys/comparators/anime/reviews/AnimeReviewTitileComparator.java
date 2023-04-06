package com.example.recsys.comparators.anime.reviews;

import com.example.recsys.entity.AnimeReview;

import java.util.Comparator;

public class AnimeReviewTitileComparator implements Comparator<AnimeReview> {
    @Override
    public int compare(AnimeReview o1, AnimeReview o2) {
        return o1.getAnime().getTitle().compareTo(o2.getAnime().getTitle());
    }
}
