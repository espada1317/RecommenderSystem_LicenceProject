package com.example.recsys.controller;

import com.example.recsys.entity.AnimeReview;
import com.example.recsys.service.AnimeService;
import com.example.recsys.service.ProfileService;
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
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private final AnimeService animeService;

    @Autowired
    private final ProfileService profileService;

    public AnimeController(AnimeService animeService, ProfileService profileService) {
        this.animeService = animeService;
        this.profileService = profileService;
    }

    @GetMapping
    public String listOfAnime( Model model,
                                  @Param("keyword") String keyword,
                                  @Param("genre") String genre,
                                  @Param("type") String type,
                                  @Param("source") String source,
                                  @Param("year") Integer startYear,
                                  @Param("year") Integer endYear,
                                  @Param("sortBy") String sortBy,
                                  Principal principal) {
        model.addAttribute("isAnimePage", true);
        model.addAttribute("principalName", principal.getName());
        model.addAttribute("animeGenres", animeService.getAnimeGenres());
        model.addAttribute("animeTypes", animeService.getAnimeTypes());
        model.addAttribute("animeSources", animeService.getAnimeSources());
        model.addAttribute("releaseStartYears", animeService.getReleaseStartYears());
        model.addAttribute("releaseEndYears", animeService.getReleaseEndYears());
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedGenre",genre);
        model.addAttribute("selectedType",type);
        model.addAttribute("selectedSource",source);
        model.addAttribute("selectedStartYear", startYear);
        model.addAttribute("selectedEndYear", endYear);
        model.addAttribute("selectedSort", sortBy);
        model.addAttribute("animeList", animeService.searchAnimeByMultipleFilter(keyword, genre, type, source, startYear, endYear, sortBy));
        model.addAttribute("friendsRecommend", animeService.recommendedByFriends( profileService.getAllActiveFollowers(principal.getName()), principal.getName() ));
        return "anime";
    }

    @GetMapping("/getById/{id}")
    public String animeById(Model model,
                         @PathVariable("id") int animeID,
                         Principal principal) {
        model.addAttribute("isAnimePage", true);
        model.addAttribute("animeDetails", animeService.getAnimeById(animeID));
        model.addAttribute("animeID", animeID);
        model.addAttribute("similarAnime", animeService.getSimilarContent(animeService.getAnimeById(animeID)));

        Optional<AnimeReview> animeReview = animeService.getReviewByNicknameAndAnimeId(principal.getName(), animeID);
        animeReview.ifPresent(anime -> model.addAttribute("reviewInfo", anime));

        return "anime_details";
    }

}