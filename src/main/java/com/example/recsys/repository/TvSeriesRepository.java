package com.example.recsys.repository;

import com.example.recsys.entity.Movie;
import com.example.recsys.entity.TvSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface TvSeriesRepository extends JpaRepository<TvSeries, Integer> {

    @Query("SELECT tv FROM tv_series tv WHERE CONCAT(tv.overview, ' ', tv.title) LIKE CONCAT('%', :keyword , '%')")
    List<TvSeries> findByTitleContainingOrOverviewContaining(@Param("keyword") String keyword);

    @Query(value = "SELECT DISTINCT TRIM(value) AS VAL FROM tv_series CROSS APPLY string_split(genres,',') ORDER BY VAL ASC", nativeQuery = true)
    List<String> findAllDistinctGenreList();

    @Query("SELECT tv FROM tv_series tv WHERE tv.genres LIKE CONCAT('%', :genre , '%')")
    List<TvSeries> findByGenreContaining(@Param("genre") String genre);

    @Query(value = "SELECT DISTINCT runtime_start FROM tv_series ORDER BY runtime_start ASC", nativeQuery = true)
    List<Integer> getReleaseStartYears();

    @Query(value = "SELECT DISTINCT runtime_end FROM tv_series ORDER BY runtime_end DESC", nativeQuery = true)
    List<Integer> getReleaseEndYears();

    @Query(value = "SELECT * FROM tv_series WHERE runtime_start >= :startYear", nativeQuery = true)
    List<TvSeries> findByMinimStartYearRelease(Integer startYear);

    @Query(value = "SELECT * FROM tv_series WHERE runtime_end <= :endYear", nativeQuery = true)
    List<TvSeries> findByMaximEndYearRelease(Integer endYear);

    @Query("SELECT tv FROM tv_series tv WHERE tv.title LIKE CONCAT(:title , '%') AND tv.tvKey <> :tvKey ORDER BY tv.voteAverage DESC")
    List<TvSeries> findByTitleContaining(@Param("title") String title, @Param("tvKey") Integer tvKey);

    @Query(value = "SELECT genre FROM\n" +
            "(\n" +
            "SELECT TOP 3 TRIM(value) as genre, COUNT(*) as number FROM\n" +
            "(\n" +
            "SELECT genres FROM\n" +
            "tv_reviews m_r INNER JOIN tv_series m ON m_r.tv_id = m.tv_key\n" +
            "WHERE nickname = 'espada1317' AND ( category = 'completed' OR category = 'plan_watch' )\n" +
            ") val CROSS APPLY string_split(genres,',')\n" +
            "GROUP BY value\n" +
            "ORDER BY number DESC\n" +
            ") wtf", nativeQuery = true)
    List<String> getMostPopularUserGenres(@Param("nickname") String nickname);

    @Query(value = "SELECT m.* FROM\n" +
            "tv_reviews m_r INNER JOIN tv_series m ON m_r.tv_id = m.tv_key\n" +
            "WHERE nickname = :nickname", nativeQuery = true)
    List<TvSeries> getAllMarkedTvs(@Param("nickname") String nickname);

}
