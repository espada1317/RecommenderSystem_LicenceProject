package com.example.recsys.service.implementation;

import com.example.recsys.comparators.tvseries.TvImdbComparator;
import com.example.recsys.comparators.tvseries.TvLengthComparator;
import com.example.recsys.comparators.tvseries.TvTitleComparator;
import com.example.recsys.entity.TvSeries;
import com.example.recsys.repository.TvSeriesRepository;
import com.example.recsys.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvSeriesServiceImpl implements TvSeriesService {

    @Autowired
    private final TvSeriesRepository tvSeriesRepository;

    public TvSeriesServiceImpl(TvSeriesRepository tvSeriesRepository) {
        this.tvSeriesRepository = tvSeriesRepository;
    }

    public static final int LIMIT = 48;

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

}