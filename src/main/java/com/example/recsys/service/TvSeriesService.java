package com.example.recsys.service;

import com.example.recsys.dto.TvReviewDto;
import com.example.recsys.entity.TvSeries;
import com.example.recsys.entity.TvSeriesReviews;

import java.util.List;
import java.util.Optional;

public interface TvSeriesService {

    List<TvSeries> getAllTv();

    List<TvSeries> searchTvByMultipleFilter(String keyword, String genre, Integer startYear, Integer endYear, String sortBy);

    TvSeries getTvById(int tvID);

    List<String> getTvGenres();

    List<Integer> getReleaseStartYears();

    List<Integer> getReleaseEndYears();

    void saveReview(Integer tvId, String nickname, TvReviewDto movieReviewsDto);

    void updateReview(String nickname, Integer tvId, TvReviewDto movieReviewDto);

    void deleteReview(String nickname, Integer tvId);

    Optional<TvSeriesReviews> getReviewByNicknameAndTvId(String nickname, Integer movieId);

    List<TvSeriesReviews> getTvActivity(String nickname);

    List<TvSeriesReviews> searchPersonalTvByMultipleFilter(String nickname, String category, String sortBy);

    List<TvSeriesReviews> getAllUserAndFriendsTvActivity(String nickname);

    List<TvSeriesReviews> getRecentWatchedTvs(String nickname);

    List<TvSeriesReviews> getPlanToWatchTvs(String nickname);

    List<TvSeriesReviews> getReviewsByNickname(String nickname);

    List<TvSeriesReviews> getAllActivityByNickname(String nickname);
}