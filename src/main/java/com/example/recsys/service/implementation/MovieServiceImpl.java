package com.example.recsys.service.implementation;

import com.example.recsys.comparators.movies.*;
import com.example.recsys.comparators.movies.reviews.MovieReviewImdbRatingComparator;
import com.example.recsys.comparators.movies.reviews.MovieReviewTitleComparator;
import com.example.recsys.comparators.movies.reviews.MovieReviewYearComparator;
import com.example.recsys.dto.MovieReviewDto;
import com.example.recsys.entity.Movie;
import com.example.recsys.entity.MovieReviews;
import com.example.recsys.repository.MovieRepository;
import com.example.recsys.repository.MovieReviewRepository;
import com.example.recsys.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final MovieReviewRepository movieReviewRepository;

    public static final int LIMIT = 48;

    public MovieServiceImpl(MovieRepository movieRepository, MovieReviewRepository movieReviewRepository) {
        this.movieRepository = movieRepository;
        this.movieReviewRepository = movieReviewRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMoviesByMultipleFilter(String keyword, String genre, Integer year, String language, String sortBy) {
        List<Movie> result = getAllMovies();

        if(keyword != null) {
           List<Movie> keywordList = movieRepository.findByTitleContainingOrOverviewContaining(keyword);
           result.retainAll(keywordList);
        }
        if(genre != null && !genre.equals("*")) {
            List<Movie> genreList = movieRepository.findByGenreContaining(genre);
            result.retainAll(genreList);
        }
        if(year != null && year != 0) {
            List<Movie> yearList = movieRepository.findByYearContaining(year);
            result.retainAll(yearList);
        }
        if(language != null && !language.equals("*")) {
            List<Movie> langList = movieRepository.findByLanguageContaining(language);
            result.retainAll(langList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new MovieTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new MovieTitleComparator().reversed());
                }
                case "yearAsc" -> {
                    result.sort(new MovieYearComparator());
                }
                case "yearDesc" -> {
                    result.sort(new MovieYearComparator().reversed());
                }
                case "langAsc" -> {
                    result.sort(new MovieLanguageComparator());
                }
                case "langDesc" -> {
                    result.sort(new MovieLanguageComparator().reversed());
                }
                case "lengthAsc" -> {
                    result.sort(new MovieLengthComparator());
                }
                case "lengthDesc" -> {
                    result.sort(new MovieLengthComparator().reversed());
                }
                case "popularityAsc" -> {
                    result.sort(new MoviePopularityComparator());
                }
                case "popularityDesc" -> {
                    result.sort(new MoviePopularityComparator().reversed());
                }
                case "imdbAsc" -> {
                    result.sort(new MovieImdbRatingComparator());
                }
                case "imdbDesc" -> {
                    result.sort(new MovieImdbRatingComparator().reversed());
                }
            }
        }

        return result.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public List<String> getMovieGenres() {
        return movieRepository.findAllDistinctGenreList();
    }

    @Override
    public List<Integer> getReleaseYears() {
        return movieRepository.findAllDistinctYearList();
    }

    @Override
    public List<String> getOriginalLanguages() {
        return movieRepository.findAllDistinctLanguageList();
    }

    @Override
    public void saveReview(Integer movieId, String nickname, MovieReviewDto movieReviewsDto) {
        Movie referencedMovie = getMovieById(movieId);

        MovieReviews movieReviews = new MovieReviews();
        movieReviews.setReviewScore(movieReviewsDto.getScoreReview());
        movieReviews.setCategory(movieReviewsDto.getCategory());
        movieReviews.setReviewMessage(movieReviewsDto.getReviewMessage());
        movieReviews.setMovie(referencedMovie);
        movieReviews.setNickname(nickname);
        movieReviews.setLocalDateTime(LocalDateTime.now());

        movieReviewRepository.save(movieReviews);
    }

    @Override
    public Optional<MovieReviews> getReviewByNicknameAndMovieId(String nickname, Integer movieId) {
        return Optional.ofNullable(movieReviewRepository.findReviewByUserAndMovie(nickname, movieId));
    }

    @Override
    public void updateReview(String nickname, Integer movieId, MovieReviewDto movieReviewDto) {
        Optional<MovieReviews> movieReview = Optional.ofNullable(movieReviewRepository.findReviewByUserAndMovie(nickname, movieId));

        if(movieReview.isPresent()) {
            String modifiedCategory = movieReviewDto.getCategory();
            Integer modifiedScore = movieReviewDto.getScoreReview();
            String modifiedReview = movieReviewDto.getReviewMessage();

            movieReviewRepository.updateReview(modifiedCategory, modifiedScore, modifiedReview, LocalDateTime.now(), movieReview.get().getMovieReviewKey());
        }
    }

    @Override
    public void deleteReview(String nickname, Integer movieId) {
        Optional<MovieReviews> movieReview = Optional.ofNullable(movieReviewRepository.findReviewByUserAndMovie(nickname, movieId));
        movieReview.ifPresent(movieReviews -> movieReviewRepository.deleteReview(movieReviews.getMovieReviewKey()));
    }

    @Override
    public List<MovieReviews> getAllUserAndFriendsMovieActivity(String nickname) {
        return movieReviewRepository.getAllUserAndFriendsMovieActivity(nickname);
    }

    @Override
    public List<MovieReviews> getMoviesActivity(String nickname) {
        return movieReviewRepository.getMoviesActivity(nickname);
    }

    @Override
    public List<Integer> getDistinctPersonalReleaseYears(String nickname) {
        return movieReviewRepository.getListOfDistinctReleaseYearsOfPersonalMovies(nickname);
    }

    @Override
    public List<MovieReviews> searchPersonalMoviesByMultipleFilter(String nickname, String category, Integer year, String sortBy) {
        List<MovieReviews> result = getMoviesActivity(nickname);

        if(category != null && !category.equals("*")) {
            List<MovieReviews> categoryList = movieReviewRepository.getReviewsByCategories(nickname, category);
            result.retainAll(categoryList);
        }
        if(year != null && year != 0) {
            List<MovieReviews> yearList = movieReviewRepository.getReviewsByMovieYearRelease(nickname, year);
            result.retainAll(yearList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new MovieReviewTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new MovieReviewTitleComparator().reversed());
                }
                case "yearAsc" -> {
                    result.sort(new MovieReviewYearComparator());
                }
                case "yearDesc" -> {
                    result.sort(new MovieReviewYearComparator().reversed());
                }
                case "imdbAsc" -> {
                    result.sort(new MovieReviewImdbRatingComparator());
                }
                case "imdbDesc" -> {
                    result.sort(new MovieReviewImdbRatingComparator().reversed());
                }
            }
        }

        return result;
    }

    public List<MovieReviews> getPersonalMoviesByCategory(String nickname, String category) {
        return movieReviewRepository.getReviewsByCategories(nickname, category);
    }

    public List<MovieReviews> getPersonalMoviesByYear(String nickname, Integer year) {
        return movieReviewRepository.getReviewsByMovieYearRelease(nickname, year);
    }

    @Override
    public List<MovieReviews> getRecentWatchedMovies(String nickname) {
        return movieReviewRepository.getRecentWatchedMovies(nickname);
    }

    @Override
    public List<MovieReviews> getPlanToWatchMovies(String nickname) {
        return movieReviewRepository.getPlanToWatchMovies(nickname);
    }

    @Override
    public List<MovieReviews> getReviewsByNickname(String nickname) {
        return movieReviewRepository.getReviewsByNickname(nickname);
    }

    @Override
    public List<Movie> getSimilarContent(Movie movieDetails) {
        List<Movie> result = new ArrayList<>();
        List<Movie> similarMoviesByTitle = findByTitleContaining( getParsedTitle(movieDetails.getTitle()), movieDetails.getMovieKey() );
        if(similarMoviesByTitle != null) {
            result.addAll(similarMoviesByTitle);
        }
        List<Movie> similarMoviesByGenres = findByGenresContaining( movieDetails.getGenres() );
        if(similarMoviesByGenres != null) {
            result.addAll(similarMoviesByGenres);
        }
        result.remove(movieDetails);

        Set<Movie> set = new LinkedHashSet<>(result);
        result.clear();
        result.addAll(set);

        return result;
    }

    @Override
    public List<Movie> findByTitleContaining(String title, Integer movieKey) {
        return movieRepository.findByTitleContaining(title, movieKey).stream()
                .limit(5)
                .toList();
    }

    @Override
    public List<MovieReviews> getAllActivityByNickname(String nickname) {
        return movieReviewRepository.getAllActivityByNickname(nickname);
    }

    public List<Movie> findByGenresContaining(String genre) {
        String[] genresList = genre.split(",");

        List<Movie> resultList = getAllMovies();
        for(String movieGenre : genresList) {
            List<Movie> tempList = movieRepository.findByGenreContaining(movieGenre);
            resultList.retainAll(tempList);
        }

        return resultList.stream()
                .limit(10)
                .toList();
    }

    public String getParsedTitle(String title) {
        String[] result = title.split("\\s*[0-9:]+");

        return result[0].trim();
    }

}