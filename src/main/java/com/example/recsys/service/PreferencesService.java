package com.example.recsys.service;

import com.example.recsys.dto.MoviePreferenceProfileDto;

public interface PreferencesService {

    void saveMoviePreference(String nickname, MoviePreferenceProfileDto moviePreferenceProfileDto);

}
