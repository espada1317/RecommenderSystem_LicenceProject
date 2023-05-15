package com.example.recsys.repository;

import com.example.recsys.entity.Anime;
import com.example.recsys.entity.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    @Query("SELECT a FROM anime a WHERE a.title LIKE CONCAT('%', :keyword , '%')")
    List<Anime> findByTitleContaining(@Param("keyword") String keyword);

    @Query(value = "SELECT DISTINCT TRIM(value) AS VAL FROM anime CROSS APPLY string_split(genres,',') ORDER BY VAL ASC", nativeQuery = true)
    List<String> findAllDistinctGenreList();

    @Query("SELECT a FROM anime a WHERE a.genres LIKE CONCAT('%', :genre , '%')")
    List<Anime> findByGenreContaining(@Param("genre") String genre);

    @Query("SELECT DISTINCT source AS VAL FROM anime ORDER BY VAL ASC")
    List<String> findAllDistinctSourceList();

    @Query("SELECT a FROM anime a WHERE a.source LIKE CONCAT('%', :source , '%')")
    List<Anime> findBySourceContaining(@Param("source") String source);

    @Query(value = "SELECT DISTINCT YEAR(runtime_start) AS VAL FROM anime ORDER BY VAL ASC", nativeQuery = true)
    List<Integer> getReleaseStartYears();

    @Query(value = "SELECT DISTINCT YEAR(runtime_end) AS VAL FROM anime ORDER BY VAL DESC", nativeQuery = true)
    List<Integer> getReleaseEndYears();

    @Query(value = "SELECT * FROM anime WHERE YEAR(runtime_start) >= :startYear", nativeQuery = true)
    List<Anime> findByMinimStartYearRelease(Integer startYear);

    @Query(value = "SELECT * FROM anime WHERE YEAR(runtime_end) <= :endYear", nativeQuery = true)
    List<Anime> findByMaximEndYearRelease(Integer endYear);

    @Query("SELECT DISTINCT type AS VAL FROM anime ORDER BY VAL ASC")
    List<String> findAllDistinctTypeList();

    @Query("SELECT a FROM anime a WHERE a.type LIKE CONCAT('%', :type , '%')")
    List<Anime> findByTypeContaining(@Param("type") String type);

    @Query("SELECT a FROM anime a WHERE a.title LIKE CONCAT(:title , '%') AND a.animeKey <> :animeKey ORDER BY a.voteTotal DESC")
    List<Anime> findByTitleContaining(@Param("title") String title, @Param("animeKey") Integer animeKey);

}