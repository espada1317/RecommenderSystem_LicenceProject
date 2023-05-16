package com.example.recsys.controller;

import com.example.recsys.entity.TvSeries;
import com.example.recsys.entity.TvSeriesReviews;
import com.example.recsys.service.ProfileService;
import com.example.recsys.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/tv")
public class TvSeriesController {

    @Autowired
    private final TvSeriesService tvSeriesService;

    @Autowired
    private final ProfileService profileService;

    public TvSeriesController(TvSeriesService tvSeriesService, ProfileService profileService) {
        this.tvSeriesService = tvSeriesService;
        this.profileService = profileService;
    }

    @GetMapping
    public String listOfTvSeries( Model model,
                                @Param("keyword") String keyword,
                                @Param("genre") String genre,
                                @Param("year") Integer startYear,
                                @Param("year") Integer endYear,
                                @Param("sortBy") String sortBy,
                                Principal principal) {
        model.addAttribute("isTvPage",true);
        model.addAttribute("principalName",principal.getName());
        model.addAttribute("tvGenres", tvSeriesService.getTvGenres());
        model.addAttribute("releaseStartYears", tvSeriesService.getReleaseStartYears());
        model.addAttribute("releaseEndYears", tvSeriesService.getReleaseEndYears());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre",genre);
        model.addAttribute("selectedStartYear", startYear);
        model.addAttribute("selectedEndYear", endYear);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("tvSeries", tvSeriesService.searchTvByMultipleFilter(keyword, genre, startYear, endYear, sortBy));
        model.addAttribute("friendsRecommend", tvSeriesService.recommendedByFriends( profileService.getAllActiveFollowers(principal.getName()), principal.getName() ));
        return "tv_series";
    }

    @GetMapping("/getById/{id}")
    public String tvById(Model model,
                            @PathVariable("id") int tvID,
                            Principal principal) {
        model.addAttribute("isTvPage",true);
        TvSeries tvDetails = tvSeriesService.getTvById(tvID);
        model.addAttribute("tvSeries", tvDetails);
        model.addAttribute("tvID", tvID);
        model.addAttribute("similarTvs", tvSeriesService.getSimilarContent(tvDetails));

        Optional<TvSeriesReviews> tvReview = tvSeriesService.getReviewByNicknameAndTvId(principal.getName(), tvID);
        tvReview.ifPresent(tvReviews -> model.addAttribute("reviewInfo", tvReviews));

        return "tv_details";
    }

}
