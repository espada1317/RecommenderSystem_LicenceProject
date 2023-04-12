package com.example.recsys.repository;

import com.example.recsys.entity.MovieGenresPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoviePreferenceRepository extends JpaRepository<MovieGenresPreferences, Integer> {

    List<MovieGenresPreferences> findByNickname(String nickname);
}