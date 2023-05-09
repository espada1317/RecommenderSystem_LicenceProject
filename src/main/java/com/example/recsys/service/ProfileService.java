package com.example.recsys.service;

import com.example.recsys.dto.*;
import com.example.recsys.entity.*;

import java.util.List;

public interface ProfileService {

    List<UserActivityDto> getAllUserRecentActivity(List<MovieReviews> movieReviews, List<TvSeriesReviews> tvSeriesReviews, List<AnimeReview> animeReviews, List<BookReview> bookReviews);

    List<RecentReviewsDto> getAllRecentReviews(List<MovieReviews> movieReviews, List<TvSeriesReviews> tvSeriesReviews, List<AnimeReview> animeReviews, List<BookReview> bookReviews);

    MovieStatsDto getPersonalMovieStats(List<MovieReviews> movieReviews);

    TvStatsDto getPersonalTvStats(List<TvSeriesReviews> tvSeriesReviews);

    AnimeStatsDto getPersonalAnimeStats(List<AnimeReview> animeReviews);

    BookStatsDto getPersonalBookStats(List<BookReview> bookReviews);

}
