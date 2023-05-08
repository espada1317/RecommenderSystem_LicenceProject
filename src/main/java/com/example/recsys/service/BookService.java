package com.example.recsys.service;

import com.example.recsys.dto.BookReviewDto;
import com.example.recsys.entity.BookReview;
import com.example.recsys.entity.Books;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Books> getAllBooks();

    List<Books> searchBooksByMultipleFilter(String keyword, String genre, String author);

    List<String> getBooksGenres();

    Books getBookById(Integer bookId);

    void saveReview(Integer animeId, String nickname, BookReviewDto bookReviewDto);

    void updateReview(String nickname, Integer bookId, BookReviewDto bookReviewDto);

    void deleteReview(String nickname, Integer bookId);

    Optional<BookReview> getReviewByNicknameAndBookId(String nickname, Integer bookId);

    List<BookReview> getBookActivity(String nickname);

    List<BookReview> searchPersonalBooksByMultipleFilter(String nickname, String category, String sortBy);

}