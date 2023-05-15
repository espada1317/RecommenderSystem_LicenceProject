package com.example.recsys.repository;

import com.example.recsys.entity.Anime;
import com.example.recsys.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Books, Integer> {

    @Query("SELECT b FROM books b WHERE CONCAT(b.title + ' ' + b.authors) LIKE CONCAT('%', :keyword , '%')")
    List<Books> findByTitleContaining(String keyword);

    @Query(value = "SELECT DISTINCT TRIM(value) AS VAL FROM books CROSS APPLY string_split(genres,',') ORDER BY VAL ASC", nativeQuery = true)
    List<String> findAllDistinctGenreList();

    @Query("SELECT b FROM books b WHERE b.genres LIKE CONCAT('%', :genre , '%')")
    List<Books> findByGenreContaining(@Param("genre") String genre);

    @Query("SELECT b FROM books b WHERE b.title LIKE CONCAT(:title , '%') AND b.bookKey <> :bookKey ORDER BY b.rating DESC")
    List<Books> findByTitleContaining(@Param("title") String title, @Param("bookKey") Integer bookKey);

}