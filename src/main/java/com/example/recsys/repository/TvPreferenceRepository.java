package com.example.recsys.repository;

import com.example.recsys.entity.TvGenresPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvPreferenceRepository extends JpaRepository<TvGenresPreferences, Integer> {

    List<TvGenresPreferences> findByNickname(String nickname);

}
