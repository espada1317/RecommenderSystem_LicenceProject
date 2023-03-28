package com.example.recsys.service;

import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.entity.MovieGenresPreferences;

import java.util.Optional;

public interface PreferencesService {

    void saveMoviePreference(String nickname, MoviePreferenceProfileDto moviePreferenceProfileDto);

    void skipMoviePreference(String nickname);

    Optional<MovieGenresPreferences> getPreferenceProfileOfUser(String nickname);

}