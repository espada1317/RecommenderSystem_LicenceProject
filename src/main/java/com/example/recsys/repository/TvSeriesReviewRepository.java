package com.example.recsys.repository;

import com.example.recsys.entity.TvSeriesReviews;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TvSeriesReviewRepository extends JpaRepository<TvSeriesReviews, Integer> {

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname AND tv_id = :tvId", nativeQuery = true)
    TvSeriesReviews findReviewByUserAndMovie(String nickname, Integer tvId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tv_reviews tv_r SET tv_r.category = ?1, tv_r.reviewScore = ?2, tv_r.reviewMessage = ?3, tv_r.localDateTime = ?4 WHERE tv_r.tvReviewKey = ?5")
    void updateReview(String modifiedCategory, Integer modifiedScore, String modifiedReview, LocalDateTime localDateTime, Integer reviewKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM tv_reviews tv_r WHERE tv_r.tvReviewKey = ?1")
    void deleteReview(Integer reviewKey);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname ORDER BY datetime DESC", nativeQuery = true)
    List<TvSeriesReviews> getTvActivity(String nickname);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname AND category = :category", nativeQuery = true)
    List<TvSeriesReviews> getReviewsByCategories(String nickname, String category);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname AND ( (review_score <> '' OR review_score <> NULL ) OR review_message <> '') ORDER BY datetime DESC", nativeQuery = true)
    List<TvSeriesReviews> getAllUserAndFriendsTvActivity(String nickname);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname AND category = 'completed' ORDER BY datetime DESC", nativeQuery = true)
    List<TvSeriesReviews> getRecentWatchedTvs(String nickname);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname AND category = 'plan_watch' ORDER BY datetime DESC", nativeQuery = true)
    List<TvSeriesReviews> getPlanToWatchTvs(String nickname);

    @Query(value = "SELECT * FROM tv_reviews WHERE review_message <> '' AND nickname = :nickname", nativeQuery = true)
    List<TvSeriesReviews> getReviewsByNickname(String nickname);

    @Query(value = "SELECT * FROM tv_reviews WHERE nickname = :nickname", nativeQuery = true)
    List<TvSeriesReviews> getAllActivityByNickname(String nickname);
}