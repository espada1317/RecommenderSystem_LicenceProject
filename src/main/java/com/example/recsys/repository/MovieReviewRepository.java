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

    @Query(value = "SELECT DISTINCT YEAR(release) AS rel_year FROM movie_reviews INNER JOIN movie ON movie.movie_key = movie_reviews.movie_id WHERE nickname = :nickname ORDER BY rel_year DESC", nativeQuery = true)
    List<Integer> getListOfDistinctReleaseYearsOfPersonalMovies(String nickname);

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname AND category = :category", nativeQuery = true)
    List<MovieReviews> getReviewsByCategories(String nickname, String category);

    @Query(value = "SELECT * FROM movie_reviews INNER JOIN movie ON movie.movie_key = movie_reviews.movie_id WHERE nickname = :nickname AND YEAR(release) = :year", nativeQuery = true)
    List<MovieReviews> getReviewsByMovieYearRelease(String nickname, Integer year);

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname AND category = 'completed' ORDER BY datetime DESC", nativeQuery = true)
    List<MovieReviews> getRecentWatchedMovies(String nickname);

    @Query(value = "SELECT * FROM movie_reviews WHERE nickname = :nickname AND category = 'plan_watch' ORDER BY datetime DESC", nativeQuery = true)
    List<MovieReviews> getPlanToWatchMovies(String nickname);

    @Query(value = "SELECT * FROM movie_reviews WHERE review_message <> '' AND nickname = :nickname", nativeQuery = true)
    List<MovieReviews> getReviewsByNickname(String nickname);
}