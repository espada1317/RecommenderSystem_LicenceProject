package com.example.recsys.comparators.profile;

import com.example.recsys.dto.RecentReviewsDto;

import java.util.Comparator;

public class RecentReviewsDtoLocalDateComparator implements Comparator<RecentReviewsDto> {
    @Override
    public int compare(RecentReviewsDto o1, RecentReviewsDto o2) {
        return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
    }
}
