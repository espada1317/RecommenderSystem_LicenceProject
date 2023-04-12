package com.example.recsys.service;

import com.example.recsys.dto.AnimePreferenceProfileDto;
import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.dto.TvPreferenceProfileDto;
import com.example.recsys.entity.AnimeGenresPreferences;
import com.example.recsys.entity.MovieGenresPreferences;
import com.example.recsys.entity.TvGenresPreferences;

import java.util.List;
import java.util.Optional;

public interface PreferencesService {

    void saveMoviePreference(String nickname, MoviePreferenceProfileDto moviePreferenceProfileDto);

    void skipMoviePreference(String nickname);

    List<MovieGenresPreferences> getMoviePreferenceProfileOfUser(String nickname);

    void saveTvPreference(String nickname, TvPreferenceProfileDto tvPreferenceProfileDto);

    void skipTvPreference(String nickname);

    List<TvGenresPreferences> getTvPreferenceProfileOfUser(String nickname);

    void saveAnimePreference(String nickname, AnimePreferenceProfileDto animePreferenceProfileDto);

    void skipAnimePreference(String nickname);

    List<AnimeGenresPreferences> getAnimePreferenceProfileOfUser(String nickname);

    void skipAllPreference(String nickname);

}