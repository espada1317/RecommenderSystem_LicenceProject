package com.example.recsys.service;

import com.example.recsys.entity.Books;

import java.util.List;

public interface BookService {

    List<Books> getAllBooks();

    List<Books> searchBooksByMultipleFilter(String keyword, String genre, String author);

    List<String> getBooksGenres();

}