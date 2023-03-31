package com.example.recsys.repository;

import com.example.recsys.entity.MovieReviews;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieReviewRepository extends JpaRepository<MovieReviews, Integer> {

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname AND movie_id = :movieId", nativeQuery = true)
    MovieReviews findReviewByUserAndMovie(String nickname, Integer movieId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE movie_reviews m_r SET m_r.category = ?1, m_r.reviewScore = ?2, m_r.reviewMessage = ?3, m_r.localDateTime = ?4 WHERE m_r.movieReviewKey = ?5")
    void updateReview(String modifiedCategory, Integer modifiedScore, String modifiedReview, LocalDateTime localDateTime, Integer reviewKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM movie_reviews m_r WHERE m_r.movieReviewKey = ?1")
    void deleteReview(Integer reviewKey);

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname AND ( (review_score <> '' OR review_score <> NULL ) OR review_message <> '') ORDER BY datetime DESC", nativeQuery = true)
    List<MovieReviews> getAllUserAndFriendActivity(String nickname);

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname ORDER BY datetime DESC", nativeQuery = true)
    List<MovieReviews> getMoviesActivity(String nickname);
}