package com.example.recsys.service.implementation;

import com.example.recsys.comparators.books.BooksScoreComparator;
import com.example.recsys.comparators.books.BooksScoreCountComparator;
import com.example.recsys.comparators.books.BooksTitleComparator;
import com.example.recsys.comparators.books.reviews.BookReviewRatingComparator;
import com.example.recsys.comparators.books.reviews.BookReviewTitleComparator;
import com.example.recsys.dto.BookReviewDto;
import com.example.recsys.entity.Anime;
import com.example.recsys.entity.BookReview;
import com.example.recsys.entity.Books;
import com.example.recsys.entity.TvSeries;
import com.example.recsys.repository.BookRepository;
import com.example.recsys.repository.BookReviewRepository;
import com.example.recsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookReviewRepository bookReviewRepository;

    public static final int LIMIT = 48;

    public BookServiceImpl(BookRepository bookRepository, BookReviewRepository bookReviewRepository) {
        this.bookRepository = bookRepository;
        this.bookReviewRepository = bookReviewRepository;
    }

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Books> searchBooksByMultipleFilter(String keyword, String genre, String sortBy) {
        List<Books> result = getAllBooks();

        if(keyword != null) {
            List<Books> keywordList = bookRepository.findByTitleContaining(keyword);
            result.retainAll(keywordList);
        }
        if(genre != null && !genre.equals("*")) {
            List<Books> genreList = bookRepository.findByGenreContaining(genre);
            result.retainAll(genreList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new BooksTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new BooksTitleComparator().reversed());
                }
                case "scoreAsc" -> {
                    result.sort(new BooksScoreComparator());
                }
                case "scoreDesc" -> {
                    result.sort(new BooksScoreComparator().reversed());
                }
                case "scoreCountAsc" -> {
                    result.sort(new BooksScoreCountComparator());
                }
                case "scoreCountDesc" -> {
                    result.sort(new BooksScoreCountComparator().reversed());
                }
            }
        }

        return result.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksGenres() {
        return bookRepository.findAllDistinctGenreList();
    }

    @Override
    public Books getBookById(Integer bookId) {
        return bookRepository.findById(bookId).get();
    }

    @Override
    public void saveReview(Integer bookId, String nickname, BookReviewDto bookReviewDto) {
        Books referencedBook = getBookById(bookId);

        BookReview bookReviews = new BookReview();
        bookReviews.setReviewScore(bookReviewDto.getScoreReview());
        bookReviews.setCategory(bookReviewDto.getCategory());
        bookReviews.setReviewMessage(bookReviewDto.getReviewMessage());
        bookReviews.setBooks(referencedBook);
        bookReviews.setNickname(nickname);
        bookReviews.setLocalDateTime(LocalDateTime.now());

        bookReviewRepository.save(bookReviews);
    }

    @Override
    public void updateReview(String nickname, Integer bookId, BookReviewDto bookReviewDto) {
        Optional<BookReview> bookReview = Optional.ofNullable(bookReviewRepository.findReviewByUserAndBook(nickname, bookId));

        if(bookReview.isPresent()) {
            String modifiedCategory = bookReviewDto.getCategory();
            Integer modifiedScore = bookReviewDto.getScoreReview();
            String modifiedReview = bookReviewDto.getReviewMessage();

            bookReviewRepository.updateReview(modifiedCategory, modifiedScore, modifiedReview, LocalDateTime.now(), bookReview.get().getBookReviewKey());
        }
    }

    @Override
    public void deleteReview(String nickname, Integer bookId) {
        Optional<BookReview> bookReview = Optional.ofNullable(bookReviewRepository.findReviewByUserAndBook(nickname, bookId));
        bookReview.ifPresent(review -> bookReviewRepository.deleteReview(review.getBookReviewKey()));
    }

    @Override
    public Optional<BookReview> getReviewByNicknameAndBookId(String nickname, Integer bookId) {
        return Optional.ofNullable(bookReviewRepository.findReviewByUserAndBook(nickname, bookId));
    }

    @Override
    public List<BookReview> getBookActivity(String nickname) {
        return bookReviewRepository.getBookActivity(nickname);
    }

    @Override
    public List<BookReview> searchPersonalBooksByMultipleFilter(String nickname, String category, String sortBy) {
        List<BookReview> result = getBookActivity(nickname);

        if(category != null && !category.equals("*")) {
            List<BookReview> categoryList = bookReviewRepository.getReviewsByCategories(nickname, category);
            result.retainAll(categoryList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new BookReviewTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new BookReviewTitleComparator().reversed());
                }
                case "ratingAsc" -> {
                    result.sort(new BookReviewRatingComparator());
                }
                case "ratingDesc" -> {
                    result.sort(new BookReviewRatingComparator().reversed());
                }
            }
        }

        return result.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookReview> getAllUserAndFriendsBooksActivity(String nickname) {
        return bookReviewRepository.getAllUserAndFriendsBooksActivity(nickname);
    }

    @Override
    public List<BookReview> getRecentCompletedBook(String nickname) {
        return bookReviewRepository.getRecentCompletedBook(nickname);
    }

    @Override
    public List<BookReview> getPlanToReadBook(String nickname) {
        return bookReviewRepository.getPlanToReadBook(nickname);
    }

    @Override
    public List<BookReview> getReviewsByNickname(String nickname) {
        return bookReviewRepository.getReviewsByNickname(nickname);
    }

    @Override
    public List<BookReview> getAllActivityByNickname(String nickname) {
        return bookReviewRepository.getAllActivityByNickname(nickname);
    }

    @Override
    public List<Books> getSimilarContent(Books books) {
        List<Books> result = new ArrayList<>();
        List<Books> similarMoviesByTitle = findByTitleContaining( getParsedTitle(books.getTitle()), books.getBookKey() );
        if(similarMoviesByTitle != null) {
            result.addAll(similarMoviesByTitle);
        }
        List<Books> similarTvByGenres = findByGenresContaining( books.getGenres() );
        if(similarTvByGenres != null) {
            result.addAll(similarTvByGenres);
        }
        result.remove(books);

        Set<Books> set = new LinkedHashSet<>(result);
        result.clear();
        result.addAll(set);

        return result;
    }

    @Override
    public List<Books> findByTitleContaining(String title, Integer bookKey) {
        return bookRepository.findByTitleContaining(title, bookKey).stream()
                .limit(5)
                .toList();
    }

    public String getParsedTitle(String title) {
        String[] result = title.split("\\s*[0-9:]+");

        return result[0].trim();
    }

    public List<Books> findByGenresContaining(String genre) {
        String[] genresList = genre.split(",");

        List<Books> resultList = getAllBooks();
        for(String movieGenre : genresList) {
            List<Books> tempList = bookRepository.findByGenreContaining(movieGenre);
            resultList.retainAll(tempList);
        }

        return resultList.stream()
                .limit(10)
                .toList();
    }

}