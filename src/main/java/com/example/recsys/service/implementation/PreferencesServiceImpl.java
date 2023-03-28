package com.example.recsys.service.implementation;

import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.entity.MovieGenresPreferences;
import com.example.recsys.repository.MoviePreferenceRepository;
import com.example.recsys.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PreferencesServiceImpl implements PreferencesService {

    @Autowired
    private final MoviePreferenceRepository moviePreferenceRepository;

    public PreferencesServiceImpl(MoviePreferenceRepository moviePreferenceRepository) {
        this.moviePreferenceRepository = moviePreferenceRepository;
    }

    @Override
    public void saveMoviePreference(String nickname, MoviePreferenceProfileDto moviePreferenceProfileDto) {
        MovieGenresPreferences movieGenresPreferences = new MovieGenresPreferences();
        movieGenresPreferences.setNickname(nickname);
        movieGenresPreferences.setActionRatio(getMovieGenreRation(moviePreferenceProfileDto.getAction()));
        movieGenresPreferences.setAdventureRatio(getMovieGenreRation(moviePreferenceProfileDto.getAdventure()));
        movieGenresPreferences.setAnimationRatio(getMovieGenreRation(moviePreferenceProfileDto.getAnimation()));
        movieGenresPreferences.setComedyRatio(getMovieGenreRation(moviePreferenceProfileDto.getComedy()));
        movieGenresPreferences.setCrimeRatio(getMovieGenreRation(moviePreferenceProfileDto.getCrime()));
        movieGenresPreferences.setDocumentaryRatio(getMovieGenreRation(moviePreferenceProfileDto.getDocumentary()));
        movieGenresPreferences.setDramaRatio(getMovieGenreRation(moviePreferenceProfileDto.getDrama()));
        movieGenresPreferences.setFamilyRatio(getMovieGenreRation(moviePreferenceProfileDto.getFamily()));
        movieGenresPreferences.setFantasyRatio(getMovieGenreRation(moviePreferenceProfileDto.getFantasy()));
        movieGenresPreferences.setHistoryRatio(getMovieGenreRation(moviePreferenceProfileDto.getHistory()));
        movieGenresPreferences.setHorrorRatio(getMovieGenreRation(moviePreferenceProfileDto.getHorror()));
        movieGenresPreferences.setMusicRatio(getMovieGenreRation(moviePreferenceProfileDto.getMusic()));
        movieGenresPreferences.setMysteryRatio(getMovieGenreRation(moviePreferenceProfileDto.getMystery()));
        movieGenresPreferences.setRomanceRatio(getMovieGenreRation(moviePreferenceProfileDto.getRomance()));
        movieGenresPreferences.setScienceFictionRatio(getMovieGenreRation(moviePreferenceProfileDto.getScience_fiction()));
        movieGenresPreferences.setThrillerRatio(getMovieGenreRation(moviePreferenceProfileDto.getThriller()));
        movieGenresPreferences.setTvMovieRatio(getMovieGenreRation(moviePreferenceProfileDto.getTvMovie()));
        movieGenresPreferences.setWarRatio(getMovieGenreRation(moviePreferenceProfileDto.getWar()));
        movieGenresPreferences.setWesternRatio(getMovieGenreRation(moviePreferenceProfileDto.getWestern()));

        moviePreferenceRepository.save(movieGenresPreferences);
    }

    public Double getMovieGenreRation(String genre) {
        if(genre == null) {
            return 0.25;
        }
        if(genre.equals("often")) {
            return 0.75;
        }
        if(genre.equals("sometimes")) {
            return 0.5;
        }
        if(genre.equals("never")) {
            return 0.0;
        }

        return 0.25;
    }
}
