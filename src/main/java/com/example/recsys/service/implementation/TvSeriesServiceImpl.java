package com.example.recsys.service.implementation;

import com.example.recsys.comparators.movies.MovieImdbRatingComparator;
import com.example.recsys.comparators.tvseries.TvImdbComparator;
import com.example.recsys.comparators.tvseries.TvLengthComparator;
import com.example.recsys.comparators.tvseries.TvTitleComparator;
import com.example.recsys.comparators.tvseries.reviews.TvReviewImdbComparator;
import com.example.recsys.comparators.tvseries.reviews.TvReviewTitleComparator;
import com.example.recsys.dto.TvReviewDto;
import com.example.recsys.entity.*;
import com.example.recsys.repository.TvSeriesRepository;
import com.example.recsys.repository.TvSeriesReviewRepository;
import com.example.recsys.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TvSeriesServiceImpl implements TvSeriesService {

    @Autowired
    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    private final TvSeriesReviewRepository tvSeriesReviewRepository;

    public TvSeriesServiceImpl(TvSeriesRepository tvSeriesRepository, TvSeriesReviewRepository tvSeriesReviewRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
        this.tvSeriesReviewRepository = tvSeriesReviewRepository;
    }

    public static final int LIMIT = 16;

    @Override
    public List<TvSeries> getAllTv() {
        return tvSeriesRepository.findAll();
    }

    @Override
    public List<TvSeries> searchTvByMultipleFilter(String keyword, String genre, Integer startYear, Integer endYear, String sortBy) {
        List<TvSeries> result = getAllTv();

        if(keyword != null) {
            List<TvSeries> keywordList = tvSeriesRepository.findByTitleContainingOrOverviewContaining(keyword);
            result.retainAll(keywordList);
        }
        if(genre != null && !genre.equals("*")) {
            List<TvSeries> genreList = tvSeriesRepository.findByGenreContaining(genre);
            result.retainAll(genreList);
        }
        if(startYear != null && startYear != 0) {
            List<TvSeries> startYearList = tvSeriesRepository.findByMinimStartYearRelease(startYear);
            result.retainAll(startYearList);
        }
        if(endYear != null && endYear != 0) {
            List<TvSeries> endYearList = tvSeriesRepository.findByMaximEndYearRelease(endYear);
            result.retainAll(endYearList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new TvTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new TvTitleComparator().reversed());
                }
                case "lengthAsc" -> {
                    result.sort(new TvLengthComparator());
                }
                case "lengthDesc" -> {
                    result.sort(new TvLengthComparator().reversed());
                }
                case "imdbAsc" -> {
                    result.sort(new TvImdbComparator());
                }
                case "imdbDesc" -> {
                    result.sort(new TvImdbComparator().reversed());
                }
            }
        }

        return result.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public TvSeries getTvById(int tvID) {
        return tvSeriesRepository.findById(tvID).get();
    }

    @Override
    public List<String> getTvGenres() {
        return tvSeriesRepository.findAllDistinctGenreList();
    }

    @Override
    public List<Integer> getReleaseStartYears() {
        return tvSeriesRepository.getReleaseStartYears();
    }

    @Override
    public List<Integer> getReleaseEndYears() {
        return tvSeriesRepository.getReleaseEndYears();
    }

    @Override
    public void saveReview(Integer tvId, String nickname, TvReviewDto tvReviewsDto) {
        TvSeries referencedTv = getTvById(tvId);

        TvSeriesReviews tvReviews = new TvSeriesReviews();
        tvReviews.setReviewScore(tvReviewsDto.getScoreReview());
        tvReviews.setCategory(tvReviewsDto.getCategory());
        tvReviews.setReviewMessage(tvReviewsDto.getReviewMessage());
        tvReviews.setTvSeries(referencedTv);
        tvReviews.setNickname(nickname);
        tvReviews.setLocalDateTime(LocalDateTime.now());

        tvSeriesReviewRepository.save(tvReviews);
    }

    @Override
    public void updateReview(String nickname, Integer tvId, TvReviewDto movieReviewDto) {
        Optional<TvSeriesReviews> tvReview = Optional.ofNullable(tvSeriesReviewRepository.findReviewByUserAndMovie(nickname, tvId));

        if(tvReview.isPresent()) {
            String modifiedCategory = movieReviewDto.getCategory();
            Integer modifiedScore = movieReviewDto.getScoreReview();
            String modifiedReview = movieReviewDto.getReviewMessage();

            tvSeriesReviewRepository.updateReview(modifiedCategory, modifiedScore, modifiedReview, LocalDateTime.now(), tvReview.get().getTvReviewKey());
        }
    }

    @Override
    public void deleteReview(String nickname, Integer tvId) {
        Optional<TvSeriesReviews> tvReview = Optional.ofNullable(tvSeriesReviewRepository.findReviewByUserAndMovie(nickname, tvId));
        tvReview.ifPresent(tvReviews -> tvSeriesReviewRepository.deleteReview(tvReviews.getTvReviewKey()));
    }

    @Override
    public Optional<TvSeriesReviews> getReviewByNicknameAndTvId(String nickname, Integer tvId) {
        return Optional.ofNullable(tvSeriesReviewRepository.findReviewByUserAndMovie(nickname, tvId));
    }

    @Override
    public List<TvSeriesReviews> getTvActivity(String nickname) {
        return tvSeriesReviewRepository.getTvActivity(nickname);
    }

    @Override
    public List<TvSeriesReviews> searchPersonalTvByMultipleFilter(String nickname, String category, String sortBy) {
        List<TvSeriesReviews> result = getTvActivity(nickname);

        if(category != null && !category.equals("*")) {
            List<TvSeriesReviews> categoryList = tvSeriesReviewRepository.getReviewsByCategories(nickname, category);
            result.retainAll(categoryList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new TvReviewTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new TvReviewTitleComparator().reversed());
                }
                case "imdbAsc" -> {
                    result.sort(new TvReviewImdbComparator());
                }
                case "imdbDesc" -> {
                    result.sort(new TvReviewImdbComparator().reversed());
                }
            }
        }

        return result;
    }

    @Override
    public List<TvSeriesReviews> getAllUserAndFriendsTvActivity(String nickname) {
        return tvSeriesReviewRepository.getAllUserAndFriendsTvActivity(nickname);
    }

    @Override
    public List<TvSeriesReviews> getRecentWatchedTvs(String nickname) {
        return tvSeriesReviewRepository.getRecentWatchedTvs(nickname);
    }

    @Override
    public List<TvSeriesReviews> getPlanToWatchTvs(String nickname) {
        return tvSeriesReviewRepository.getPlanToWatchTvs(nickname);
    }

    @Override
    public List<TvSeriesReviews> getReviewsByNickname(String nickname) {
        return tvSeriesReviewRepository.getReviewsByNickname(nickname);
    }

    @Override
    public List<TvSeriesReviews> getAllActivityByNickname(String nickname) {
        return tvSeriesReviewRepository.getAllActivityByNickname(nickname);
    }

    @Override
    public List<TvSeries> getSimilarContent(TvSeries tvSeriesDetails) {
        List<TvSeries> result = new ArrayList<>();
        List<TvSeries> similarMoviesByTitle = findByTitleContaining( getParsedTitle(tvSeriesDetails.getTitle()), tvSeriesDetails.getTvKey() );
        if(similarMoviesByTitle != null) {
            result.addAll(similarMoviesByTitle);
        }
        List<TvSeries> similarTvByGenres = findByGenresContaining( tvSeriesDetails.getGenres() );
        if(similarTvByGenres != null) {
            result.addAll(similarTvByGenres);
        }
        result.remove(tvSeriesDetails);

        Set<TvSeries> set = new LinkedHashSet<>(result);
        result.clear();
        result.addAll(set);

        return result;
    }

    @Override
    public List<TvSeries> findByTitleContaining(String title, Integer movieKey) {
        return tvSeriesRepository.findByTitleContaining(title, movieKey).stream()
                .limit(5)
                .toList();
    }

    @Override
    public List<TvSeries> recommendedByFriends(List<Followers> followers, String nickname) {
        List<TvSeriesReviews> completedBookReviews = getRecentWatchedTvs(nickname);
        List<TvSeries> personalBooks = new ArrayList<>();
        List<TvSeries> friendsBooks = new ArrayList<>();

        for(TvSeriesReviews completedBookReview : completedBookReviews) {
            personalBooks.add(completedBookReview.getTvSeries());
        }

        for(Followers follower : followers) {
            String followerNick = follower.getId().getFollower();
            List<TvSeriesReviews> fiendsCompletedBookReviews = getRecentWatchedTvs(followerNick);

            for(TvSeriesReviews friendCompletedReview : fiendsCompletedBookReviews) {
                friendsBooks.add(friendCompletedReview.getTvSeries());
            }
        }

        friendsBooks.removeAll(personalBooks);
        friendsBooks.sort(new TvImdbComparator().reversed());

        return friendsBooks.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    public String getParsedTitle(String title) {
        String[] result = title.split("\\s*[0-9:]+");

        return result[0].trim();
    }

    public List<TvSeries> findByGenresContaining(String genre) {
        String[] genresList = genre.split(",");

        List<TvSeries> resultList = getAllTv();
        for(String movieGenre : genresList) {
            List<TvSeries> tempList = tvSeriesRepository.findByGenreContaining(movieGenre);
            resultList.retainAll(tempList);
        }

        return resultList.stream()
                .limit(10)
                .toList();
    }
}