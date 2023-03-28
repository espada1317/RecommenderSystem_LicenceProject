package com.example.recsys.service.implementation;

import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.entity.MovieGenresPreferences;
import com.example.recsys.repository.MoviePreferenceRepository;
import com.example.recsys.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        movieGenresPreferences.setSkipped(0);
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

    @Override
    public void skipMoviePreference(String nickname) {
        MovieGenresPreferences movieGenresPreferences = new MovieGenresPreferences();
        movieGenresPreferences.setNickname(nickname);
        movieGenresPreferences.setSkipped(1);
        movieGenresPreferences.setActionRatio(0.25);
        movieGenresPreferences.setAdventureRatio(0.25);
        movieGenresPreferences.setAnimationRatio(0.25);
        movieGenresPreferences.setComedyRatio(0.25);
        movieGenresPreferences.setCrimeRatio(0.25);
        movieGenresPreferences.setDocumentaryRatio(0.25);
        movieGenresPreferences.setDramaRatio(0.25);
        movieGenresPreferences.setFamilyRatio(0.25);
        movieGenresPreferences.setFantasyRatio(0.25);
        movieGenresPreferences.setHistoryRatio(0.25);
        movieGenresPreferences.setHorrorRatio(0.25);
        movieGenresPreferences.setMusicRatio(0.25);
        movieGenresPreferences.setMysteryRatio(0.25);
        movieGenresPreferences.setRomanceRatio(0.25);
        movieGenresPreferences.setScienceFictionRatio(0.25);
        movieGenresPreferences.setThrillerRatio(0.25);
        movieGenresPreferences.setTvMovieRatio(0.25);
        movieGenresPreferences.setWarRatio(0.25);
        movieGenresPreferences.setWesternRatio(0.25);

        moviePreferenceRepository.save(movieGenresPreferences);
    }

    @Override
    public Optional<MovieGenresPreferences> getPreferenceProfileOfUser(String nickname) {
        return moviePreferenceRepository.findByNickname(nickname);
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
        return 0.0;
    }
}
