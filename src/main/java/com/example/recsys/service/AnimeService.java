package com.example.recsys.service;

import com.example.recsys.dto.AnimeReviewDto;
import com.example.recsys.entity.Anime;
import com.example.recsys.entity.AnimeReview;
import com.example.recsys.entity.TvSeries;

import java.util.List;
import java.util.Optional;

public interface AnimeService {

    List<Anime> getAllAnime();

    List<Anime> searchAnimeByMultipleFilter(String keyword, String genre, String type, String source, Integer startYear, Integer endYear, String sortBy);

    List<String> getAnimeGenres();

    List<String> getAnimeTypes();

    List<String> getAnimeSources();

    List<Integer> getReleaseStartYears();

    List<Integer> getReleaseEndYears();

    Anime getAnimeById(Integer animeId);

    void saveReview(Integer animeId, String nickname, AnimeReviewDto animeReviewDto);

    void updateReview(String nickname, Integer animeId, AnimeReviewDto animeReviewDto);

    void deleteReview(String nickname, Integer animeId);

    Optional<AnimeReview> getReviewByNicknameAndAnimeId(String nickname, Integer movieId);

    List<AnimeReview> getAnimeActivity(String nickname);

    List<AnimeReview> getAllUserAndFriendsAnimeActivity(String nickname);

    List<AnimeReview> searchPersonalAnimeByMultipleFilter(String nickname, String category, String sortBy);

    List<AnimeReview> getRecentWatchedAnime(String nickname);

    List<AnimeReview> getPlanToWatchAnime(String nickname);

    List<AnimeReview> getReviewsByNickname(String nickname);

    List<AnimeReview> getAllActivityByNickname(String nickname);


    List<Anime> getSimilarContent(Anime anime);

    List<Anime> findByTitleContaining(String title, Integer animeKey);

}