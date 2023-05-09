package com.example.recsys.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookStatsDto {

    private int completed;
    private int planRead;
    private int reading;

    private int totalCount;

    private int noPagesPlan;
    private int noPagesCompleted;
    private int noPagesReading;

}