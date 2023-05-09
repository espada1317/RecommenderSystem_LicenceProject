package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieStatsDto {

    private int days;
    private int hours;
    private int minutes;
    private int completed;
    private int planWatch;
    private int dropped;
    private int totalCount;
    private double daysWatchPlan;
    private double daysWatchDropped;
    private double daysWatchCompleted;

}