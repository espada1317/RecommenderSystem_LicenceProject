package com.example.recsys.service;

import com.example.recsys.dto.RecentReviewsDto;
import com.example.recsys.dto.UserActivityDto;
import com.example.recsys.entity.MovieReviews;
import com.example.recsys.entity.TvSeriesReviews;

import java.util.List;

public interface ProfileService {

    List<UserActivityDto> getAllUserRecentActivity(List<MovieReviews> movieReviews, List<TvSeriesReviews> tvSeriesReviews);

    List<RecentReviewsDto> getAllRecentReviews(List<MovieReviews> movieReviews, List<TvSeriesReviews> tvSeriesReviews);

}
