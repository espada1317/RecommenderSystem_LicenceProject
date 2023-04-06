package com.example.recsys.service.implementation;

import com.example.recsys.comparators.anime.AnimeLengthComparator;
import com.example.recsys.comparators.anime.AnimeScoreComparator;
import com.example.recsys.comparators.anime.AnimeTitleComparator;
import com.example.recsys.comparators.anime.reviews.AnimeReviewLengthComparator;
import com.example.recsys.comparators.anime.reviews.AnimeReviewTitileComparator;
import com.example.recsys.dto.AnimeReviewDto;
import com.example.recsys.entity.Anime;
import com.example.recsys.entity.AnimeReview;
import com.example.recsys.repository.AnimeRepository;
import com.example.recsys.repository.AnimeReviewRepository;
import com.example.recsys.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeServiceImpl implements AnimeService {

    @Autowired
    private final AnimeRepository animeRepository;

    @Autowired
    private final AnimeReviewRepository animeReviewRepository;

    public static final int LIMIT = 48;

    public AnimeServiceImpl(AnimeRepository animeRepository, AnimeReviewRepository animeReviewRepository) {
        this.animeRepository = animeRepository;
        this.animeReviewRepository = animeReviewRepository;
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Override
    public List<Anime> searchAnimeByMultipleFilter(String keyword, String genre, String type, String source, Integer startYear, Integer endYear, String sortBy) {
        List<Anime> result = getAllAnime();

        if(keyword != null) {
            List<Anime> keywordList = animeRepository.findByTitleContaining(keyword);
            result.retainAll(keywordList);
        }
        if(genre != null && !genre.equals("*")) {
            List<Anime> genreList = animeRepository.findByGenreContaining(genre);
            result.retainAll(genreList);
        }
        if(type != null && !type.equals("*")) {
            List<Anime> typeList = animeRepository.findByTypeContaining(type);
            result.retainAll(typeList);
        }
        if(source != null && !source.equals("*")) {
            List<Anime> sourceList = animeRepository.findBySourceContaining(source);
            result.retainAll(sourceList);
        }
        if(startYear != null && startYear != 0) {
            List<Anime> startYearList = animeRepository.findByMinimStartYearRelease(startYear);
            result.retainAll(startYearList);
        }
        if(endYear != null && endYear != 0) {
            List<Anime> endYearList = animeRepository.findByMaximEndYearRelease(endYear);
            result.retainAll(endYearList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new AnimeTitleComparator());
                }
                case "titleDesc" -> {
                    result.sort(new AnimeTitleComparator().reversed());
                }
                case "lengthAsc" -> {
                    result.sort(new AnimeLengthComparator());
                }
                case "lengthDesc" -> {
                    result.sort(new AnimeLengthComparator().reversed());
                }
                case "scoreAsc" -> {
                    result.sort(new AnimeScoreComparator());
                }
                case "scoreDesc" -> {
                    result.sort(new AnimeScoreComparator().reversed());
                }
            }
        }

        return result.stream()
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAnimeGenres() {
        return animeRepository.findAllDistinctGenreList();
    }

    @Override
    public List<String> getAnimeTypes() {
        return animeRepository.findAllDistinctTypeList();
    }

    @Override
    public List<String> getAnimeSources() {
        return animeRepository.findAllDistinctSourceList();
    }

    @Override
    public List<Integer> getReleaseStartYears() {
        return animeRepository.getReleaseStartYears();
    }

    @Override
    public List<Integer> getReleaseEndYears() {
        return animeRepository.getReleaseEndYears();
    }

    @Override
    public Anime getAnimeById(Integer animeId) {
        return animeRepository.findById(animeId).get();
    }

    @Override
    public void saveReview(Integer animeId, String nickname, AnimeReviewDto animeReviewDto) {
        Anime referencedAnime = getAnimeById(animeId);

        AnimeReview animeReviews = new AnimeReview();
        animeReviews.setReviewScore(animeReviewDto.getScoreReview());
        animeReviews.setCategory(animeReviewDto.getCategory());
        animeReviews.setReviewMessage(animeReviewDto.getReviewMessage());
        animeReviews.setAnime(referencedAnime);
        animeReviews.setNickname(nickname);
        animeReviews.setLocalDateTime(LocalDateTime.now());

        animeReviewRepository.save(animeReviews);
    }

    @Override
    public void updateReview(String nickname, Integer animeId, AnimeReviewDto animeReviewDto) {
        Optional<AnimeReview> animeReview = Optional.ofNullable(animeReviewRepository.findReviewByUserAndAnime(nickname, animeId));

        if(animeReview.isPresent()) {
            String modifiedCategory = animeReviewDto.getCategory();
            Integer modifiedScore = animeReviewDto.getScoreReview();
            String modifiedReview = animeReviewDto.getReviewMessage();

            animeReviewRepository.updateReview(modifiedCategory, modifiedScore, modifiedReview, LocalDateTime.now(), animeReview.get().getAnimeReviewKey());
        }
    }

    @Override
    public void deleteReview(String nickname, Integer animeId) {
        Optional<AnimeReview> animeReview = Optional.ofNullable(animeReviewRepository.findReviewByUserAndAnime(nickname, animeId));
        animeReview.ifPresent(review -> animeReviewRepository.deleteReview(review.getAnimeReviewKey()));
    }

    @Override
    public Optional<AnimeReview> getReviewByNicknameAndAnimeId(String nickname, Integer animeId) {
        return Optional.ofNullable(animeReviewRepository.findReviewByUserAndAnime(nickname, animeId));
    }

    @Override
    public List<AnimeReview> getAnimeActivity(String nickname) {
        return animeReviewRepository.getAnimeActivity(nickname);
    }

    @Override
    public List<AnimeReview> getAllUserAndFriendsAnimeActivity(String nickname) {
        return animeReviewRepository.getAllUserAndFriendsAnimeActivity(nickname);
    }

    @Override
    public List<AnimeReview> searchPersonalAnimeByMultipleFilter(String nickname, String category, String sortBy) {
        List<AnimeReview> result = getAnimeActivity(nickname);

        if(category != null && !category.equals("*")) {
            List<AnimeReview> categoryList = animeReviewRepository.getReviewsByCategories(nickname, category);
            result.retainAll(categoryList);
        }
        if(sortBy != null && !sortBy.equals("*")) {
            switch (sortBy) {
                case "titleAsc" -> {
                    result.sort(new AnimeReviewTitileComparator());
                }
                case "titleDesc" -> {
                    result.sort(new AnimeReviewTitileComparator().reversed());
                }
                case "lengthAsc" -> {
                    result.sort(new AnimeReviewLengthComparator());
                }
                case "lengthDesc" -> {
                    result.sort(new AnimeReviewLengthComparator().reversed());
                }
            }
        }

        return result;
    }

    @Override
    public List<AnimeReview> getRecentWatchedAnime(String nickname) {
        return animeReviewRepository.getRecentWatchedAnime(nickname);
    }

    @Override
    public List<AnimeReview> getPlanToWatchAnime(String nickname) {
        return animeReviewRepository.getPlanToWatchAnime(nickname);
    }

    @Override
    public List<AnimeReview> getReviewsByNickname(String nickname) {
        return animeReviewRepository.getReviewsByNickname(nickname);
    }

}