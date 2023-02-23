package com.example.recsys.repository;

import com.example.recsys.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
//    List<Movie> findFirst10ByOrderByTitleName();

    @Query("SELECT m FROM movie m WHERE CONCAT(m.overview, ' ', m.title) LIKE CONCAT('%', :keyword , '%')")
    List<Movie> findByTitleContainingOrOverviewContaining(@Param("keyword") String keyword);

    Movie findByTitle(String title);
}
