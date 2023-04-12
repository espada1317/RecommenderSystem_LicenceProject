package com.example.recsys.service.implementation;

import com.example.recsys.dto.AnimePreferenceProfileDto;
import com.example.recsys.dto.MoviePreferenceProfileDto;
import com.example.recsys.dto.TvPreferenceProfileDto;
import com.example.recsys.entity.AnimeGenresPreferences;
import com.example.recsys.entity.MovieGenresPreferences;
import com.example.recsys.entity.TvGenresPreferences;
import com.example.recsys.repository.AnimePreferenceRepository;
import com.example.recsys.repository.MoviePreferenceRepository;
import com.example.recsys.repository.TvPreferenceRepository;
import com.example.recsys.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PreferencesServiceImpl implements PreferencesService {

    @Autowired
    private final MoviePreferenceRepository moviePreferenceRepository;

    @Autowired
    private final TvPreferenceRepository tvPreferenceRepository;

    @Autowired
    private final AnimePreferenceRepository animePreferenceRepository;

    public PreferencesServiceImpl(MoviePreferenceRepository moviePreferenceRepository, TvPreferenceRepository tvPreferenceRepository, AnimePreferenceRepository animePreferenceRepository) {
        this.moviePreferenceRepository = moviePreferenceRepository;
        this.tvPreferenceRepository = tvPreferenceRepository;
        this.animePreferenceRepository = animePreferenceRepository;
    }

    @Override
    public void saveMoviePreference(String nickname, MoviePreferenceProfileDto moviePreferenceProfileDto) {
        MovieGenresPreferences movieGenresPreferences = new MovieGenresPreferences();
        movieGenresPreferences.setNickname(nickname);
        movieGenresPreferences.setSkipped(0);
        movieGenresPreferences.setActionRatio(getGenreRation(moviePreferenceProfileDto.getAction()));
        movieGenresPreferences.setAdventureRatio(getGenreRation(moviePreferenceProfileDto.getAdventure()));
        movieGenresPreferences.setAnimationRatio(getGenreRation(moviePreferenceProfileDto.getAnimation()));
        movieGenresPreferences.setComedyRatio(getGenreRation(moviePreferenceProfileDto.getComedy()));
        movieGenresPreferences.setCrimeRatio(getGenreRation(moviePreferenceProfileDto.getCrime()));
        movieGenresPreferences.setDocumentaryRatio(getGenreRation(moviePreferenceProfileDto.getDocumentary()));
        movieGenresPreferences.setDramaRatio(getGenreRation(moviePreferenceProfileDto.getDrama()));
        movieGenresPreferences.setFamilyRatio(getGenreRation(moviePreferenceProfileDto.getFamily()));
        movieGenresPreferences.setFantasyRatio(getGenreRation(moviePreferenceProfileDto.getFantasy()));
        movieGenresPreferences.setHistoryRatio(getGenreRation(moviePreferenceProfileDto.getHistory()));
        movieGenresPreferences.setHorrorRatio(getGenreRation(moviePreferenceProfileDto.getHorror()));
        movieGenresPreferences.setMusicRatio(getGenreRation(moviePreferenceProfileDto.getMusic()));
        movieGenresPreferences.setMysteryRatio(getGenreRation(moviePreferenceProfileDto.getMystery()));
        movieGenresPreferences.setRomanceRatio(getGenreRation(moviePreferenceProfileDto.getRomance()));
        movieGenresPreferences.setScienceFictionRatio(getGenreRation(moviePreferenceProfileDto.getScience_fiction()));
        movieGenresPreferences.setThrillerRatio(getGenreRation(moviePreferenceProfileDto.getThriller()));
        movieGenresPreferences.setTvMovieRatio(getGenreRation(moviePreferenceProfileDto.getTvMovie()));
        movieGenresPreferences.setWarRatio(getGenreRation(moviePreferenceProfileDto.getWar()));
        movieGenresPreferences.setWesternRatio(getGenreRation(moviePreferenceProfileDto.getWestern()));

        if(getMoviePreferenceProfileOfUser(nickname).isEmpty()) {
            moviePreferenceRepository.save(movieGenresPreferences);
        }
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

        if(getMoviePreferenceProfileOfUser(nickname).isEmpty()) {
            moviePreferenceRepository.save(movieGenresPreferences);
        }
    }

    @Override
    public List<MovieGenresPreferences> getMoviePreferenceProfileOfUser(String nickname) {
        return moviePreferenceRepository.findByNickname(nickname);
    }

    @Override
    public void saveTvPreference(String nickname, TvPreferenceProfileDto tvPreferenceProfileDto) {
        TvGenresPreferences tvGenresPreferences = new TvGenresPreferences();
        tvGenresPreferences.setNickname(nickname);
        tvGenresPreferences.setSkipped(0);
        tvGenresPreferences.setActionRatio(getGenreRation(tvPreferenceProfileDto.getAction()));
        tvGenresPreferences.setAdventureRatio(getGenreRation(tvPreferenceProfileDto.getAdventure()));
        tvGenresPreferences.setAnimationRatio(getGenreRation(tvPreferenceProfileDto.getAnimation()));
        tvGenresPreferences.setBiographyRatio(getGenreRation(tvPreferenceProfileDto.getBiography()));
        tvGenresPreferences.setComedyRatio(getGenreRation(tvPreferenceProfileDto.getComedy()));
        tvGenresPreferences.setCrimeRatio(getGenreRation(tvPreferenceProfileDto.getCrime()));
        tvGenresPreferences.setDocumentaryRatio(getGenreRation(tvPreferenceProfileDto.getDocumentary()));
        tvGenresPreferences.setDramaRatio(getGenreRation(tvPreferenceProfileDto.getDrama()));
        tvGenresPreferences.setFamilyRatio(getGenreRation(tvPreferenceProfileDto.getFamily()));
        tvGenresPreferences.setFantasyRatio(getGenreRation(tvPreferenceProfileDto.getFantasy()));
        tvGenresPreferences.setGameShowRatio(getGenreRation(tvPreferenceProfileDto.getGame_show()));
        tvGenresPreferences.setHistoryRatio(getGenreRation(tvPreferenceProfileDto.getHistory()));
        tvGenresPreferences.setHorrorRatio(getGenreRation(tvPreferenceProfileDto.getHorror()));
        tvGenresPreferences.setMusicRatio(getGenreRation(tvPreferenceProfileDto.getMusic()));
        tvGenresPreferences.setMusicalRatio(getGenreRation(tvPreferenceProfileDto.getMusical()));
        tvGenresPreferences.setMysteryRatio(getGenreRation(tvPreferenceProfileDto.getMystery()));
        tvGenresPreferences.setNewsRatio(getGenreRation(tvPreferenceProfileDto.getNews()));
        tvGenresPreferences.setRealityTvRatio(getGenreRation(tvPreferenceProfileDto.getReality_tv()));
        tvGenresPreferences.setRomanceRatio(getGenreRation(tvPreferenceProfileDto.getRomance()));
        tvGenresPreferences.setScienceFictionRatio(getGenreRation(tvPreferenceProfileDto.getSci_fi()));
        tvGenresPreferences.setSportRatio(getGenreRation(tvPreferenceProfileDto.getSport()));
        tvGenresPreferences.setTalkShowRatio(getGenreRation(tvPreferenceProfileDto.getTalk_show()));
        tvGenresPreferences.setThrillerRatio(getGenreRation(tvPreferenceProfileDto.getThriller()));
        tvGenresPreferences.setWarRatio(getGenreRation(tvPreferenceProfileDto.getWar()));
        tvGenresPreferences.setWesternRatio(getGenreRation(tvPreferenceProfileDto.getWestern()));

        if(getTvPreferenceProfileOfUser(nickname).isEmpty()) {
            tvPreferenceRepository.save(tvGenresPreferences);
        }
    }

    @Override
    public void skipTvPreference(String nickname) {
        TvGenresPreferences tvGenresPreferences = new TvGenresPreferences();
        tvGenresPreferences.setNickname(nickname);
        tvGenresPreferences.setSkipped(1);
        tvGenresPreferences.setActionRatio(0.25);
        tvGenresPreferences.setAdventureRatio(0.25);
        tvGenresPreferences.setAnimationRatio(0.25);
        tvGenresPreferences.setBiographyRatio(0.25);
        tvGenresPreferences.setComedyRatio(0.25);
        tvGenresPreferences.setCrimeRatio(0.25);
        tvGenresPreferences.setDocumentaryRatio(0.25);
        tvGenresPreferences.setDramaRatio(0.25);
        tvGenresPreferences.setFamilyRatio(0.25);
        tvGenresPreferences.setFantasyRatio(0.25);
        tvGenresPreferences.setGameShowRatio(0.25);
        tvGenresPreferences.setHistoryRatio(0.25);
        tvGenresPreferences.setHorrorRatio(0.25);
        tvGenresPreferences.setMusicRatio(0.25);
        tvGenresPreferences.setMusicalRatio(0.25);
        tvGenresPreferences.setMysteryRatio(0.25);
        tvGenresPreferences.setNewsRatio(0.25);
        tvGenresPreferences.setRealityTvRatio(0.25);
        tvGenresPreferences.setRomanceRatio(0.25);
        tvGenresPreferences.setScienceFictionRatio(0.25);
        tvGenresPreferences.setSportRatio(0.25);
        tvGenresPreferences.setTalkShowRatio(0.25);
        tvGenresPreferences.setThrillerRatio(0.25);
        tvGenresPreferences.setWarRatio(0.25);
        tvGenresPreferences.setWesternRatio(0.25);

        if(getTvPreferenceProfileOfUser(nickname).isEmpty()) {
            tvPreferenceRepository.save(tvGenresPreferences);
        }
    }

    @Override
    public List<TvGenresPreferences> getTvPreferenceProfileOfUser(String nickname) {
        return tvPreferenceRepository.findByNickname(nickname);
    }

    @Override
    public void saveAnimePreference(String nickname, AnimePreferenceProfileDto animePreferenceProfileDto) {
        AnimeGenresPreferences animeGenresPreferences = new AnimeGenresPreferences();
        animeGenresPreferences.setNickname(nickname);
        animeGenresPreferences.setSkipped(0);
        animeGenresPreferences.setActionRatio(getGenreRation(animePreferenceProfileDto.getAction()));
        animeGenresPreferences.setAdventureRatio(getGenreRation(animePreferenceProfileDto.getAdventure()));
        animeGenresPreferences.setAvantGardeRatio(getGenreRation(animePreferenceProfileDto.getAvant_garde()));
        animeGenresPreferences.setCarsRatio(getGenreRation(animePreferenceProfileDto.getCars()));
        animeGenresPreferences.setComedyRatio(getGenreRation(animePreferenceProfileDto.getComedy()));
        animeGenresPreferences.setCrimeRatio(getGenreRation(animePreferenceProfileDto.getCrime()));
        animeGenresPreferences.setDemonsRatio(getGenreRation(animePreferenceProfileDto.getDemons()));
        animeGenresPreferences.setDramaRatio(getGenreRation(animePreferenceProfileDto.getDrama()));
        animeGenresPreferences.setFantasyRatio(getGenreRation(animePreferenceProfileDto.getFantasy()));
        animeGenresPreferences.setGameRatio(getGenreRation(animePreferenceProfileDto.getGame()));
        animeGenresPreferences.setGourmetRatio(getGenreRation(animePreferenceProfileDto.getGourmet()));
        animeGenresPreferences.setHistoricalRatio(getGenreRation(animePreferenceProfileDto.getHistorical()));
        animeGenresPreferences.setHorrorRatio(getGenreRation(animePreferenceProfileDto.getHorror()));
        animeGenresPreferences.setJoseiRatio(getGenreRation(animePreferenceProfileDto.getJosei()));
        animeGenresPreferences.setKidsRatio(getGenreRation(animePreferenceProfileDto.getKids()));
        animeGenresPreferences.setMartialArtsRatio(getGenreRation(animePreferenceProfileDto.getMartial_arts()));
        animeGenresPreferences.setMechaRatio(getGenreRation(animePreferenceProfileDto.getMecha()));
        animeGenresPreferences.setMilitaryRatio(getGenreRation(animePreferenceProfileDto.getMilitary()));
        animeGenresPreferences.setMusicRatio(getGenreRation(animePreferenceProfileDto.getMusic()));
        animeGenresPreferences.setMysteryRatio(getGenreRation(animePreferenceProfileDto.getMystery()));
        animeGenresPreferences.setParodyRatio(getGenreRation(animePreferenceProfileDto.getParody()));
        animeGenresPreferences.setPoliceRatio(getGenreRation(animePreferenceProfileDto.getPolice()));
        animeGenresPreferences.setPsychologicalRatio(getGenreRation(animePreferenceProfileDto.getPsychological()));
        animeGenresPreferences.setRomanceRatio(getGenreRation(animePreferenceProfileDto.getRomance()));
        animeGenresPreferences.setSamuraiRatio(getGenreRation(animePreferenceProfileDto.getSamurai()));
        animeGenresPreferences.setSchoolRatio(getGenreRation(animePreferenceProfileDto.getSchool()));
        animeGenresPreferences.setScienceFictionRatio(getGenreRation(animePreferenceProfileDto.getSci_fi()));
        animeGenresPreferences.setSeinenRatio(getGenreRation(animePreferenceProfileDto.getSeinen()));
        animeGenresPreferences.setShoujoRatio(getGenreRation(animePreferenceProfileDto.getShoujo()));
        animeGenresPreferences.setShounenRatio(getGenreRation(animePreferenceProfileDto.getShounen()));
        animeGenresPreferences.setSliceOfLifeRatio(getGenreRation(animePreferenceProfileDto.getSlice_of_life()));
        animeGenresPreferences.setSpaceRatio(getGenreRation(animePreferenceProfileDto.getSpace()));
        animeGenresPreferences.setSportsRatio(getGenreRation(animePreferenceProfileDto.getSports()));
        animeGenresPreferences.setSuperPowerRatio(getGenreRation(animePreferenceProfileDto.getSuper_power()));
        animeGenresPreferences.setSupernaturalRatio(getGenreRation(animePreferenceProfileDto.getSupernatural()));
        animeGenresPreferences.setSuspenseRatio(getGenreRation(animePreferenceProfileDto.getSuspense()));
        animeGenresPreferences.setVampireRatio(getGenreRation(animePreferenceProfileDto.getVampire()));
        animeGenresPreferences.setWorkLifeRatio(getGenreRation(animePreferenceProfileDto.getWork_life()));

        if(getAnimePreferenceProfileOfUser(nickname).isEmpty()) {
            animePreferenceRepository.save(animeGenresPreferences);
        }
    }

    @Override
    public void skipAnimePreference(String nickname) {
        AnimeGenresPreferences animeGenresPreferences = new AnimeGenresPreferences();
        animeGenresPreferences.setNickname(nickname);
        animeGenresPreferences.setSkipped(0);
        animeGenresPreferences.setActionRatio(0.25);
        animeGenresPreferences.setAdventureRatio(0.25);
        animeGenresPreferences.setAvantGardeRatio(0.25);
        animeGenresPreferences.setCarsRatio(0.25);
        animeGenresPreferences.setComedyRatio(0.25);
        animeGenresPreferences.setCrimeRatio(0.25);
        animeGenresPreferences.setDemonsRatio(0.25);
        animeGenresPreferences.setDramaRatio(0.25);
        animeGenresPreferences.setFantasyRatio(0.25);
        animeGenresPreferences.setGameRatio(0.25);
        animeGenresPreferences.setGourmetRatio(0.25);
        animeGenresPreferences.setHistoricalRatio(0.25);
        animeGenresPreferences.setHorrorRatio(0.25);
        animeGenresPreferences.setJoseiRatio(0.25);
        animeGenresPreferences.setKidsRatio(0.25);
        animeGenresPreferences.setMartialArtsRatio(0.25);
        animeGenresPreferences.setMechaRatio(0.25);
        animeGenresPreferences.setMilitaryRatio(0.25);
        animeGenresPreferences.setMusicRatio(0.25);
        animeGenresPreferences.setMysteryRatio(0.25);
        animeGenresPreferences.setParodyRatio(0.25);
        animeGenresPreferences.setPoliceRatio(0.25);
        animeGenresPreferences.setPsychologicalRatio(0.25);
        animeGenresPreferences.setRomanceRatio(0.25);
        animeGenresPreferences.setSamuraiRatio(0.25);
        animeGenresPreferences.setSchoolRatio(0.25);
        animeGenresPreferences.setScienceFictionRatio(0.25);
        animeGenresPreferences.setSeinenRatio(0.25);
        animeGenresPreferences.setShoujoRatio(0.25);
        animeGenresPreferences.setShounenRatio(0.25);
        animeGenresPreferences.setSliceOfLifeRatio(0.25);
        animeGenresPreferences.setSpaceRatio(0.25);
        animeGenresPreferences.setSportsRatio(0.25);
        animeGenresPreferences.setSuperPowerRatio(0.25);
        animeGenresPreferences.setSupernaturalRatio(0.25);
        animeGenresPreferences.setSuspenseRatio(0.25);
        animeGenresPreferences.setVampireRatio(0.25);
        animeGenresPreferences.setWorkLifeRatio(0.25);

        if(getAnimePreferenceProfileOfUser(nickname).isEmpty()) {
            animePreferenceRepository.save(animeGenresPreferences);
        }
    }

    @Override
    public List<AnimeGenresPreferences> getAnimePreferenceProfileOfUser(String nickname) {
        return animePreferenceRepository.findByNickname(nickname);
    }

    @Override
    public void skipAllPreference(String nickname) {
        skipMoviePreference(nickname);
        skipTvPreference(nickname);
        skipAnimePreference(nickname);
    }

    public Double getGenreRation(String genre) {
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