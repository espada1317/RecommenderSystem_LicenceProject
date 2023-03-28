package com.example.recsys.repository;

import com.example.recsys.entity.MovieGenresPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePreferenceRepository extends JpaRepository<MovieGenresPreferences, Integer> {
}
