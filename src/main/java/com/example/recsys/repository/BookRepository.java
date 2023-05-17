package com.example.recsys.repository;

import com.example.recsys.entity.Anime;
import com.example.recsys.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
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

    @Query(value = "SELECT genre FROM\n" +
            "(\n" +
            "SELECT TOP 3 TRIM(value) as genre, COUNT(*) as number FROM\n" +
            "(\n" +
            "SELECT m.* FROM\n" +
            "book_reviews m_r INNER JOIN books m ON m_r.book_id = m.book_key\n" +
            "WHERE nickname = :nickname AND (category <> 'dropped')\n" +
            ") val CROSS APPLY string_split(genres,',')\n" +
            "GROUP BY value\n" +
            "ORDER BY number DESC\n" +
            ") wtf", nativeQuery = true)
    List<String> getMostPopularUserGenres(@Param("nickname") String nickname);

    @Query(value = "SELECT m.* FROM\n" +
            "book_reviews m_r INNER JOIN books m ON m_r.book_id = m.book_key\n" +
            "WHERE nickname = :nickname", nativeQuery = true)
    List<Books> getAllMarkedBooks(@Param("nickname") String nickname);

}