package com.example.recsys.repository;

import com.example.recsys.entity.AnimeGenresPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimePreferenceRepository extends JpaRepository<AnimeGenresPreferences, Integer> {

    List<AnimeGenresPreferences> findByNickname(String nickname);
}
