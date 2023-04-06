package com.example.recsys.comparators.anime;

import com.example.recsys.entity.Anime;

import java.util.Comparator;

public class AnimeTitleComparator implements Comparator<Anime> {
    @Override
    public int compare(Anime o1, Anime o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
