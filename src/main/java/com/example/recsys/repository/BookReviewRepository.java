package com.example.recsys.repository;

import com.example.recsys.entity.AnimeReview;
import com.example.recsys.entity.BookReview;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname AND book_id = :bookId", nativeQuery = true)
    BookReview findReviewByUserAndBook(String nickname, Integer bookId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE book_reviews b_r SET b_r.category = ?1, b_r.reviewScore = ?2, b_r.reviewMessage = ?3, b_r.localDateTime = ?4 WHERE b_r.bookReviewKey = ?5")
    void updateReview(String modifiedCategory, Integer modifiedScore, String modifiedReview, LocalDateTime localDateTime, Integer reviewKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM book_reviews b_r WHERE b_r.bookReviewKey = ?1")
    void deleteReview(Integer reviewKey);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname ORDER BY datetime DESC", nativeQuery = true)
    List<BookReview> getBookActivity(String nickname);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname AND category = :category", nativeQuery = true)
    List<BookReview> getReviewsByCategories(String nickname, String category);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname AND ( (review_score <> '' OR review_score <> NULL ) OR review_message <> '') ORDER BY datetime DESC", nativeQuery = true)
    List<BookReview> getAllUserAndFriendsBooksActivity(String nickname);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname AND category = 'completed' ORDER BY datetime DESC", nativeQuery = true)
    List<BookReview> getRecentCompletedBook(String nickname);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname AND category = 'plan_read' ORDER BY datetime DESC", nativeQuery = true)
    List<BookReview> getPlanToReadBook(String nickname);

    @Query(value = "SELECT * FROM book_reviews WHERE review_message <> '' AND nickname = :nickname", nativeQuery = true)
    List<BookReview> getReviewsByNickname(String nickname);

    @Query(value = "SELECT * FROM book_reviews WHERE nickname = :nickname", nativeQuery = true)
    List<BookReview> getAllActivityByNickname(String nickname);
}