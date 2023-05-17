package com.example.recsys.controller;

import com.example.recsys.entity.BookReview;
import com.example.recsys.entity.Books;
import com.example.recsys.entity.Followers;
import com.example.recsys.service.BookService;
import com.example.recsys.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    @Autowired
    private final ProfileService profileService;

    public BookController(BookService bookService, ProfileService profileService) {
        this.bookService = bookService;
        this.profileService = profileService;
    }

    @GetMapping
    public String listOfBooks(Model model,
                              @Param("keyword") String keyword,
                              @Param("genre") String genre,
                              @Param("sortBy") String sortBy,
                              Principal principal) {
        model.addAttribute("isBookPage", true);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("booksGenres", bookService.getBooksGenres());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre", genre);
        model.addAttribute("selectedSort", sortBy);
        List<Followers> followersList = profileService.getAllActiveFollowers(principal.getName());
        List<Books> friendsListBooks = bookService.parseFriendsBooks(followersList);
        model.addAttribute("personalRecommend", bookService.personalRecommended(principal.getName(), friendsListBooks));
        model.addAttribute("bookList", bookService.searchBooksByMultipleFilter(keyword, genre, sortBy));
        model.addAttribute("friendsRecommend", bookService.recommendedByFriends( friendsListBooks, principal.getName() ));
        return "books";
    }

    @GetMapping("/getById/{id}")
    public String bookById(Model model,
                            @PathVariable("id") int bookID,
                            Principal principal) {
        model.addAttribute("isBookPage", true);
        model.addAttribute("bookDetails", bookService.getBookById(bookID));
        model.addAttribute("similarBooks", bookService.getSimilarContent(bookService.getBookById(bookID)));
        model.addAttribute("bookID", bookID);
        Optional<BookReview> bookReview = bookService.getReviewByNicknameAndBookId(principal.getName(), bookID);
        bookReview.ifPresent(book -> model.addAttribute("reviewInfo", book));
        return "book_details";
    }

}