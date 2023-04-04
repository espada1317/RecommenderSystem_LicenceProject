package com.example.recsys.service;

import com.example.recsys.entity.TvSeries;

import java.util.List;

public interface TvSeriesService {

    List<TvSeries> getAllTv();

    List<TvSeries> searchTvByMultipleFilter(String keyword, String genre, Integer startYear, Integer endYear, String sortBy);

    TvSeries getTvById(int tvID);

    List<String> getTvGenres();

    List<Integer> getReleaseStartYears();

    List<Integer> getReleaseEndYears();
}