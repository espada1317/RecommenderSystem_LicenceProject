package com.example.recsys.repository;

import com.example.recsys.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM movie m WHERE CONCAT(m.overview, ' ', m.title) LIKE CONCAT('%', :keyword , '%')")
    List<Movie> findByTitleContainingOrOverviewContaining(@Param("keyword") String keyword);

    @Query("SELECT m FROM movie m WHERE m.genres LIKE CONCAT('%', :genre , '%')")
    List<Movie> findByGenreContaining(@Param("genre") String genre);

    @Query("SELECT m FROM movie m WHERE YEAR(m.release) = :year")
    List<Movie> findByYearContaining(@Param("year") Integer year);

    @Query("SELECT m FROM movie m WHERE m.language = :language")
    List<Movie> findByLanguageContaining(@Param("language") String language);

    @Query(value = "SELECT DISTINCT TRIM(value) FROM movie CROSS APPLY string_split(genres,',')", nativeQuery = true)
    List<String> findAllDistinctGenreList();

    @Query(value = "SELECT DISTINCT YEAR(release) AS r_year FROM movie ORDER BY r_year DESC", nativeQuery = true)
    List<Integer> findAllDistinctYearList();

    @Query(value = "SELECT DISTINCT language AS lang FROM movie ORDER BY lang", nativeQuery = true)
    List<String> findAllDistinctLanguageList();

    Movie findByTitle(String title);
}
