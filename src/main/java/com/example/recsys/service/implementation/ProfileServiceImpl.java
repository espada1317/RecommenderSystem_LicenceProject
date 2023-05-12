package com.example.recsys.service.implementation;

import com.example.recsys.comparators.profile.RecentReviewsDtoLocalDateComparator;
import com.example.recsys.comparators.profile.UserActivityDtoLocalDateComparator;
import com.example.recsys.dto.*;
import com.example.recsys.entity.*;
import com.example.recsys.repository.FollowersRepository;
import com.example.recsys.repository.UserInfoRepository;
import com.example.recsys.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private final FollowersRepository followersRepository;

    @Autowired
    private final UserInfoRepository userInfoRepository;

    public ProfileServiceImpl(FollowersRepository followersRepository, UserInfoRepository userInfoRepository) {
        this.followersRepository = followersRepository;
        this.userInfoRepository = userInfoRepository;
    }

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

    @Override
    public MovieStatsDto getPersonalMovieStats(List<MovieReviews> movieReviews) {
        int totalCount = movieReviews.size();
        int planCount = 0;
        int completedCount = 0;
        int droppedCount = 0;
        int completedTotalLength = 0;
        int planTotalLength = 0;
        int droppedTotalLength = 0;

        for(MovieReviews x : movieReviews) {
            if(x.getCategory().equals("plan_watch")) {
                planCount++;
                planTotalLength += x.getMovie().getLength();
            }
            if(x.getCategory().equals("completed")) {
                completedCount++;
                completedTotalLength += x.getMovie().getLength();
            }
            if(x.getCategory().equals("dropped")) {
                droppedCount++;
                droppedTotalLength += x.getMovie().getLength();
            }
        }

        int tempCount = completedTotalLength;
        int daysCount = tempCount / (24 * 60);
        tempCount -= daysCount * 24 * 60;
        int hoursCount = tempCount / 60;
        tempCount -= hoursCount * 60;
        int minutesCount = tempCount % 60;

        double planDays = planTotalLength / ( 24.0 * 60.0 );
        double droppedDays = droppedTotalLength / ( 24.0 * 60.0 );
        double completedDays = completedTotalLength / ( 24.0 * 60.0 );

        MovieStatsDto result = new MovieStatsDto();
        result.setCompleted(completedCount);
        result.setDropped(droppedCount);
        result.setPlanWatch(planCount);
        result.setTotalCount(totalCount);
        result.setDays(daysCount);
        result.setHours(hoursCount);
        result.setMinutes(minutesCount);
        result.setDaysWatchPlan( (double) Math.round(planDays * 100) / 100 );
        result.setDaysWatchDropped( (double) Math.round(droppedDays * 100) / 100 );
        result.setDaysWatchCompleted( (double) Math.round(completedDays * 100) / 100 );

        return result;
    }

    @Override
    public TvStatsDto getPersonalTvStats(List<TvSeriesReviews> tvSeriesReviews) {
        int totalCount = tvSeriesReviews.size();
        int planCount = 0;
        int completedCount = 0;
        int droppedCount = 0;
        int watchingCount = 0;
        int onHoldCount = 0;

        int completedTotalLength = 0;
        int planTotalLength = 0;
        int droppedTotalLength = 0;
        int watchingTotalLength = 0;
        int onHoldTotalLength = 0;

        int noEpisodesPlan = 0;
        int noEpisodesWatching = 0;
        int noEpisodesCompleted = 0;
        int noEpisodesOnHold = 0;
        int noEpisodesDropped = 0;

        for(TvSeriesReviews x : tvSeriesReviews) {
            int temp = x.getTvSeries().getAverageRuntime() * x.getTvSeries().getNumberEpisodes();
            if(x.getCategory().equals("plan_watch")) {
                planCount++;
                planTotalLength += temp;
                noEpisodesPlan += x.getTvSeries().getNumberEpisodes();
            }
            if(x.getCategory().equals("completed")) {
                completedCount++;
                completedTotalLength += temp;
                noEpisodesCompleted += x.getTvSeries().getNumberEpisodes();
            }
            if(x.getCategory().equals("watching")) {
                watchingCount++;
                watchingTotalLength += temp;
                noEpisodesWatching += x.getTvSeries().getNumberEpisodes();
            }
            if(x.getCategory().equals("dropped")) {
                droppedCount++;
                droppedTotalLength += temp;
                noEpisodesDropped += x.getTvSeries().getNumberEpisodes();
            }
            if(x.getCategory().equals("on_hold")) {
                onHoldCount++;
                onHoldTotalLength += temp;
                noEpisodesOnHold += x.getTvSeries().getNumberEpisodes();
            }
        }

        int tempCount = completedTotalLength;
        int daysCount = tempCount / (24 * 60);
        tempCount -= daysCount * 24 * 60;
        int hoursCount = tempCount / 60;
        tempCount -= hoursCount * 60;
        int minutesCount = tempCount % 60;

        double planDays = planTotalLength / ( 24.0 * 60.0 );
        double droppedDays = droppedTotalLength / ( 24.0 * 60.0 );
        double completedDays = completedTotalLength / ( 24.0 * 60.0 );
        double watchingDays = watchingTotalLength / ( 24.0 * 60.0 );
        double onHoldDays = onHoldTotalLength / (24.0 * 60.0 );

        TvStatsDto result = new TvStatsDto();
        result.setCompleted(completedCount);
        result.setDropped(droppedCount);
        result.setPlanWatch(planCount);
        result.setOnHold(onHoldCount);
        result.setWatching(watchingCount);

        result.setTotalCount(totalCount);

        result.setDays(daysCount);
        result.setHours(hoursCount);
        result.setMinutes(minutesCount);

        result.setDaysWatchPlan( (double) Math.round(planDays * 100) / 100 );
        result.setDaysWatchDropped( (double) Math.round(droppedDays * 100) / 100 );
        result.setDaysWatchCompleted( (double) Math.round(completedDays * 100) / 100 );
        result.setDaysWatchWatching( (double) Math.round(watchingDays * 100) / 100 );
        result.setDaysWatchOnHold( (double) Math.round(onHoldDays * 100) / 100 );

        result.setNoEpisodesCompleted(noEpisodesCompleted);
        result.setNoEpisodesDropped(noEpisodesDropped);
        result.setNoEpisodesPlan(noEpisodesPlan);
        result.setNoEpisodesWatching(noEpisodesWatching);
        result.setNoEpisodesOnHold(noEpisodesOnHold);

        return result;
    }

    @Override
    public AnimeStatsDto getPersonalAnimeStats(List<AnimeReview> animeReviews) {
        int totalCount = animeReviews.size();
        int planCount = 0;
        int completedCount = 0;
        int droppedCount = 0;
        int watchingCount = 0;
        int onHoldCount = 0;

        int completedTotalLength = 0;
        int planTotalLength = 0;
        int droppedTotalLength = 0;
        int watchingTotalLength = 0;
        int onHoldTotalLength = 0;

        int noEpisodesPlan = 0;
        int noEpisodesWatching = 0;
        int noEpisodesCompleted = 0;
        int noEpisodesOnHold = 0;
        int noEpisodesDropped = 0;

        for(AnimeReview x : animeReviews) {
            int temp = x.getAnime().getAverageRuntime() * x.getAnime().getNumberEpisodes();
            if(x.getCategory() == null) {
                continue;
            }
            if(x.getCategory().equals("plan_watch")) {
                planCount++;
                planTotalLength += temp;
                noEpisodesPlan += x.getAnime().getNumberEpisodes();
            }
            if(x.getCategory().equals("completed")) {
                completedCount++;
                completedTotalLength += temp;
                noEpisodesCompleted += x.getAnime().getNumberEpisodes();
            }
            if(x.getCategory().equals("watching")) {
                watchingCount++;
                watchingTotalLength += temp;
                noEpisodesWatching += x.getAnime().getNumberEpisodes();
            }
            if(x.getCategory().equals("dropped")) {
                droppedCount++;
                droppedTotalLength += temp;
                noEpisodesDropped += x.getAnime().getNumberEpisodes();
            }
            if(x.getCategory().equals("on_hold")) {
                onHoldCount++;
                onHoldTotalLength += temp;
                noEpisodesOnHold += x.getAnime().getNumberEpisodes();
            }
        }

        int tempCount = completedTotalLength;
        int daysCount = tempCount / (24 * 60);
        tempCount -= daysCount * 24 * 60;
        int hoursCount = tempCount / 60;
        tempCount -= hoursCount * 60;
        int minutesCount = tempCount % 60;

        double planDays = planTotalLength / ( 24.0 * 60.0 );
        double droppedDays = droppedTotalLength / ( 24.0 * 60.0 );
        double completedDays = completedTotalLength / ( 24.0 * 60.0 );
        double watchingDays = watchingTotalLength / ( 24.0 * 60.0 );
        double onHoldDays = onHoldTotalLength / (24.0 * 60.0 );

        AnimeStatsDto result = new AnimeStatsDto();
        result.setCompleted(completedCount);
        result.setDropped(droppedCount);
        result.setPlanWatch(planCount);
        result.setOnHold(onHoldCount);
        result.setWatching(watchingCount);

        result.setTotalCount(totalCount);

        result.setDays(daysCount);
        result.setHours(hoursCount);
        result.setMinutes(minutesCount);

        result.setDaysWatchPlan( (double) Math.round(planDays * 100) / 100 );
        result.setDaysWatchDropped( (double) Math.round(droppedDays * 100) / 100 );
        result.setDaysWatchCompleted( (double) Math.round(completedDays * 100) / 100 );
        result.setDaysWatchWatching( (double) Math.round(watchingDays * 100) / 100 );
        result.setDaysWatchOnHold( (double) Math.round(onHoldDays * 100) / 100 );

        result.setNoEpisodesCompleted(noEpisodesCompleted);
        result.setNoEpisodesDropped(noEpisodesDropped);
        result.setNoEpisodesPlan(noEpisodesPlan);
        result.setNoEpisodesWatching(noEpisodesWatching);
        result.setNoEpisodesOnHold(noEpisodesOnHold);

        return result;
    }

    @Override
    public BookStatsDto getPersonalBookStats(List<BookReview> bookReviews) {
        int totalCount = bookReviews.size();
        int planCount = 0;
        int completedCount = 0;
        int readingCount = 0;

        int completedTotalLength = 0;
        int planTotalLength = 0;
        int readingTotalLength = 0;

        for(BookReview x : bookReviews) {
            if(x.getCategory().equals("plan_read")) {
                planCount++;
                planTotalLength += Integer.parseInt( x.getBooks().getNumPages().split(" pages")[0].trim() );
            }
            if(x.getCategory().equals("completed")) {
                completedCount++;
                completedTotalLength += Integer.parseInt( x.getBooks().getNumPages().split(" pages")[0].trim() );
            }
            if(x.getCategory().equals("reading")) {
                readingCount++;
                readingTotalLength += Integer.parseInt( x.getBooks().getNumPages().split(" pages")[0].trim() );
            }
        }

        BookStatsDto result = new BookStatsDto();
        result.setCompleted(completedCount);
        result.setReading(readingCount);
        result.setPlanRead(planCount);
        result.setTotalCount(totalCount);
        result.setNoPagesPlan(planTotalLength);
        result.setNoPagesReading(readingTotalLength);
        result.setNoPagesCompleted(completedTotalLength);

        return result;
    }

    @Override
    public TotalTimeStatsDto getTotalTime(List<MovieReviews> movieReviews, List<TvSeriesReviews> tvSeriesReviews, List<AnimeReview> animeReviews) {
        TotalTimeStatsDto totalTimeStatsDto = new TotalTimeStatsDto();

        int totalMinutes = 0;

        for(MovieReviews x : movieReviews) {
            if(x.getCategory().equals("completed")) {
                totalMinutes += x.getMovie().getLength();
            }
        }

        for(AnimeReview x : animeReviews) {
            int temp = x.getAnime().getAverageRuntime() * x.getAnime().getNumberEpisodes();
            if(x.getCategory() == null) {
                continue;
            }
            if(x.getCategory().equals("completed")) {
                totalMinutes += temp;
            }
        }

        for(TvSeriesReviews x : tvSeriesReviews) {
            int temp = x.getTvSeries().getAverageRuntime() * x.getTvSeries().getNumberEpisodes();
            if(x.getCategory().equals("completed")) {
                totalMinutes += temp;
            }
        }

        int daysCount = totalMinutes / (24 * 60);
        totalMinutes -= daysCount * 24 * 60;
        int hoursCount = totalMinutes / 60;
        totalMinutes -= hoursCount * 60;
        int minutesCount = totalMinutes % 60;

        totalTimeStatsDto.setDays(daysCount);
        totalTimeStatsDto.setHours(hoursCount);
        totalTimeStatsDto.setMinutes(minutesCount);

        return totalTimeStatsDto;
    }

    @Override
    public boolean getExistingFollowRelation(String nickname, String follower) {
        return followersRepository.checkForExistingFollower(nickname, follower) != null;
    }

    @Override
    public void saveFollower(String username, String follower) {
        Followers followers = new Followers();
        followers.setId(new FollowerId( username, follower ));
        followersRepository.save(followers);
    }

    @Override
    public void deleteFollower(String username, String follower) {
        followersRepository.deleteFollower(username, follower);
    }

    @Override
    public List<Followers> getAllActiveFollowers(String nickname) {
        return followersRepository.getAllFollowersForUser(nickname);
    }

    @Override
    public List<FollowersInfoDto> getAllFollowersInfo(List<Followers> followers) {
        List<FollowersInfoDto> result = new ArrayList<>();

        for(Followers follower : followers) {
            Optional<UserInfo> userInfo = userInfoRepository.findByNickname(follower.getId().getFollower());
            FollowersInfoDto temp = new FollowersInfoDto();
            if(userInfo.isPresent()) {
                temp.setNickname(userInfo.get().getNickname());
                temp.setFullName(userInfo.get().getFullName());
                result.add(temp);
            }
        }
        return result;
    }

}