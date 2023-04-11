package com.example.recsys.repository;

import com.example.recsys.entity.MovieGenresPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoviePreferenceRepository extends JpaRepository<MovieGenresPreferences, Integer> {

    Optional<MovieGenresPreferences> findByNickname(String nickname);
}