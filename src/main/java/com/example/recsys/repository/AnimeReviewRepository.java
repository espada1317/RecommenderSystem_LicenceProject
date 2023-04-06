package com.example.recsys.repository;

import com.example.recsys.entity.AnimeReview;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AnimeReviewRepository extends JpaRepository<AnimeReview, Integer> {

    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname AND anime_id = :animeId", nativeQuery = true)
    AnimeReview findReviewByUserAndAnime(String nickname, Integer animeId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE anime_reviews a_r SET a_r.category = ?1, a_r.reviewScore = ?2, a_r.reviewMessage = ?3, a_r.localDateTime = ?4 WHERE a_r.animeReviewKey = ?5")
    void updateReview(String modifiedCategory, Integer modifiedScore, String modifiedReview, LocalDateTime localDateTime, Integer reviewKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM anime_reviews a_r WHERE a_r.animeReviewKey = ?1")
    void deleteReview(Integer reviewKey);

    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname ORDER BY datetime DESC", nativeQuery = true)
    List<AnimeReview> getAnimeActivity(String nickname);

    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname AND ( (review_score <> '' OR review_score <> NULL ) OR review_message <> '') ORDER BY datetime DESC", nativeQuery = true)
    List<AnimeReview> getAllUserAndFriendsAnimeActivity(String nickname);

    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname AND category = :category", nativeQuery = true)
    List<AnimeReview> getReviewsByCategories(String nickname, String category);

    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname AND category = 'completed' ORDER BY datetime DESC", nativeQuery = true)
    List<AnimeReview> getRecentWatchedAnime(String nickname);
    @Query(value = "SELECT * FROM anime_reviews WHERE nickname = :nickname AND category = 'plan_watch' ORDER BY datetime DESC", nativeQuery = true)
    List<AnimeReview> getPlanToWatchAnime(String nickname);

    @Query(value = "SELECT * FROM anime_reviews WHERE review_message <> '' AND nickname = :nickname", nativeQuery = true)
    List<AnimeReview> getReviewsByNickname(String nickname);

}