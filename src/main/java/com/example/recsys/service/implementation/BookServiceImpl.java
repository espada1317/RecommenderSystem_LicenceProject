package com.example.recsys.service.implementation;

import com.example.recsys.comparators.anime.AnimeLengthComparator;
import com.example.recsys.comparators.anime.AnimeScoreComparator;
import com.example.recsys.comparators.anime.AnimeTitleComparator;
import com.example.recsys.comparators.books.BooksScoreComparator;
import com.example.recsys.comparators.books.BooksScoreCountComparator;
import com.example.recsys.comparators.books.BooksTitleComparator;
import com.example.recsys.entity.Books;
import com.example.recsys.repository.BookRepository;
import com.example.recsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public static final int LIMIT = 48;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

}