package com.example.recsys.service.implementation;

import com.example.recsys.comparators.profile.RecentReviewsDtoLocalDateComparator;
import com.example.recsys.comparators.profile.UserActivityDtoLocalDateComparator;
import com.example.recsys.dto.RecentReviewsDto;
import com.example.recsys.dto.UserActivityDto;
import com.example.recsys.entity.AnimeReview;
import com.example.recsys.entity.BookReview;
import com.example.recsys.entity.MovieReviews;
import com.example.recsys.entity.TvSeriesReviews;
import com.example.recsys.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Override
    public List<UserActivityDto> getAllUserRecentActivity(List<MovieReviews> movieReviews,
                                                          List<TvSeriesReviews> tvSeriesReviews,
                                                          List<AnimeReview> animeReviews,
                                                          List<BookReview> bookReviews) {
        List<UserActivityDto> userActivityDtoList = new ArrayList<>();

        for(MovieReviews movReview : movieReviews) {
            UserActivityDto tempUserActivity = new UserActivityDto();
            tempUserActivity.setTitle( movReview.getMovie().getTitle() );
            tempUserActivity.setType("movie");
            tempUserActivity.setPoster( movReview.getMovie().getPoster() );
            tempUserActivity.setContentPosterLink( "/movies/getById/" + movReview.getMovie().getMovieKey() );
            tempUserActivity.setReviewScore( movReview.getReviewScore() );
            tempUserActivity.setReviewMessage( movReview.getReviewMessage() );
            tempUserActivity.setLocalDateTime( movReview.getLocalDateTime() );

            userActivityDtoList.add(tempUserActivity);
        }

        for(TvSeriesReviews tvSeriesReview : tvSeriesReviews) {
            UserActivityDto tempUserActivity = new UserActivityDto();
            tempUserActivity.setTitle( tvSeriesReview.getTvSeries().getTitle() );
            tempUserActivity.setType("tv series");
            tempUserActivity.setPoster( tvSeriesReview.getTvSeries().getPoster() );
            tempUserActivity.setContentPosterLink( "/tv/getById/" + tvSeriesReview.getTvSeries().getTvKey() );
            tempUserActivity.setReviewScore( tvSeriesReview.getReviewScore() );
            tempUserActivity.setReviewMessage( tvSeriesReview.getReviewMessage() );
            tempUserActivity.setLocalDateTime( tvSeriesReview.getLocalDateTime() );

            userActivityDtoList.add(tempUserActivity);
        }

        for(AnimeReview animeReview : animeReviews) {
            UserActivityDto tempUserActivity = new UserActivityDto();
            tempUserActivity.setTitle( animeReview.getAnime().getTitle() );
            tempUserActivity.setType("anime");
            tempUserActivity.setPoster( animeReview.getAnime().getPoster() );
            tempUserActivity.setContentPosterLink( "/anime/getById/" + animeReview.getAnime().getAnimeKey() );
            tempUserActivity.setReviewScore( animeReview.getReviewScore() );
            tempUserActivity.setReviewMessage( animeReview.getReviewMessage() );
            tempUserActivity.setLocalDateTime( animeReview.getLocalDateTime() );

            userActivityDtoList.add(tempUserActivity);
        }

        for(BookReview bookReview : bookReviews) {
            UserActivityDto tempUserActivity = new UserActivityDto();
            tempUserActivity.setTitle( bookReview.getBooks().getTitle() );
            tempUserActivity.setType("book");
            tempUserActivity.setPoster( bookReview.getBooks().getPoster() );
            tempUserActivity.setContentPosterLink( "/books/getById/" + bookReview.getBooks().getBookKey() );
            tempUserActivity.setReviewScore( bookReview.getReviewScore() );
            tempUserActivity.setReviewMessage( bookReview.getReviewMessage() );
            tempUserActivity.setLocalDateTime( bookReview.getLocalDateTime() );

            userActivityDtoList.add(tempUserActivity);
        }

        userActivityDtoList.sort(new UserActivityDtoLocalDateComparator().reversed());

        return userActivityDtoList;
    }

    @Override
    public List<RecentReviewsDto> getAllRecentReviews(List<MovieReviews> movieReviews,
                                                      List<TvSeriesReviews> tvSeriesReviews,
                                                      List<AnimeReview> animeReviews,
                                                      List<BookReview> bookReviews) {
        List<RecentReviewsDto> recentReviewsDtoList = new ArrayList<>();

        for(MovieReviews movReview : movieReviews) {
            RecentReviewsDto tempUserReview = new RecentReviewsDto();
            tempUserReview.setTitle( movReview.getMovie().getTitle() );
            tempUserReview.setLink( "/movies/getById/" + movReview.getMovie().getMovieKey() );
            tempUserReview.setReviewScore( movReview.getReviewScore() );
            tempUserReview.setReviewMessage( movReview.getReviewMessage() );
            tempUserReview.setLocalDateTime( movReview.getLocalDateTime() );

            recentReviewsDtoList.add(tempUserReview);
        }

        for(TvSeriesReviews tvSeriesReview : tvSeriesReviews) {
            RecentReviewsDto tempUserReview = new RecentReviewsDto();
            tempUserReview.setTitle( tvSeriesReview.getTvSeries().getTitle() );
            tempUserReview.setLink( "/tv/getById/" + tvSeriesReview.getTvSeries().getTvKey() );
            tempUserReview.setReviewScore( tvSeriesReview.getReviewScore() );
            tempUserReview.setReviewMessage( tvSeriesReview.getReviewMessage() );
            tempUserReview.setLocalDateTime( tvSeriesReview.getLocalDateTime() );

            recentReviewsDtoList.add(tempUserReview);
        }

        for(AnimeReview animeReview : animeReviews) {
            RecentReviewsDto tempUserReview = new RecentReviewsDto();
            tempUserReview.setTitle( animeReview.getAnime().getTitle() );
            tempUserReview.setLink( "/anime/getById/" + animeReview.getAnime().getAnimeKey() );
            tempUserReview.setReviewScore( animeReview.getReviewScore() );
            tempUserReview.setReviewMessage( animeReview.getReviewMessage() );
            tempUserReview.setLocalDateTime( animeReview.getLocalDateTime() );

            recentReviewsDtoList.add(tempUserReview);
        }

        for(BookReview bookReview : bookReviews) {
            RecentReviewsDto tempUserReview = new RecentReviewsDto();
            tempUserReview.setTitle( bookReview.getBooks().getTitle() );
            tempUserReview.setLink( "/books/getById/" + bookReview.getBooks().getBookKey() );
            tempUserReview.setReviewScore( bookReview.getReviewScore() );
            tempUserReview.setReviewMessage( bookReview.getReviewMessage() );
            tempUserReview.setLocalDateTime( bookReview.getLocalDateTime() );

            recentReviewsDtoList.add(tempUserReview);
        }

        recentReviewsDtoList.sort(new RecentReviewsDtoLocalDateComparator().reversed());

        return recentReviewsDtoList;
    }

}