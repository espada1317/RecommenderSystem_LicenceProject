package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimeStatsDto {

    private int days;
    private int hours;
    private int minutes;

    private int completed;
    private int planWatch;
    private int dropped;
    private int onHold;
    private int watching;

    private int totalCount;

    private int noEpisodesPlan;
    private int noEpisodesWatching;
    private int noEpisodesCompleted;
    private int noEpisodesOnHold;
    private int noEpisodesDropped;

    private double daysWatchPlan;
    private double daysWatchDropped;
    private double daysWatchCompleted;
    private double daysWatchWatching;
    private double daysWatchOnHold;

}