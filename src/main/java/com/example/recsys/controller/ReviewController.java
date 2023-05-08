package com.example.recsys.controller;

import com.example.recsys.dto.AnimeReviewDto;
import com.example.recsys.dto.BookReviewDto;
import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.dto.TvReviewDto;
import com.example.recsys.service.AnimeService;
import com.example.recsys.service.BookService;
import com.example.recsys.service.MovieService;
import com.example.recsys.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ReviewController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final TvSeriesService tvSeriesService;

    @Autowired
    private final AnimeService animeService;

    @Autowired
    private final BookService bookService;

    public ReviewController(MovieService movieService, TvSeriesService tvSeriesService, AnimeService animeService, BookService bookService) {
        this.movieService = movieService;
        this.tvSeriesService = tvSeriesService;
        this.animeService = animeService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=save")
    public String saveMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                  @RequestParam("id") Integer movieId,
                                  Principal principal) {
        movieService.saveReview(movieId, principal.getName(), movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=update")
    public String updateMovieReview(@ModelAttribute("movieReviewDto") MovieReviewDto movieReviewDto,
                                    @RequestParam("id") Integer movieId,
                                    Principal principal) {
        movieService.updateReview(principal.getName(), movieId, movieReviewDto);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/movie/saveReview", params = "action=delete")
    public String deleteMovieReview(@RequestParam("id") Integer movieId,
                                    Principal principal) {
        movieService.deleteReview(principal.getName(), movieId);
        return "redirect:/movies/getById/" + movieId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=save")
    public String saveTvReview(@ModelAttribute("tvReviewDto") TvReviewDto tvReviewDto,
                                  @RequestParam("id") Integer movieId,
                                  Principal principal) {
        tvSeriesService.saveReview(movieId, principal.getName(), tvReviewDto);
        return "redirect:/tv/getById/" + movieId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=update")
    public String updateTvReview(@ModelAttribute("tvReviewDto") TvReviewDto tvReviewDto,
                                    @RequestParam("id") Integer tvId,
                                    Principal principal) {
        tvSeriesService.updateReview(principal.getName(), tvId, tvReviewDto);
        return "redirect:/tv/getById/" + tvId;
    }

    @PostMapping(value = "/tv/saveReview", params = "action=delete")
    public String deleteTvReview(@RequestParam("id") Integer movieId,
                                    Principal principal) {
        tvSeriesService.deleteReview(principal.getName(), movieId);
        return "redirect:/tv/getById/" + movieId;
    }

    @PostMapping(value = "/anime/saveReview", params = "action=save")
    public String saveAnimeReview(@ModelAttribute("animeReviewDto") AnimeReviewDto animeReviewDto,
                               @RequestParam("id") Integer animeId,
                               Principal principal) {
        animeService.saveReview(animeId, principal.getName(), animeReviewDto);
        return "redirect:/anime/getById/" + animeId;
    }

    @PostMapping(value = "/anime/saveReview", params = "action=update")
    public String updateAnimeReview(@ModelAttribute("animeReviewDto") AnimeReviewDto animeReviewDto,
                                 @RequestParam("id") Integer animeId,
                                 Principal principal) {
        animeService.updateReview(principal.getName(), animeId, animeReviewDto);
        return "redirect:/anime/getById/" + animeId;
    }

    @PostMapping(value = "/anime/saveReview", params = "action=delete")
    public String deleteAnimeReview(@RequestParam("id") Integer animeId,
                                 Principal principal) {
        animeService.deleteReview(principal.getName(), animeId);
        return "redirect:/anime/getById/" + animeId;
    }

    @PostMapping(value = "/books/saveReview", params = "action=save")
    public String saveBookReview(@ModelAttribute("bookReviewDto") BookReviewDto bookReviewDto,
                                  @RequestParam("id") Integer bookId,
                                  Principal principal) {
        bookService.saveReview(bookId, principal.getName(), bookReviewDto);
        return "redirect:/books/getById/" + bookId;
    }

    @PostMapping(value = "/books/saveReview", params = "action=update")
    public String updateBookReview(@ModelAttribute("bookReviewDto") BookReviewDto bookReviewDto,
                                    @RequestParam("id") Integer bookId,
                                    Principal principal) {
        bookService.updateReview(principal.getName(), bookId, bookReviewDto);
        return "redirect:/books/getById/" + bookId;
    }

    @PostMapping(value = "/books/saveReview", params = "action=delete")
    public String deleteBookReview(@RequestParam("id") Integer bookId,
                                    Principal principal) {
        bookService.deleteReview(principal.getName(), bookId);
        return "redirect:/books/getById/" + bookId;
    }

}