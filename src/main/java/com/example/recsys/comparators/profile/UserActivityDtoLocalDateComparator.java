package com.example.recsys.comparators.profile;

import com.example.recsys.dto.UserActivityDto;

import java.util.Comparator;

public class UserActivityDtoLocalDateComparator implements Comparator<UserActivityDto> {
    @Override
    public int compare(UserActivityDto o1, UserActivityDto o2) {
        return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
    }
}
